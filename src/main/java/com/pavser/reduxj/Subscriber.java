package com.pavser.reduxj;

import java.io.Serializable;

/**
 * Created by Sergey on 18.05.2017.
 */
public interface Subscriber<StateType, ActionType extends Serializable> {
   void notice(Store<StateType, ActionType> store);
}
