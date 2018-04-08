package com.pavser.reduxj;

import com.rits.cloning.Cloner;

public class State<T> {

    private T value;

    public State(T value) {
        this.value = value;
    }

    public T getValue(){
        Cloner cloner = new Cloner();
        return cloner.deepClone(value);
    }
}
