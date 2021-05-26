package br.com.fiap.infinitumspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.infinitumspring.entity.CountyEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface CountyRepository extends JpaRepository<CountyEntity, Long>{

}