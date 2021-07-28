package com.cos.viewtest.provider;

import com.cos.viewtest.model.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonProvider {

    public List<Person> findAll(){
        List<Person> persons = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            persons.add(new Person("이름"+i, "010222"));
        }
        return persons;
    }
}
