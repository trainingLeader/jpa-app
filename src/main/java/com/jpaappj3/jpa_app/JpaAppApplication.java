package com.jpaappj3.jpa_app;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jpaappj3.jpa_app.entities.Person;
import com.jpaappj3.jpa_app.respositories.PersonRepository;

import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class JpaAppApplication implements CommandLineRunner {
	@Autowired
	private PersonRepository personRepository;
	public static void main(String[] args) {
		SpringApplication.run(JpaAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//create();
		// List<Person> persons = (List<Person>) personRepository.findByProgrammingLanguage("Java");
		List<Person> persons = (List<Person>) personRepository.buscarByProgrammingLanguage("Java","Johlver");
		persons.stream().forEach(person -> System.out.println(person));
	}
	@Transactional
	public void create() {
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese el nombre:");
		String name = scanner.next();
		System.out.println("Ingrese el apellido:");
		String lastname = scanner.next();
		System.out.println("Ingrese el lenguaje de programacion:");
		String programmingLanguage = scanner.next();
		scanner.close();

		Person person = new Person(null, name, lastname, programmingLanguage);

		Person personNew = personRepository.save(person);
		System.out.println(personNew);

		personRepository.findById(personNew.getId()).ifPresent(System.out::println);

	}


}
