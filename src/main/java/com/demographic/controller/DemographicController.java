package com.demographic.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.demographic.formbean.PersonForm;
import com.demographic.model.Person;
import com.demographic.service.PersonService;
import com.demographic.validator.PersonValidator;

@Controller
public class DemographicController {

	@Autowired
	private PersonService personService;
	
	private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

	@Autowired
	private PersonValidator personValidator;

	@InitBinder
	protected void initBinder(WebDataBinder dataBinder) {
		Object target = dataBinder.getTarget();
		if (target == null) {
			return;
		}
		System.out.println("Target=" + target);

		if (target.getClass() == PersonForm.class) {
			dataBinder.setValidator(personValidator);
		}
	}

	@RequestMapping("/persons")
	public String viewMembers(Model model) {
		List<Person> list = (List<Person>) personService.findAll();
		String page="";
		if(list !=null && !list.isEmpty()) {
			model.addAttribute("persons", list);
			page="personsPage";
		}
		else {
			page="noDataPage";
		}
		
		return page;
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String viewRegister(Model model) {
		PersonForm form = new PersonForm();
		model.addAttribute("personForm", form);
		return "registrationPage";
	}

	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String saveRegister(Model model, 
			@ModelAttribute("personForm") @Validated PersonForm personForm, 
			BindingResult result, 
			final RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			return "registrationPage";
		}
		Person person = null;
		try {
			person = createAppUser(personForm);
			personService.save(person);
		}
		catch (Exception e) {
			model.addAttribute("errorMessage", "Error: " + e.getMessage());
			return "registrationPage";
		}

		redirectAttributes.addFlashAttribute("flashPerson", person);

		return "redirect:/persons";
	}
	
	public Person createAppUser(PersonForm form) {
		Person person=null;
		try {
			Date dob = df.parse(form.getDob());
			person = new Person(form.getPpsNumber(), form.getName(), dob, form.getPhoneNumber());
		} catch (Exception ex) {
			System.out.println(ex);
		}

		return person;
	}

}
