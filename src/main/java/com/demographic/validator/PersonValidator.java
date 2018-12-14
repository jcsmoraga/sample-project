package com.demographic.validator;

import static java.util.Calendar.YEAR;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.demographic.formbean.PersonForm;
import com.demographic.model.Person;
import com.demographic.service.PersonService;

@Component
public class PersonValidator implements Validator {

	private static int NAME_MAX_lENGTH = 25;

	private static int MIN_AGE = 16;

	private static String PHONE_PREFIX = "(08).*";

	private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

	@Autowired
	private PersonService personService;

	@Override
	public boolean supports(Class<?> cl) {
		return cl == PersonForm.class;
	}

	@Override
	public void validate(Object target, Errors errors) {
		PersonForm personForm = (PersonForm) target;
		String phone = null;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.personForm.name");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dob", "NotEmpty.personForm.dob");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ppsNumber", "NotEmpty.personForm.ppsNumber");

		if (personForm.getName().trim().length() > NAME_MAX_lENGTH) {
			errors.rejectValue("name", "Size.personForm.name");
		}

		phone = personForm.getPhoneNumber();
		if (phone != null && !phone.trim().equals("") && !phone.matches(PHONE_PREFIX)) {
			errors.rejectValue("phoneNumber", "Match.personForm.phoneNumber");
		}
		valDOB(errors, personForm);

		if (!errors.hasFieldErrors("name")) {
			 List<Person> persons = (List<Person>) personService.findByPpsNumberLike(personForm.getPpsNumber().trim());
			 if (persons != null && persons.size()==1) {
			 errors.rejectValue("ppsNumber", "Duplicate.personForm.ppsNumber");  
			 }
		}

	}

	void valDOB(Errors errors, PersonForm personForm) {

		try {
			Date dob = df.parse(personForm.getDob());
			Calendar calA = Calendar.getInstance();
			Calendar calB = Calendar.getInstance();
			calA.setTime(dob);
			calB.setTime(new Date());
			
			if (dob.compareTo(new Date()) >= 0) {
				errors.rejectValue("dob", "InvalidDate.personForm.dob");
			}

			if ((calB.get(YEAR)-calA.get(YEAR)) < MIN_AGE) {
				errors.rejectValue("dob", "InvalidAge.personForm.dob");
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
