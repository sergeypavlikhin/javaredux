package com.pavser.javaredux;

import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sergey on 18.05.2017.
 */

public class Tester {

    @Test
    public void basicTestCase(){
        Store<Map<String, List<String>>, MapDispatchableObject> store = Store.createStore(new MapReducer(), new HashMap<>());


    }

    private static class MapReducer implements Reducer<Map<String, List<String>>, MapDispatchableObject>{

        @Override
        public Map<String, List<String>> reduce(Map<String, List<String>> stringListMap, MapDispatchableObject object) {
            switch (object.getCommand()){
                case "ADD":{
                    stringListMap.get(object.getName()).addAll(object.getMessage());
                    break;
                }
                case "REMOVE":{
                    stringListMap.get(object.getName()).removeAll(object.getMessage());
                    break;
                }
            }
            return stringListMap;
        }
    }

    private static class MapDispatchableObject implements DispatchableObject{

        private String command;
        private String name;
        private List<String> message;

        public MapDispatchableObject(String command, String name, List<String> message) {
            this.command = command;
            this.name = name;
            this.message = message;
        }

        public String getCommand() {
            return command;
        }

        public void setCommand(String command) {
            this.command = command;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<String> getMessage() {
            return message;
        }

        public void setMessage(List<String> message) {
            this.message = message;
        }
    }

}
