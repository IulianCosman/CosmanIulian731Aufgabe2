package org.example.Repositories;

import java.util.List;

public interface Repository<T> {
    void add(T item);
    void remove(T item);
    void update(T item1, T item2);
    List<T> getAll();
}
