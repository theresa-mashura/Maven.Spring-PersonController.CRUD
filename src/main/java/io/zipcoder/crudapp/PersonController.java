package io.zipcoder.crudapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class PersonController {

    private PersonRepository personRepository;

    @Autowired
    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @RequestMapping(value = "/people", method = RequestMethod.POST)
    public ResponseEntity<Person> createPerson(@RequestBody Person p) {
        return new ResponseEntity<>(this.personRepository.save(p), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/people/{id}", method = RequestMethod.GET)
    public ResponseEntity<Person> getPerson(@PathVariable int id) {
        return new ResponseEntity<> (this.personRepository.findOne(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/people", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Person>> getPersonList() {
        return new ResponseEntity<> (this.personRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/people/", method = RequestMethod.PUT)
    public ResponseEntity<Person> updatePerson(@RequestBody Person p) {
        int id = p.getId();
        if (this.personRepository.findOne(id) != null){
            Person originalPerson = this.personRepository.findOne(id);
            originalPerson.setFirstName(p.getFirstName());
            originalPerson.setLastName(p.getLastName());
            return new ResponseEntity<>(this.personRepository.save(originalPerson), HttpStatus.OK);
        } else {
            this.createPerson(p);
        }
        return null;
    }

    @RequestMapping(value = "/people/{id}", method = RequestMethod.DELETE)
    public ResponseEntity DeletePerson(@PathVariable int id) {
        this.personRepository.delete(id);
        return new ResponseEntity<> (HttpStatus.NO_CONTENT);
    }


}
