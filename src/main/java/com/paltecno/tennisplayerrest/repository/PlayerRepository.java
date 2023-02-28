package com.paltecno.tennisplayerrest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.paltecno.tennisplayerrest.model.Player;

public interface PlayerRepository extends JpaRepository<Player, Integer> {

	@Modifying
	@Query("update Player p set p.titles = :titles where p.id = :id")
	void updateTitles(@Param("id") int id, @Param("titles") int titles);

	@Modifying
	@Query("update Player p set p.name = :name where p.id= :id")
	void updateNames(@Param("id") int id, @Param("name") String name);
}
