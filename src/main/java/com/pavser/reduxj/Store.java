package com.pavser.reduxj;

import com.pavser.reduxj.skeletone.Action;
import com.pavser.reduxj.skeletone.MainReducer;
import com.pavser.reduxj.skeletone.Reducer;
import com.pavser.reduxj.skeletone.State;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergey on 18.05.2017.
 */
public class Store<StateType, ActionType extends Serializable> {

    private static volatile Store instance;

    public static <StateType, ActionType extends Serializable> Store<StateType, ActionType> getInstance() {
        Store localInstance = instance;
        if (localInstance == null) {
            synchronized (Store.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new Store();
                }
            }
        }
        return localInstance;
    }

    public static <StateType, ActionType extends Serializable> void initialize(StateType stateType){
        Store.<StateType, ActionType>getInstance().setState(stateType);
    }


    private List<Subscriber> subscribers;
    private MainReducer<StateType, ActionType> reducer;
    private State<StateType> state;

    private <StateType, ActionType> Store() {
        this.subscribers = new ArrayList<>();
    }

    private void setState(StateType state){
        this.state = new State<>(state);
    }

    public void dispatch(Action<ActionType> action){
        state = reducer.reduce(state, action);
        noticeSubscribers();
    }

    public Store addReducer(Reducer<StateType, ActionType> reducer){
        getMainReducer().addReducer(reducer);
        return this;
    }

    private MainReducer<StateType,ActionType> getMainReducer(){
        if (reducer == null){
            reducer = new MainReducer<>();
        }
        return reducer;
    }

    public StateType getState(){
        return state.getValue();
    }

    private void noticeSubscribers(){
        subscribers.forEach(subscriber -> subscriber.notice(this));
    }

    public void addSubscriber(Subscriber subscriber){
        subscribers.add(subscriber);
    }

}
