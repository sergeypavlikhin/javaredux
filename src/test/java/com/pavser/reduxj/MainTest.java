package com.pavser.reduxj;

import com.pavser.reduxj.helpers.ActionCreator;
import com.pavser.reduxj.skeletone.State;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainTest {

    public static final String NEW_MESSAGE_KEY = "NEW_MESSAGE";
    public static final String NUMBER_MESSAGE_TO_DELETE_KEY = "NUMBER_MESSAGE_TO_DELETE";

    enum ActionType{
        ADD_MESSAGE,
        DELETE_MESSAGE;
    }

    @Test
    public void testTest(){
        State<List<String>> state = new State<>(new ArrayList<>());
        Store<List<String>, ActionType> store = new Store<>(state);

        System.out.println("Init state of store:");
        System.out.println(store.getState());

        store.addReducer((currentState, action) -> {
            switch (action.getType()){
                case ADD_MESSAGE: {
                    Map<String, String> payload = action.getPayload();
                    String mess = payload.get(NEW_MESSAGE_KEY);

                    List<String> messages = currentState.getValue();
                    messages.add(mess);

                    return messages;
                }
                case DELETE_MESSAGE: {
                    Map<String, String> payload = action.getPayload();
                    String mess = payload.get(NUMBER_MESSAGE_TO_DELETE_KEY);

                    List<String> messages = currentState.getValue();
                    messages.remove(Integer.parseInt(mess));

                    return messages;
                }
            }
            return null;
        });

        store.dispatch(ActionCreator.create(ActionType.ADD_MESSAGE, new HashMap<String, String>(){{put(NEW_MESSAGE_KEY, "Привет, Я Программист");}}));


        System.out.println("State after dispatch:");
        System.out.println(store.getState());


    }
}
