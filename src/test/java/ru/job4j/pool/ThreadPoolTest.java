package ru.job4j.pool;

import org.junit.Test;

import static org.junit.Assert.*;

public class ThreadPoolTest {

    @Test
    public void work() throws InterruptedException {
        ThreadPool threadPool = new ThreadPool();
        threadPool.work(() -> System.out.println("job1"));
        threadPool.work(() -> System.out.println("job2"));
        threadPool.work(() -> System.out.println("job3"));
        threadPool.work(() -> System.out.println("job4"));
        threadPool.work(() -> System.out.println("job5"));
        threadPool.shutdown();
    }
}