package org.project.repository;

import org.project.entity.Filme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FilmeRepository extends JpaRepository<Filme, Long> {

    @Query("SELECT f.producers FROM Filme f WHERE f.winner = true group by f.producers having count(f) > 1 ")
    List<String> findWinners();

    @Query("SELECT f.year FROM Filme f where f.producers = ?1 order by f.year asc")
    List<Integer> findYears(String s);
}
