package com.example.ukgtime;

import com.example.ukgtime.Employee;
public interface CorporateEventDao {
    boolean add(Employee employee);
    boolean find(long eId);
}
