package com.locke.olap.impl;

import com.locke.olap.ViewSerde;
import com.locke.olap.error.MalformedViewException;
import com.locke.olap.models.*;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.jsontype.NamedType;

import java.io.IOException;


/**
 * @author David Gilmore
 * @date 5/15/14
 */
public class JsonViewSerde<T> implements ViewSerde {

    @Override
    public View deserialize(String message) throws MalformedViewException, IOException {

        ObjectMapper mapper = new ObjectMapper();

        mapper.registerSubtypes(
                  new NamedType(Condition.class, "condition")
                , new NamedType(JoinView.class, "join")
                , new NamedType(TableView.class, "table")
                , new NamedType(SelectView.class, "select")
        );

        View view;

        try {
            view = mapper.readValue(message, View.class);
        } catch (IOException e) {
            throw new MalformedViewException(e.getMessage());
        }

        return view;
    }

    @Override
    public String serialize(Object object) {
        return null;
    }
}
