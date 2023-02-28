package com.paltecno.tennisplayerrest.service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import com.paltecno.tennisplayerrest.exceptions.PlayerNotFoundException;
import com.paltecno.tennisplayerrest.model.Player;
import com.paltecno.tennisplayerrest.repository.PlayerRepository;

@Service
public class PlayerService {

	@Autowired
	private PlayerRepository repo;

	public List<Player> getAllPlayers() {
		return repo.findAll();
	}

	public Player getPlayer(Integer id) {
		Optional<Player> tempPlayer = repo.findById(id);
		Player p = null;
		if (tempPlayer.isPresent())
			p = tempPlayer.get();

		else
			throw new PlayerNotFoundException("Player with id {" + id + "} not found.");

		return p;

	}

	public Player addPlayer(Player p) {
		return repo.save(p);
	}

	public Player updatePlayer(int id, Player p) {
		Optional<Player> tempPlayer = repo.findById(id);

		if (tempPlayer.isEmpty())
			throw new PlayerNotFoundException("Player with id {" + id + "} not found.");

		p.setId(id);
		return repo.save(p);
	}

	public Player patch(int id, Map<String, Object> playerPatch) {
		Optional<Player> player = repo.findById(id);

		if (player.isPresent()) {
			playerPatch.forEach((key, value) -> {
				Field field = org.springframework.util.ReflectionUtils.findField(Player.class, key);
				ReflectionUtils.makeAccessible(field);
				ReflectionUtils.setField(field, player.get(), value);
			});
		}

		else {
			throw new PlayerNotFoundException("Player with id {" + id + "} not found.");
		}
		return repo.save(player.get());
	}

	@Transactional
	public void updateTitles(int id, int titles) {
		Optional<Player> tempPlayer = repo.findById(id);
		if (tempPlayer.isEmpty())
			throw new PlayerNotFoundException("Player with id {" + id + "} not found.");

		repo.updateTitles(id, titles);
	}

	@Transactional
	public void updateNames(int id, String name) {
		Optional<Player> tempPlayer = repo.findById(id);
		repo.updateNames(id, name);
		if (tempPlayer.isEmpty())
			throw new RuntimeException("Player with id {" + id + "} not found");
		repo.updateNames(id, name);
	}

	public void deletePlayer(int id) {
		Optional<Player> tempPlayer = repo.findById(id);
		if (tempPlayer.isEmpty())
			throw new PlayerNotFoundException("Player with id {" + id + "} not found.");

		repo.delete(tempPlayer.get());

	}

}
