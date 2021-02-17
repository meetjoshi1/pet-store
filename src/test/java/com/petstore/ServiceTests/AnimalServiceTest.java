package com.petstore.ServiceTests;

import com.petstore.dto.AnimalDTO;
import com.petstore.model.Animal;
import com.petstore.repository.AnimalRepository;
import com.petstore.service.AnimalService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import java.util.ArrayList;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


import static org.mockito.Mockito.when;



@ExtendWith(MockitoExtension.class)
public class AnimalServiceTest {

    @Mock
    AnimalRepository animalRepository;

    @InjectMocks
    AnimalService animalService;

    @Test
    public void getAllAnimals(){


        Animal animal1=new Animal("101","Lion1","species", LocalDate.of(2015,12,27)
                , "Male","Gold", false, null);

        Animal animal2=new Animal("102","Lion1","species", LocalDate.of(2015,12,27)
                , "Male","Gold", false, null);

        List<Animal> expected=new ArrayList<>();
        expected.add(animal1);
        expected.add(animal2);

        when(animalRepository.findAll()).thenReturn(expected);

        List<AnimalDTO> actual=animalService.getAnimals();


        verify(animalRepository, times(1)).findAll();

        assertEquals(expected.get(0).getShelternateId(),actual.get(0).getShelternateId());
        assertEquals(expected.get(0).getAnimalName(),actual.get(0).getAnimalName());
        assertEquals(expected.get(0).getBirthDate(),actual.get(0).getBirthDate());
        assertEquals(expected.get(0).getColor(),actual.get(0).getColor());
        assertEquals(expected.get(0).getSex(),actual.get(0).getSex());
        assertEquals(expected.get(0).getSpecies(),actual.get(0).getSpecies());


        assertEquals(expected.size(),actual.size());


    }


    @Test
    public void addAnimals() throws Exception {
        List<AnimalDTO> animalsDto = List.of(
                new AnimalDTO(1l,"1","cat1","CAT", LocalDate.of(2015,03,23),"FEMALE","BLACK",false, null),
                new AnimalDTO(2l, "2","cat2","CAT",LocalDate.of(2016,03,23),"MALE","BROWN",false, null),
                new AnimalDTO(3l, "3","dog1","DOG",LocalDate.of(2017,03,23),"FEMALE","YELLOW",false, null),
                new AnimalDTO(4l,"4","dog4","DOG", LocalDate.of(2015,03,23),"MALE","WHITE",false, null),
                new AnimalDTO(5l, "5","bird","BIRD", LocalDate.of(2015,03,23),"FEMALE","GREEN",false, null)
        );

        List<Animal> animalsEntities = List.of(
                new Animal("1","cat1","CAT", LocalDate.of(2015,03,23),"FEMALE","BLACK",false, null),
                new Animal("2","cat2","CAT",LocalDate.of(2016,03,23),"MALE","BROWN",false, null),
                new Animal("3","dog1","DOG",LocalDate.of(2017,03,23),"FEMALE","YELLOW",false, null),
                new Animal("4","dog4","DOG", LocalDate.of(2015,03,23),"MALE","WHITE",false, null),
                new Animal("5","bird","BIRD", LocalDate.of(2015,03,23),"FEMALE","GREEN",false, null)
        );
        when(animalRepository.saveAll(animalsEntities)).thenReturn(animalsEntities);

        List<AnimalDTO> actual = animalService.addAnimals(animalsDto);

        assertEquals(animalsDto, actual);
    }

    @Test
    public void returnAnimalToShelter(){

        animalService.removeAnimals(List.of("101"));
        verify(animalRepository, times(1)).deleteAnimalByShelternateId("101");

    }
}
