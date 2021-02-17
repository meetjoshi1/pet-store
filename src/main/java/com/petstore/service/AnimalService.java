package com.petstore.service;

import com.petstore.dto.AnimalDTO;
import com.petstore.model.Animal;
import com.petstore.repository.AnimalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnimalService {

    private AnimalRepository animalRepository;

    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    private Animal mapTo(AnimalDTO animalDTO) {
        return new Animal(animalDTO.getShelternateId(), animalDTO.getAnimalName(), animalDTO.getSpecies(),
                animalDTO.getBirthDate(), animalDTO.getSex(), animalDTO.getColor());
    }

    private AnimalDTO mapToDto(Animal animal) {
        return new AnimalDTO(animal.getId(),animal.getShelternateId(), animal.getAnimalName(), animal.getSpecies(),
                animal.getBirthDate(), animal.getSex(), animal.getColor());
    }


    public List<AnimalDTO> getAnimals() {
        return animalRepository.findAll().stream().map(animal -> mapToDto(animal)).collect(Collectors.toList());
    }

    public List<AnimalDTO> addAnimals(List<AnimalDTO> animals) {

        List<Animal> animalList = animals.stream().map(animalDto -> mapTo(animalDto)).collect(Collectors.toList());
        animalList = animalRepository.saveAll(animalList);
        List<AnimalDTO> animalsDtos = animalList.stream().map(animal -> mapToDto(animal)).collect(Collectors.toList());
        return animalsDtos;
    }


}
