package com.app;


import org.springframework.data.jpa.repository.JpaRepository;

public interface MensajeDAO extends JpaRepository<Mensaje, Integer> {

}
