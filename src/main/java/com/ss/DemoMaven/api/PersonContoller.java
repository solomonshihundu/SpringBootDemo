package com.ss.DemoMaven.api;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ss.DemoMaven.model.Person;
import com.ss.DemoMaven.service.PersonService;

@RequestMapping("api/v1/person")
@RestController
public class PersonContoller {

	private final PersonService personService;
	
	@Autowired
	public PersonContoller(PersonService personService)
	{
		this.personService = personService;
	}
	
	@PostMapping
	public void addPerson(@RequestBody Person person)
	{
		personService.addPerson(person);	
	}
	
	@GetMapping
	public List<Person> getAllPersons()
	{
		return personService.getAllPersons();
	}
	
	@GetMapping(path = "(id )")
	public Person getPersonById(@PathVariable("id")UUID id)
	{
		return personService.getPersonById(id)
				.orElse(null);
	}
}
