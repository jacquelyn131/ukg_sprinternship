package com.example.ukgtime;

import java.util.List;
import java.util.Optional;

public interface CorporateEventDao<T> {
    boolean add(T t);
    boolean find(long id);
    List<T> list();
    Optional<T> get(long id);
    void update(T t, long id);
    boolean delete(long id);


}
