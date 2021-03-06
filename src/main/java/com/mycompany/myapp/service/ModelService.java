package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link Model}.
 */
public interface ModelService {

    /**
     * Save a model.
     *
     * @param model the entity to save.
     * @return the persisted entity.
     */
    Model save(Model model);

    /**
     * Get all the models.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Model> findAll(Pageable pageable);

    /**
     * Get the "id" model.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Model> findOne(String id);

    /**
     * Delete the "id" model.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
