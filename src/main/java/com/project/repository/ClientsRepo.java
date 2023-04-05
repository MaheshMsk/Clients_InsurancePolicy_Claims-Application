package com.project.repository;

import com.project.model.Clients;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientsRepo extends JpaRepository<Clients,Long> {

}
