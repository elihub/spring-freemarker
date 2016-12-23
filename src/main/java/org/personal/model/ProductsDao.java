/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.personal.model;

import org.personal.utils.HashProvider;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
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
public class ProductsDao implements Products {

    private final SessionFactory sessionFactory;
    private final HashProvider<Product> hasherProducts;

    @Inject
    public ProductsDao(final SessionFactory sessionFactory, @Named("getHasherProducts") HashProvider<Product> hasherProducts) {
        this.sessionFactory = sessionFactory;
        this.hasherProducts = hasherProducts;
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<Product> getAll() {
        final Session currentSession = getCurrentSession();
        final Query query = currentSession.createQuery("from Product");
        return query.list();
    }

    @Override
    public void create(Product product) {
        product.setHash(hasherProducts.get(product));
        getCurrentSession().persist(product);
    }

    @Override
    public void update(Product product) {
        final Session currentSession = getCurrentSession();
        final Product oldProduct = getByHash(product.getHash(), currentSession);
        product.setId(oldProduct.getId());
        currentSession.merge(product);
    }

    @Override
    public void delete(String hash) {
        final Session currentSession = getCurrentSession();
        final Product product = getByHash(hash, currentSession);
        currentSession.delete(product);
    }

    private Product getByHash(final String hash, final Session currentSession) {
        final Criteria criteria = currentSession.createCriteria(Product.class);
        criteria.add(Restrictions.eq("hash", hash));
        return (Product) criteria.uniqueResult();
    }
}
