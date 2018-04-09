package com.pavser.reduxj.helpers;

import com.pavser.reduxj.skeletone.Action;

import java.io.Serializable;
import java.util.Map;

public class ActionCreator {

    /**
     * Just create instance of Action.
     *
     * @param type - action type
     * @param payload - action payload
     * @param <ActionType> - generic
     * @return - new instance of Action
     */
    public static <ActionType extends Serializable> Action<ActionType> create(ActionType type, Map<String, String> payload){
        return new Action<ActionType>() {
            @Override
            public ActionType getType() {
                return type;
            }

            @Override
            public Map<String, String> getPayload() {
                return payload;
            }
        };
    }



}
