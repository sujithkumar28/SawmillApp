package com.sawmill.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sawmill.model.Sawmill;

@Repository
public interface SawmillDao extends JpaRepository<Sawmill, Integer>{
	
	Optional<Sawmill> findByName(String name);

}
