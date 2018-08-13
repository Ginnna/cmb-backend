package com.cmb.demo.repository;

import com.cmb.demo.domain.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecordRepository extends JpaRepository<Record, String> {
    List<Record> findByUser(String User);
}
