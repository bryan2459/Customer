package bryan.springframework.controllers;

import bryan.springframework.Services.CustomerService.CustomerService;
import bryan.springframework.domain.Person;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


public class IndexControllerTest {

    @Mock
    CustomerService customerService;

    @Mock
    Model model;

    IndexController indexController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        indexController = new IndexController(customerService);
    }

    @Test
    public void getIndexPage() {
        //given
        Set<Person> persons = new HashSet<>();
        persons.add(new Person());

        Person person = new Person();
        person.setId(1L);

        persons.add(person);

        when(customerService.getCustomers()).thenReturn(persons);

        ArgumentCaptor<Set<Person>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

        //when
        String viewName = indexController.getIndexPage(model);


        //then
        assertEquals("index", viewName);
        verify(customerService, times(1)).getCustomers();
        verify(model, times(1)).addAttribute(eq("persons"), argumentCaptor.capture());
        Set<Person> setInController = argumentCaptor.getValue();
        assertEquals(2, setInController.size());

    }

    @Test
    public void testMockMvc() throws Exception {
        MockMvc  mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();
        mockMvc.perform(get("/")).andExpect(status().isOk())
                .andExpect(view().name("index"));

    }
}