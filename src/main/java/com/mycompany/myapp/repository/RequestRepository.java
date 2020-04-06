package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Request;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Request entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RequestRepository extends MongoRepository<Request, String> {
}
