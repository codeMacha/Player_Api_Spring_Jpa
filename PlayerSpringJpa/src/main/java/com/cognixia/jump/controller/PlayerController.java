package com.cognixia.jump.controller;

import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.model.Player;
import com.cognixia.jump.service.PlayerService;

@RequestMapping("/api")
@RestController
public class PlayerController {

	@Autowired
	PlayerService serv;
	
	@GetMapping("/player")
	public List<Player> getPlayers(){
		return serv.getAllPlayer();
	}
	
	@GetMapping("/player/{id}")
	public ResponseEntity<?> getPlayer(@PathVariable int id){
		
		Optional<Player> found = serv.getPlayerById(id);
		
		if(found == null) {
			return ResponseEntity.status(404).body("player id: "+ id + " was not found");
		}
		
		return ResponseEntity.status(200).body(found);
	}
	
	@PutMapping("/player")
	public ResponseEntity<?> updatePlayer(@RequestBody Player player){
		
		Player updated = serv.updatePlayer(player);
		
		if(updated == null) {
			return ResponseEntity.status(404).body("player with id: " + player.getId()+ " doesnt exist");
		}
		
		return ResponseEntity.status(200).body(updated);
	}
	
	@PostMapping("/player")
	public ResponseEntity<?> createPlayer(@RequestBody Player player){
		
		if(player.getSport() == "") {
			return ResponseEntity.status(404).body("Player needs a sport");
		}
		
		Player newplayer = serv.addPlayer(player);
		return ResponseEntity.status(202).body(newplayer);
		
	}
	
	@GetMapping("/player/sport/{sport}")
	public List<Player> getAllBySport(@PathVariable String sport){
		return serv.getPlayersBySport(sport);
	}
	
	
	@DeleteMapping("/player/{id}")
	public ResponseEntity<?> deletePlayer(@PathVariable int id){
		if(serv.deletePlayer(id)) {
			return ResponseEntity.status(200).body("successfully deleted");
		}
		return ResponseEntity.status(404).body("player with id: " + id+" doesnt exist");
	}
	
	@PatchMapping("/player/update/sport")
	public ResponseEntity<?> updateSport(@PathParam(value="id") int id, @PathParam(value="sport") String sport){
		
		Player updated = serv.updateSport(id, sport);
		
		if(updated == null) {
			return ResponseEntity.status(404).body("not found");
		}
		
		return ResponseEntity.status(202).body(updated);
	}

	
}
