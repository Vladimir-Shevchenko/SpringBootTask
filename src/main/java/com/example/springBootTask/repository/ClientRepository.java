package com.example.springBootTask.repository;

import com.example.springBootTask.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    @Query("select client from Client client where client.idClient = :idClient")
    Client findClient(@Param("idClient")Integer idClient);

}
