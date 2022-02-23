package com.cognixia.jump.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognixia.jump.model.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer>{
	
	public List<Player> findBySport(String sport);
	
}
