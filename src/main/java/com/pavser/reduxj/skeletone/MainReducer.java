package com.pavser.reduxj.skeletone;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainReducer<StateType, ActionType extends Serializable> {

    private final List<Reducer<StateType, ActionType>> reducers = new ArrayList<>();

    public MainReducer<StateType, ActionType> addReducer(Reducer<StateType, ActionType> reducer){
        reducers.add(reducer);
        return this;
    }

    public State<StateType> reduce(State<StateType> currentState, Action<ActionType> action){
        for (Reducer<StateType, ActionType> reducer : reducers){
            StateType nextState = reducer.reduce(currentState, action);
            if (nextState != null) {
                // If there is a consistent state then return one
                return new State<StateType>(nextState);
            }
        }
        return null;
    }

}
