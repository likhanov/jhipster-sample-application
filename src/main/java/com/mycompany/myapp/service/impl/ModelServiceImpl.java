package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.ModelService;
import com.mycompany.myapp.domain.Model;
import com.mycompany.myapp.repository.ModelRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Model}.
 */
@Service
public class ModelServiceImpl implements ModelService {

    private final Logger log = LoggerFactory.getLogger(ModelServiceImpl.class);

    private final ModelRepository modelRepository;

    public ModelServiceImpl(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    /**
     * Save a model.
     *
     * @param model the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Model save(Model model) {
        log.debug("Request to save Model : {}", model);
        return modelRepository.save(model);
    }

    /**
     * Get all the models.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    public Page<Model> findAll(Pageable pageable) {
        log.debug("Request to get all Models");
        return modelRepository.findAll(pageable);
    }

    /**
     * Get one model by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    public Optional<Model> findOne(String id) {
        log.debug("Request to get Model : {}", id);
        return modelRepository.findById(id);
    }

    /**
     * Delete the model by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Model : {}", id);
        modelRepository.deleteById(id);
    }
}
