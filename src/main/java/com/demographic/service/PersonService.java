package com.demographic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demographic.dao.PersonDAO;
import com.demographic.model.Person;

@Service
public class PersonService {

	@Autowired
	private PersonDAO personDAO;

	public List<Person> findAll() {
		return (List<Person>) personDAO.findAll();

	}

	public Person save(Person person) {
		personDAO.save(person);
		return person;
	}
	
	
	public List<Person> findByPpsNumberLike(String ppsNumber){
		 return (List<Person>) personDAO.findByPpsNumberLike(ppsNumber);
	}
	

}
