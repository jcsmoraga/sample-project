package com.demographic.dao;


import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.demographic.model.Person;

@Repository
public interface  PersonDAO extends CrudRepository<Person, Long> {

    public List<Person> findByPpsNumberLike(String ppsNumber);
    
   
}