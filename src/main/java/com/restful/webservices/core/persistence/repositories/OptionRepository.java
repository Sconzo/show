package com.restful.webservices.core.persistence.repositories;

import com.restful.webservices.core.domain.entities.OptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OptionRepository extends JpaRepository<OptionEntity, Long> {
    OptionEntity findAllByQuestionId(Long id);
}
