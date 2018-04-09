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

    private List<Subscriber> subscribers;
    private MainReducer<StateType, ActionType> reducer;
    private State<StateType> state;

    public Store(State state) {
        this.state = state;
        this.subscribers = new ArrayList<>();
    }

    public void dispatch(Action action){
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
