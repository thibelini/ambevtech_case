package com.ambevtech.core.repository;

import com.ambevtech.core.entity.Cidade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CidadeRepository extends JpaRepository<Cidade, Integer> {

    Cidade findByCodigoCidade(Integer CodigoCidade);

    @Query("select cid from Cidade cid where cid.nome like %:nome% order by cid.nome")
    Page<Cidade> findAllOrderByNome(String nome, Pageable pageable);

}
