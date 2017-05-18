package com.pavser.javaredux;

import com.rits.cloning.Cloner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergey on 18.05.2017.
 */
public class Store<StateType, DispatchableInterface extends DispatchableObject> {

    public static <StateType> Store createStore(Reducer reducer, StateType defaultValue){
        return new Store(reducer, new State(defaultValue));
    }

    private List<Subscriber> subscribers;
    private Reducer<StateType, DispatchableInterface> reducer;
    private State<StateType> state;

    private Store(Reducer reducer, State state) {
        this.reducer = reducer;
        this.state = state;
        this.subscribers = new ArrayList<>();
    }

    public void dispatch(DispatchableInterface dispatchableObject){
        StateType newStateValue = reducer.reduce(state.getValue(), dispatchableObject);
        state = new State<>(newStateValue);

        noticeSubscribers();
    }

    public StateType getState(){
        return state.value;
    }

    private void noticeSubscribers(){
        subscribers.forEach(subscriber -> subscriber.notice(this));
    }

    public void addSubscriber(Subscriber subscriber){
        subscribers.add(subscriber);
    }

    static class State<T> {

        private T value;

        public State(T value) {
            this.value = value;
        }

        public T getValue(){
            Cloner cloner = new Cloner();
            return cloner.deepClone(value);
        }
    }
}
