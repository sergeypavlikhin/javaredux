package com.pavser.reduxj;

import java.util.ArrayList;
import java.util.List;

public class MainReducer<StateType> {

    private final List<Reducer<StateType>> reducers = new ArrayList<>();

    public MainReducer<StateType> addReducer(Reducer<StateType> reducer){
        reducers.add(reducer);
        return this;
    }

    public State<StateType> reduce(State<StateType> currentState, Action action){
        for (Reducer<StateType> reducer : reducers){
            State<StateType> nextState = reducer.reduce(currentState, action);
            if (nextState != null) {
                // If there is a consistent state then return one
                return nextState;
            }
        }
        return null;
    }

}
