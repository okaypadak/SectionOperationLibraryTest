package dev.padak.backend;

import com.github.javafaker.Faker;
import dev.padak.backend.service.DataService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import dev.padak.backend.Entity.PersonEntity;
import dev.padak.backend.repository.PersonRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@SpringBootTest
class BackendApplicationTests {


	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private DataService dataService;
	@Mock
	private PersonRepository personRepositoryM;

	@InjectMocks
	private DataService dataServiceM;

	@Test
	void testYakalaBunu() {

		int beforeSaveSize = 0;
		int afterSaveSize = 0;
		Boolean hataOldu = false;
		List<PersonEntity> beforeSavePersons = (List<PersonEntity>) personRepository.findAll();

		// personRepository.saveAll metodu çağrıldığında fakePersonList'i döndürmesini sağla
		//when(personRepository.saveAll(any())).thenReturn(fakePersonList);

		beforeSaveSize = beforeSavePersons.size();

		try {
			dataService.yakalaBunu();

		}catch (Exception e) {
			hataOldu = true;
			System.out.println(e.getMessage());

		}

		List<PersonEntity> afterSavePersons = (List<PersonEntity>) personRepository.findAll();
		afterSaveSize = afterSavePersons.size();

		if(hataOldu) {
			assertEquals(beforeSaveSize, afterSaveSize);
		} else {
			assertEquals(beforeSaveSize + 100001, afterSaveSize);
		}


		// personRepository.saveAll metodu bir kez çağrıldı mı?
		//verify(personRepository, times(1)).saveAll(any());

		// personRepository.save metodu bir kez çağrıldı mı?
		//verify(personRepository, times(1)).save(any());

	}

	@Test
	public void testYakalaBunuM() {

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


		when(personRepositoryM.saveAll(personList)).thenReturn(personList);

		PersonEntity person = new PersonEntity();
		person.setAd(faker.name().firstName());
		person.setSoyad(faker.name().lastName());
		person.setDogumTarihi(faker.date().birthday());
		person.setCinsiyet(faker.options().option("Erkek", "Kadın"));

		when(personRepositoryM.save(person)).thenReturn(person);

		// Act
		dataServiceM.yakalaBunu();

        List<PersonEntity> list = new ArrayList<>(personList);
		list.add(person);

		when(personRepositoryM.findAll()).thenReturn(list);

		assertEquals(100001, list.size());

		verify(personRepositoryM, times(1)).saveAll(anyList());
		verify(personRepositoryM, times(1)).save(any());
	}

}
