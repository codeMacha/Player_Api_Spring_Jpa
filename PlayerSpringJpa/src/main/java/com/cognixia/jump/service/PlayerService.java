package com.cognixia.jump.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognixia.jump.model.Player;
import com.cognixia.jump.repository.PlayerRepository;

@Service
public class PlayerService {

	@Autowired
	PlayerRepository repo;
	
	public Player addPlayer(Player player) {
		player.setId(null);
		
		Player newplayer = repo.save(player);
		
		return newplayer;
	}
	
	// returns all players
	public List<Player> getAllPlayer(){
		return repo.findAll();
	}
	
	// find player by their id
	public Optional<Player> getPlayerById(int id){
		Optional<Player> player =  repo.findById(id);
		return player;
	}
	
	// update the player if it exist else null
	public Player updatePlayer(Player player){
		
		if(repo.existsById(player.getId())) {
			Player updated = repo.save(player);
			return updated;
		}
		return null;
		
	}
	
	// get players by their sport
	public List<Player> getPlayersBySport(String sport){
		return repo.findBySport(sport);
	}
	
	// remove player by id
	public boolean deletePlayer(int id) {
	
		if(repo.existsById(id)) {
			 repo.deleteById(id);
			 return true;
		}
		
		return false;	
	}
	
	// update the only sport of the player
	public Player updateSport(int id, String sport) {
		Optional<Player> found = repo.findById(id);
		
		if(found.isEmpty()) {
			return null;
		}
		
		Player toupdate = found.get();
		
		toupdate.setSport(sport);
		
		Player updated = repo.save(toupdate);
		return updated;
	}
}
