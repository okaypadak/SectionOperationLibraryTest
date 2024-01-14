package dev.padak.backend.service;

import com.github.javafaker.Faker;
import dev.padak.backend.Entity.PersonEntity;
import dev.padak.backend.repository.PersonRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DataService {

    @Autowired
    private PersonRepository personRepository;


    @Transactional
    public void yakalaBunu(){

        System.out.println("Başladı");

        Faker faker = new Faker();
        List<PersonEntity> personList = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            PersonEntity person = new PersonEntity();
            person.setAd(faker.name().firstName());
            person.setSoyad(faker.name().lastName());
            person.setDogumTarihi(faker.date().birthday());
            person.setCinsiyet(faker.options().option("Erkek", "Kadın"));

            personList.add(person);
        }

        System.out.println("Kaydetme başladı");
        personRepository.saveAll(personList);

        PersonEntity person = new PersonEntity();
        person.setAd(faker.name().firstName());
        person.setSoyad(faker.name().lastName());
        person.setDogumTarihi(faker.date().birthday());
        person.setCinsiyet(faker.options().option("Erkek", "Kadın"));

        personRepository.save(person);

        //throw new RuntimeException("Rollback testi için örnek exception");

        System.out.println("Bitti");


    }

}
