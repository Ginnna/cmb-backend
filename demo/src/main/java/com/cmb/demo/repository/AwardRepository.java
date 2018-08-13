package com.cmb.demo.repository;

import com.cmb.demo.domain.Award;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AwardRepository extends JpaRepository<Award, String> {
}
