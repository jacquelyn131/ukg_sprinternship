package com.example.ukgtime;

import com.example.ukgtime.Employee.Employee;

public interface CorporateEventDao<T> {
    boolean add(Employee employee);
    boolean find(long eId);
}
