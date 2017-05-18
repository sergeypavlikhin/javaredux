package com.pavser.javaredux;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by Sergey on 18.05.2017.
 */

public class Tester {


    private Store<Map<String, List<String>>, MapDispatchableObject> store = null;

    @Before
    public void init(){
        store = Store.createStore(new MapReducer(), new HashMap<>());
    }

    @Test
    public void basicRegistrationTestCase(){
        Map<String, List<String>> expected = generateMap("Sergey", Collections.emptyList());

        store.dispatch(new MapDispatchableObject("NEW", "Sergey", new ArrayList<>()));

        Map<String, List<String>> actual = store.getState();
        assertThat(actual, is(expected));
    }

    @Test
    public void basicAddMessageTestCase(){

        Map<String, List<String>> expected = generateMap("Sergey", Arrays.asList("Hi", "How are your?"));

        store.dispatch(new MapDispatchableObject("NEW", "Sergey", new ArrayList<String>()));
        store.dispatch(new MapDispatchableObject("ADD", "Sergey", Arrays.asList("Hi", "How are your?")));

        Map<String, List<String>> actual = store.getState();
        assertThat(actual, is(expected));
    }

    @Test
    public void immutableTestCase(){

        Map<String, List<String>> expected = store.getState();

        store.dispatch(new MapDispatchableObject("NEW", "Sergey", new ArrayList<String>()));
        store.dispatch(new MapDispatchableObject("ADD", "Sergey", Arrays.asList("Hi", "How are your?")));
        store.dispatch(new MapDispatchableObject("ADD", "Sergey", Arrays.asList("Hi", "How are your?")));

        Assert.assertTrue(expected.isEmpty());
    }

    @Test
    public void basicTestWithSubscriber(){
        CollectorSubscriber subscriber = new CollectorSubscriber();

        store.addSubscriber(subscriber);

        store.dispatch(new MapDispatchableObject("NEW", "Sergey", new ArrayList<String>()));
        store.dispatch(new MapDispatchableObject("ADD", "Sergey", Arrays.asList("Hi", "How are your?")));

        List<Map<String, List<String>>> expected = new ArrayList<>();
        expected.add(generateMap("Sergey", Collections.emptyList()));
        expected.add(generateMap("Sergey", Arrays.asList("Hi", "How are your?")));

        assertThat(subscriber.getNotices(), is(expected));

    }
    private Map<String, List<String>> generateMap(String name,  List<String> items) {
        Map<String, List<String>> expected = new HashMap<>();
        expected.put(name, items);
        return expected;
    }

    private static class CollectorSubscriber implements Subscriber<Map<String, List<String>>>{

        private List<Map<String, List<String>>> notices = new ArrayList<>();

        @Override
        public void notice(Store<Map<String, List<String>>, ?> store) {
            Map<String, List<String>> state = store.getState();

            notices.add(state);
        }

        public List<Map<String, List<String>>> getNotices() {
            return notices;
        }
    }

    private static class MapReducer implements Reducer<Map<String, List<String>>, MapDispatchableObject>{

        @Override
        public Map<String, List<String>> reduce(Map<String, List<String>> stringListMap, MapDispatchableObject object) {
            switch (object.getCommand()){
                case "NEW":{
                    stringListMap.put(object.getName(), object.getMessage());
                    break;
                }
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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            MapDispatchableObject that = (MapDispatchableObject) o;

            if (command != null ? !command.equals(that.command) : that.command != null) return false;
            if (name != null ? !name.equals(that.name) : that.name != null) return false;
            return message != null ? message.equals(that.message) : that.message == null;
        }

        @Override
        public int hashCode() {
            int result = command != null ? command.hashCode() : 0;
            result = 31 * result + (name != null ? name.hashCode() : 0);
            result = 31 * result + (message != null ? message.hashCode() : 0);
            return result;
        }
    }

}
