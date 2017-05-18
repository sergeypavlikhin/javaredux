package com.pavser.reduxj;

/**
 * Created by Sergey on 18.05.2017.
 */
public interface Subscriber<StateType> {
   void notice(Store<StateType, ?> store);
}
