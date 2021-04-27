package ru.job4j.tracker;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DeleteActionTest {
    @Test
    public void whenDelete() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Store tracker = new MemTracker();
        String name = "Nikita";
        Item item = new Item(name);
        tracker.add(item);
        DeleteAction del = new DeleteAction();

        Input input = mock(Input.class);

        when(input.askStr(any(String.class))).thenReturn(item.getId());
        del.execute(input, tracker);

        assertThat(out.toString(), is("Done." + System.lineSeparator()));
    }

}