package com.pavser.reduxj;

import com.pavser.reduxj.helpers.ActionHelper;
import com.pavser.reduxj.skeletone.Action;

import java.util.HashMap;

import static com.pavser.reduxj.MainTest.NEW_MESSAGE_KEY;

public class ActionCreator {

    public static Action<MainTest.ActionType> addMessage(String message){
        return ActionHelper.create(MainTest.ActionType.ADD_MESSAGE, new HashMap<String, String>(){{
            put(NEW_MESSAGE_KEY, message);
        }});
    }

}
