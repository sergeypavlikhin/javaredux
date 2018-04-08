package com.pavser.reduxj;

/**
 * Created by Sergey on 18.05.2017.
 */
public interface Reducer<StateType> {
    State<StateType> reduce(State<StateType> currentState, Action action);
}
