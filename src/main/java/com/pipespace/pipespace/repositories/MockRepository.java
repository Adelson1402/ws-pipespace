package com.pipespace.pipespace.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pipespace.pipespace.requests.MockRequest;

@Repository
public interface MockRepository extends JpaRepository<MockRequest, Long> {

	public MockRequest findMockRequestBySection(String section);
}
