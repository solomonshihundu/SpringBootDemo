package com.ss.DemoMaven.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Repository;

import com.ss.DemoMaven.model.Person;

@Repository("fakeDao")
public class FakePersonDataAccessService implements PersonDao
{
	private static List<Person> personDB = new ArrayList<>();

	@Override
	public int insertPerson(UUID id, Person person) 
	 {
		personDB.add(new Person(id,person.getName()));
		return 1;
	}

	@Override
	public List<Person> selectAllPeople() {
		// TODO Auto-generated method stub
		return personDB;
	}

	@Override
	public Optional<Person> selectPersonById(UUID id) 
	{
		System.out.print("#############################"+id);
	
		return personDB.stream()
				.filter(person -> person.getId().equals(id))
				.findFirst();
	}

	@Override
	public int deletePersonById(UUID id) 
	{
		Optional<Person> personMaybe = selectPersonById(id);
		if(!personMaybe.isPresent())
		{
			return 0;
		}
		personDB.remove(personMaybe.get());
		return 1;
	}

	@Override
	public int updatePersonById(UUID id, Person personUpdate)
	{
		return selectPersonById(id)
				.map( person -> 
				{
					int indexOfPersonToUpdate = personDB.indexOf(person);
					if(indexOfPersonToUpdate >= 0)
					{
						personDB.set(indexOfPersonToUpdate, new Person(id,personUpdate.getName()));
						return 1;
					}
					return 0;
				}).orElse(0);
	}

}
