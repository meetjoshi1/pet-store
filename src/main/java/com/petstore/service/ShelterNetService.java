package com.petstore.service;

import com.petstore.dto.AnimalDTO;
import com.petstore.dto.AnimalReturnDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShelterNetService {

    private RestTemplate restTemplate;

    public ShelterNetService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<AnimalDTO> fetchAnimals(List<Integer> animalsIds) {
        List<AnimalDTO> animalsDto = List.of(
                new AnimalDTO("1","cat1","CAT", LocalDate.of(2015,03,23),"FEMALE","BLACK",null),
                new AnimalDTO( "2","cat2","CAT",LocalDate.of(2016,03,23),"MALE","BROWN",null),
                new AnimalDTO( "3","dog1","DOG",LocalDate.of(2017,03,23),"FEMALE","YELLOW",null),
                new AnimalDTO("4","dog4","DOG", LocalDate.of(2015,03,23),"MALE","WHITE",null),
                new AnimalDTO( "5","bird","BIRD", LocalDate.of(2015,03,23),"FEMALE","GREEN",null)
        );
        /* TO BE IMPLEMENTED */
      // String result = restTemplate.postForObject("http://localhost/add-comment", animalsIds, String.class);
       // List<AnimalDTO> actual = mapper.readValue(result, new TypeReference<List<AnimalDTO>>() {
        return animalsDto;
    }

    public HttpStatus returnAnimalToShelter(List<String> animalsIds) {
        List<AnimalReturnDto> animals=animalsIds.stream().map(id->new AnimalReturnDto(id,"")).collect(Collectors.toList());
        //HttpStatus status=restTemplate.postForObject("https://shelternet.herokuapp.com/animals/return", animals, HttpStatus.class);
        //return status;
        return HttpStatus.OK;
    }

    public HttpStatus returnSickAnimalToShelter(String shelternateId,String diagnosis) {
        List<AnimalReturnDto> animals=Arrays.asList(new AnimalReturnDto(shelternateId,diagnosis));
//        HttpStatus status=restTemplate.patchForObject("https://shelternet.herokuapp.com/animals/return",animals,HttpStatus.class);
        //return status;
        return HttpStatus.OK;
    }
}
