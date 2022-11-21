package com.webservice.usermanagement.dao;

import java.util.List;

public interface Dao<E> {
    /**
     * Inserts a new record
     * <p></p>
     * @param entity instance of entity class
     */
    void create(E entity);

    /**
     * Updates a record
     * <p></p>
     * @param entity instance of entity class
     */
    void update(E entity, String id);

    /**
     * Deletes a record
     * <p></p>
     * @param entity instance of entity class
     */
    void remove(E entity);

    /**
     * Selects all records
     * <p></p>
     * @return list of records
     */
    List<E> findAll();

    /**
     * Searches for a record by id
     * <p></p>
     * @param id id
     * @return instance of entity class
     */
    E findById(String id);
}
