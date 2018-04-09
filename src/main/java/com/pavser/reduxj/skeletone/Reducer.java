package com.pavser.reduxj.skeletone;

import java.io.Serializable;

/**
 * Created by Sergey on 18.05.2017.
 */
public interface Reducer<StateType, ActionType extends Serializable> {
    StateType reduce(State<StateType> currentState, Action<ActionType> action);
}
