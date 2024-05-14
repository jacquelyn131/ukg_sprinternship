package com.example.ukgtime;

import com.example.ukgtime.Employee.Employee;

import java.util.List;
import java.util.Optional;

public interface CorporateEventDao<T> {
    boolean add(Employee employee);
    boolean find(long eId);
    List<T> list();
    Optional<T> get(long id);
    void update(T t, long id);
    void delete(long id);


}
