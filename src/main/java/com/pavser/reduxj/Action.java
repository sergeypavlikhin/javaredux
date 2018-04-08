package com.pavser.reduxj;

public interface Action<T> {

    T getType();

    Payload getPayload();
}
