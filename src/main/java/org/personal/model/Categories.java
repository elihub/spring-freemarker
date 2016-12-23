package org.personal.model;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author elida
 */
@Transactional
public interface Categories {

    List<Category> getAll();

    void create(Category category);

    void update(Category category);

    void delete(Long id);
}
