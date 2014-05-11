package com.locke;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import de.flapdoodle.embed.mongo.Command;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.config.RuntimeConfigBuilder;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.config.IRuntimeConfig;
import de.flapdoodle.embed.process.config.io.ProcessOutput;
import org.h2.jdbcx.JdbcDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author David Gilmore
 * @date 5/11/14
 */
public abstract class IntegrationTestCase {

    protected static DB mongoDB;
    protected static Connection conn;
    protected static JdbcDataSource ds;
    protected static JdbcTemplate jdbcTemplate;

    private static MongodExecutable mongodExecutable;

    private static final Logger logger = Logger.getLogger("");


    static {

        initMongo();
        initH2();

        Runtime.getRuntime().addShutdownHook(new Thread() {

            public void run() {

                try {
                    IntegrationTestCase.conn.close();
                    IntegrationTestCase.mongodExecutable.stop();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private static void initMongo() {

        if (mongoDB == null) {

            IRuntimeConfig runtimeConfig = new RuntimeConfigBuilder()
                    .defaultsWithLogger(Command.MongoD, logger)
                    .processOutput(ProcessOutput.getDefaultInstanceSilent())
                    .build();

            MongodStarter runtime = MongodStarter.getInstance(runtimeConfig);

            int port = getOpenPort();

            IMongodConfig mongodConfig = null;

            try {
                mongodConfig = new MongodConfigBuilder()
                        .version(Version.Main.PRODUCTION)
                        .net(new Net(port, true))
                        .build();
            } catch (IOException e) {
                logger.log(Level.SEVERE, e.getMessage());
            }

            mongodExecutable = runtime.prepare(mongodConfig);

            try {
                mongodExecutable.start();
            } catch (IOException e) {
                logger.log(Level.SEVERE, e.getMessage());
            }

            MongoClient mongo = null;

            try {
                mongo = new MongoClient("localhost", port);
            } catch (UnknownHostException e) {
                logger.log(Level.SEVERE, e.getMessage());
            }

            mongoDB = mongo.getDB("test");
        }
    }

    private static void initH2() {

        if (conn == null) {

            ds = new JdbcDataSource();

            ds.setURL("jdbc:h2:test");
            ds.setUser("sa");
            ds.setPassword("sa");

            try {
                conn = ds.getConnection();
            } catch (SQLException e) {
                logger.log(Level.SEVERE, e.getMessage());
            }

            jdbcTemplate = new JdbcTemplate(ds);
        }
    }

    private static int getOpenPort() {

        Random r = new Random();

        int port = r.nextInt(65535);
        while (!available(port)) {
            port = r.nextInt(65535);
        }

        return port;
    }

    private static boolean available(int port) {

        if (port < 0 || port > 65535) {
            throw new IllegalArgumentException("Invalid start port: " + port);
        }

        ServerSocket ss = null;
        DatagramSocket ds = null;
        try {
            ss = new ServerSocket(port);
            ss.setReuseAddress(true);
            ds = new DatagramSocket(port);
            ds.setReuseAddress(true);
            return true;
        } catch (IOException e) {
        } finally {
            if (ds != null) {
                ds.close();
            }

            if (ss != null) {
                try {
                    ss.close();
                } catch (IOException e) {
                /* should not be thrown */
                }
            }
        }

        return false;
    }
}
