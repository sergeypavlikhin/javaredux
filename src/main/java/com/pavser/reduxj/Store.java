package com.pavser.reduxj;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergey on 18.05.2017.
 */
public class Store<StateType> {

    public static <StateType> Store createStore(Reducer reducer, StateType defaultValue){
        return new Store(reducer, new State<StateType>(defaultValue));
    }

    private List<Subscriber> subscribers;
    private MainReducer<StateType> reducer;
    private State<StateType> state;

    private Store(State state) {
        this.state = state;
        this.subscribers = new ArrayList<>();
    }

    public void dispatch(Action action){
        state = reducer.reduce(state, action);
        noticeSubscribers();
    }

    public Store addReducer(Reducer<StateType> reducer){
        getMainReducer().addReducer(reducer);
    }

    private MainReducer<StateType> getMainReducer(){
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
