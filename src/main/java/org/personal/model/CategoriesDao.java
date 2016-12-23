/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.personal.model;

import java.util.List;
import javax.inject.Inject;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 *
 * @author elida
 */

@Repository
@SuppressWarnings("unused")
public class CategoriesDao implements Categories {

    private final SessionFactory sessionFactory;

    @Inject
    public CategoriesDao(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<Category> getAll() {
        final Session currentSession = getCurrentSession();
        final Query query = currentSession.createQuery("from Category");
        return query.list();
    }

    @Override
    public void create(Category category) {
        getCurrentSession().persist(category);
    }

    @Override
    public void update(Category category) {
        final Session currentSession = getCurrentSession();
        currentSession.update(category);
    }

    @Override
    public void delete(Long id) {
        final Session currentSession = getCurrentSession();
        final Category category = getById(id, currentSession);
        currentSession.delete(category);
    }

    private Category getById(final Long id, final Session currentSession) {
        final Criteria criteria = currentSession.createCriteria(Category.class);
        criteria.add(Restrictions.eq("id", id));
        return (Category) criteria.uniqueResult();
    }

}
