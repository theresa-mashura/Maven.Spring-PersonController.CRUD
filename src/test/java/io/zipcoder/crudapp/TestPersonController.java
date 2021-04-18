package io.zipcoder.crudapp;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc   // Don't start the server at all....test only layer below it, injects Spring's MockMvc for you
public class TestPersonController {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonRepository personRepository;

    @InjectMocks
    private PersonController personController;

    @Test
    public void testGetPerson() throws Exception {
        Person p = new Person(1, "Theresa", "Mashura");
        String expectedContent = "{\"firstName\":\"Theresa\",\"lastName\":\"Mashura\",\"id\":1}";

        BDDMockito
                .given(personRepository.findOne(1))
                .willReturn(p);

        this.mockMvc.perform(MockMvcRequestBuilders
            .get("/people/{id}", 1)
        )
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().string(expectedContent));
    }


    @Test
    public void testGetPersonList() throws Exception {
        Person p1 = new Person(1, "Theresa", "Mashura");
        Person p2 = new Person(2, "Joey", "Mashura");
        List<Person> people = Arrays.asList(p1, p2);
        String expectedContent = "[{\"firstName\":\"Theresa\",\"lastName\":\"Mashura\",\"id\":1},{\"firstName\":\"Joey\",\"lastName\":\"Mashura\",\"id\":2}]";

        BDDMockito
                .given(personRepository.findAll())
                .willReturn(people);

        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/people"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(expectedContent));
    }

    @Test
    public void testCreatePerson() throws Exception {
        Person p1 = new Person(1, "Mary", "Mashura");
        String expectedContent = "{\"firstName\":\"Mary\",\"lastName\":\"Mashura\",\"id\":1}";

        BDDMockito
                .given(personRepository.save(p1))
                .willReturn(p1);

        this.mockMvc.perform( MockMvcRequestBuilders
                .post("/people")
                .contentType(MediaType.APPLICATION_JSON)
                .content(expectedContent) )
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().string(expectedContent));
    }

//    @Test
//    public void testUpdatePerson() throws Exception {
//        Person p1 = new Person(1, "Mary", "Mashura");
//        Person p2 = new Person(1, "Mary", "Ward");
//        String expectedContent = "{\"firstName\":\"Mary\",\"lastName\":\"Ward\",\"id\":1}";
//
//        BDDMockito
//                .given(personRepository.save(p1))
//                .willReturn(p1);
//
//        this.mockMvc.perform( MockMvcRequestBuilders
//                .put("/people/")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(expectedContent) )
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().string(expectedContent));
//    }

}
