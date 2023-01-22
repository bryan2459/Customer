package bryan.springframework.controllers;

import bryan.springframework.Services.CustomerService.CustomerService;
import bryan.springframework.commands.PersonCommand;
import bryan.springframework.domain.Person;
import bryan.springframework.exceptions.NotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import static org.springframework.web.servlet.function.RequestPredicates.param;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
public class PersonControllerTest {

    @Mock
    CustomerService customerService;

    PersonController controller;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        controller = new PersonController(customerService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new ControllerExceptionHandler())
                .build();
    }

    @Test
    public void testGetRecipe() throws Exception {

        Person person = new Person();
        person.setId(1L);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        when(customerService.findById(anyLong())).thenReturn(person);

        mockMvc.perform(get("/person/1/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("person/show"))
                .andExpect(model().attributeExists("person"));
    }

    @Test
    public void testGetPersonNotFound() throws Exception {

        when(customerService.findById(anyLong())).thenThrow(NotFoundException.class);

        mockMvc.perform(get("/person/1/show"))
                .andExpect(status().isNotFound())
                .andExpect(view().name("404error"));
    }

    @Test
    public void testGetPersonNumberFormatException() throws Exception {

        //when(customerService.findById(anyLong())).thenThrow(NotFoundException.class);

        mockMvc.perform(get("/person/sss1/show"))
                .andExpect(status().isBadRequest())
                .andExpect(view().name("400error"));
    }
    @Test
    public void testPostNewRecipeForm() throws Exception {
        PersonCommand command = new PersonCommand();
        command.setId(2L);

        when(customerService.savePersonCommand(any())).thenReturn(command);

        mockMvc.perform(post("/person")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("Name", "some string")
                        .param("Address", "some directions")
               )


                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/person/2/show"));
    }


}