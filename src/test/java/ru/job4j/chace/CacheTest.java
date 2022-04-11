package ru.job4j.chace;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CacheTest {

    @Test
    public void add() {
        Cache cache = new Cache();
        Base base1 = new Base(1, 0);
        Base base2 = new Base(2, 0);
        cache.add(base1);
        cache.add(base1);
        cache.add(base2);
        cache.add(base2);
        cache.add(base2);
        assertThat(cache.size(), is(2));
    }

    @Test
    public void update() {
        Cache cache = new Cache();
        Base base1 = new Base(1, 0);
        cache.add(base1);
        base1.setName("New name");
        assertThat(true, is(cache.update(base1)));
        assertThat(cache.get(base1.getId()).getName(), is("New name"));
    }

    @Test
    public void delete() {
        Cache cache = new Cache();
        Base base1 = new Base(1, 0);
        Base base2 = new Base(2, 0);

        cache.add(base1);
        cache.add(base2);

        cache.delete(base1);
        cache.delete(base1);

        assertThat(cache.size(), is(1));
    }
}