package com.example.oraclehbm.repository;

import com.example.oraclehbm.model.DimProduct;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public class ProductRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public ProductRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public Optional<DimProduct> findById(Long id) {
        DimProduct product = getCurrentSession().get(DimProduct.class, id);
        return Optional.ofNullable(product);
    }

    public Optional<DimProduct> findByProductCode(String productCode) {
        Query<DimProduct> query = getCurrentSession()
                .createQuery("FROM DimProduct WHERE productCode = :productCode", DimProduct.class);
        query.setParameter("productCode", productCode);
        return query.uniqueResultOptional();
    }

    public List<DimProduct> findAll() {
        Query<DimProduct> query = getCurrentSession()
                .createQuery("FROM DimProduct ORDER BY productId", DimProduct.class);
        return query.getResultList();
    }

    public List<DimProduct> findTop(int limit) {
        Query<DimProduct> query = getCurrentSession()
                .createQuery("FROM DimProduct ORDER BY productId", DimProduct.class);
        query.setMaxResults(limit);
        return query.getResultList();
    }

    public List<DimProduct> findByCategory(String category) {
        Query<DimProduct> query = getCurrentSession()
                .createQuery("FROM DimProduct WHERE category = :category ORDER BY productName", DimProduct.class);
        query.setParameter("category", category);
        return query.getResultList();
    }

    public List<DimProduct> findActiveProducts() {
        Query<DimProduct> query = getCurrentSession()
                .createQuery("FROM DimProduct WHERE activeFlag = true ORDER BY productName", DimProduct.class);
        return query.getResultList();
    }

    @Transactional
    public DimProduct save(DimProduct product) {
        getCurrentSession().saveOrUpdate(product);
        return product;
    }

    @Transactional
    public void delete(Long id) {
        DimProduct product = getCurrentSession().get(DimProduct.class, id);
        if (product != null) {
            getCurrentSession().delete(product);
        }
    }

    public long count() {
        Query<Long> query = getCurrentSession()
                .createQuery("SELECT COUNT(p) FROM DimProduct p", Long.class);
        return query.uniqueResult();
    }
}
