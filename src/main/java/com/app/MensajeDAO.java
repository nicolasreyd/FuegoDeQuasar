package com.app;

import javax.persistence.Id;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MensajeDAO extends JpaRepository<Mensaje, Integer> {

}
