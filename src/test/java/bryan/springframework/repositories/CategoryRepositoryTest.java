package bryan.springframework.repositories;

import bryan.springframework.domain.Category;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CategoryRepositoryTest {
    @Autowired
    CategoryRepository categoryRepository;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void findByDescription() {

        Optional<Category> categoryOptional =  categoryRepository.findByDescription(">101Kand<150K");
        assertEquals(">101Kand<150K",categoryOptional.get().getDescription());


    }
}