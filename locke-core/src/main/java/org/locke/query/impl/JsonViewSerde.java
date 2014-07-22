package org.locke.query.impl;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.jsontype.NamedType;
import org.locke.query.api.ViewSerde;
import org.locke.query.error.MalformedViewException;
import org.locke.query.models.*;

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
                  new NamedType(SimpleCondition.class, "simple")
                , new NamedType(NestedCondition.class, "conditions")
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
