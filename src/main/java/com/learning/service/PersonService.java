/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.learning.service;

import com.learning.model.Person;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface PersonService {

    public List<Person> getData(String searchName, Integer searchSex, String searchCountry, Date startTime, Date endTime, int pageIndex, int pageSize);

    public Integer count(String searchName, Integer searchSex, String searchCountry, Date startTime, Date endTime);

    public List<Person> getAllData();

    public void addData(Person p);

    public void delete(Person p);

    public Person getById(Long id);

    public List<Person> searchByDate(Date startTime, Date endTime, int pageIndex, int pageSize);

    public Integer countByDate(Date startTime, Date endTime);

}
