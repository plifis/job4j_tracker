package ru.job4j.tracker;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Array;
import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;


public class StartUITest {
    @Test
    public void whenExit() {
        StubInput input = new StubInput(
                new String[] {"0"}
        );
        StubAction action = new StubAction();
        List<UserAction> list = Arrays.asList(action);
        new StartUI().init(input, new MemTracker(), list);
        assertThat(action.isCall(), is(true));
    }
    @Test
    public void whenPrtMenu() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream def = System.out;
        System.setOut(new PrintStream(out));
        StubInput input = new StubInput(
                new String[] {"0"}
        );
        StubAction action = new StubAction();
        List<UserAction> list = Arrays.asList(action);
        new StartUI().init(input, new MemTracker(), list);
        String expect = new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                .add("Menu.")
                .add("0. Stub action")
                .toString();
        assertThat(new String(out.toByteArray()), is(expect));
        System.setOut(def);
    }
}
