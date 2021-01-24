package com.ecommerceApp.bookstore.repository;


import com.ecommerceApp.bookstore.model.Persons;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Persons,Long> {

      @Query(value="select * from persons p where p.email= :emailId", nativeQuery=true)
      Persons findByEmail(String emailId);

      @Query(value="select * from persons p where p.person_id = :personId", nativeQuery=true)
      Persons findByPersonId(int personId);
}
