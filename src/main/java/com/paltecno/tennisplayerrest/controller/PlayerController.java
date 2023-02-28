package com.paltecno.tennisplayerrest.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.paltecno.tennisplayerrest.model.Player;
import com.paltecno.tennisplayerrest.service.PlayerService;

@RestController
public class PlayerController {

	@Autowired
	PlayerService service;

	@GetMapping("/welcome")
	public String welcome() {
		return "Tennis Player REST API";
	}

	@GetMapping("/players")
	// @RequestMapping(RequestMethod.get)
	public List<Player> getALLplayers() {
		return service.getAllPlayers();
	}

	@GetMapping("/players/{id}")
	public Player getPlayer(@PathVariable int id) {
		return service.getPlayer(id);
	}

	@PostMapping("/players")
	// @RequestMapping(method=RequestMethod.POST)
	public Player addPlayer(@RequestBody Player player) {
		player.setId(0);
		return service.addPlayer(player);
	}

	@PutMapping("/players/{id}")
	// @RequestMapping(method=RequestMethod.PUT)
	public Player updatePlayer(@PathVariable int id, @RequestBody Player p) {
		return service.updatePlayer(id, p);
	}

	@PatchMapping("/players/{id}")
	// RequestMapping(method=RequestMethod.PATCH)
	public Player partialUpdate(@PathVariable int id, @RequestBody Map<String, Object> platerPatch) {
		return service.patch(id, platerPatch);
	}

	@PatchMapping("/players/{id}/titles")
	public void updateTitles(@PathVariable int id, @RequestBody int titles) {
		service.updateTitles(id, titles);
	}

	@PatchMapping("/players/{id}/name")
	public void updateNames(@PathVariable int id, @RequestBody String name) {
		service.updateNames(id, name);
	}

	@DeleteMapping("/players/{id}")
	// RequestMapping(method=RequestMethod.DELETE)
	public void deletePlayer(@PathVariable int id) {
		service.deletePlayer(id);
	}

}
