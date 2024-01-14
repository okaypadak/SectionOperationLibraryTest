package dev.padak.backend.repository;

import dev.padak.backend.Entity.PersonEntity;
import dev.padak.backend.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Section(1000)
public interface PersonRepository extends CrudRepository<PersonEntity, Long> {



}