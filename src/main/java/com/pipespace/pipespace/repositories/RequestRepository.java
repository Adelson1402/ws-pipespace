package com.pipespace.pipespace.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pipespace.pipespace.requests.Request;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {

}
