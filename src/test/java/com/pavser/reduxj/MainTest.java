package com.pavser.reduxj;

import com.pavser.reduxj.helpers.ActionHelper;
import com.pavser.reduxj.skeletone.State;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.pavser.reduxj.MainTest.ActionType.ADD_MESSAGE;
import static com.pavser.reduxj.MainTest.ActionType.DELETE_MESSAGE;

public class MainTest {

    public static final String NEW_MESSAGE_KEY = "NEW_MESSAGE";
    public static final String NUMBER_MESSAGE_TO_DELETE_KEY = "NUMBER_MESSAGE_TO_DELETE";

    enum ActionType{
        ADD_MESSAGE,
        DELETE_MESSAGE;
    }

    @Test
    public void testTest(){
        Store.<List<String>, ActionType>initialize(new ArrayList<>());

        System.out.println("Init state of store:");
        System.out.println(Store.getInstance().getState());

        Store.<List<String>, ActionType>getInstance().addReducer((currentState, action) -> {
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

        Store.<List<String>, ActionType>getInstance().dispatch(ActionCreator.addMessage("Привет, Я Программист"));

        System.out.println("State after dispatch:");
        System.out.println(Store.getInstance().getState());


    }
}
