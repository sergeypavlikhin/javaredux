package com.pavser.reduxj;

import org.junit.Test;

public class MainTest {

    @Test
    public void testTest(){
        Store<String> store = Store.createStore(null, null);

        store.addReducer((currentState, action) -> {
            switch (action.getType()){
                case "": {
                    action.getPayload();

                }
            }
        });


    }
}
