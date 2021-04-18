package io.zipcoder.crudapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {

    private PersonRepository personRepository;

    @Autowired
    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @RequestMapping(value = "/people", method = RequestMethod.POST)
    public Person createPerson(@RequestBody Person p) {
        return this.personRepository.save(p);
    }

    @RequestMapping(value = "/people/{id}", method = RequestMethod.GET)
    public Person getPerson(@PathVariable int id) {
        return this.personRepository.findOne(id);
    }

    @RequestMapping(value = "/people", method = RequestMethod.GET)
    public Iterable<Person> getPersonList() {
        return this.personRepository.findAll();
    }

    @RequestMapping(value = "/people/", method = RequestMethod.PUT)
    public Person updatePerson(@RequestBody Person p) {
        int id = p.getId();
        Person originalPerson = this.personRepository.findOne(id);
        originalPerson.setFirstName(p.getFirstName());
        originalPerson.setLastName(p.getLastName());
        return this.personRepository.save(originalPerson);
    }

    @RequestMapping(value = "/people/{id}", method = RequestMethod.DELETE)
    public void DeletePerson(@PathVariable int id) {
        this.personRepository.delete(id);
    }


}
