package com.pavser.javaredux;

/**
 * Created by Sergey on 18.05.2017.
 */
public interface Reducer<StateType, DispatchableInterface extends DispatchableObject> {

    StateType reduce(StateType state, DispatchableInterface object);


}
