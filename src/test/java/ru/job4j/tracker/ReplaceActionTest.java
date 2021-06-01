package ru.job4j.tracker;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ReplaceActionTest {
    @Test
    public void execute() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        String name = "Replaced item";
        tracker.add(new Item(name));
        String id = String.valueOf(tracker.findByName(name).get(0).getId());
        String replacedName = "New item name";
        ReplaceAction rep = new ReplaceAction();

        Input input = mock(Input.class);

        when(input.askInt(any(String.class))).thenReturn(Integer.valueOf(id));
        when(input.askStr(any(String.class))).thenReturn(replacedName);

        rep.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(output.toString(), is("Done" + ln));
        assertThat(tracker.findAll().get(0).getName(), is(replacedName));
    }
}