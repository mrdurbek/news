package com.example.news.repositry;

import com.example.news.entity.Lavozim;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface LavozimRepository extends JpaRepository<Lavozim,Long> {
    Optional<Lavozim> findByName(String name);
    boolean existsByName(String name);

}
