package ru.job4j;

import org.junit.Test;

import java.util.stream.IntStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CASCountTest {

    @Test
    public void whenOfferThenPoll() throws InterruptedException {
        CASCount casCount = new CASCount(0);

        Thread first = new Thread(() -> {
            IntStream.range(0, 10).forEach(i -> casCount.increment());
        });
        Thread second = new Thread(() -> {
            IntStream.range(0, 5).forEach(i -> casCount.increment());
        });
        first.start();
        second.start();
        first.join();
        second.join();
        assertThat(casCount.get(), is(15));
    }
}