package com.demographic;

import static org.junit.Assert.assertEquals;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.demographic.dao.PersonDAO;
import com.demographic.model.Person;
import com.demographic.service.PersonService;


@RunWith(SpringJUnit4ClassRunner.class)
public class PersonServiceTest {
	
	
	@InjectMocks
	private PersonService personService;
	
	@Mock
	private PersonDAO personDAO;
	
	
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
	}
	
	
	
	@Test
	public void savePerson(){
		Person person = new Person("1", "Test 1", new Date(), "");
		Person result = personService.save(person);
		assertEquals("Test 1", result.getName());
	}
	

}
