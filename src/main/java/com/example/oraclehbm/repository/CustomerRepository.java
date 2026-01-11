package com.example.oraclehbm.repository;

import com.example.oraclehbm.model.DimCustomer;
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
public class CustomerRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public CustomerRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public Optional<DimCustomer> findById(Long id) {
        DimCustomer customer = getCurrentSession().get(DimCustomer.class, id);
        return Optional.ofNullable(customer);
    }

    public List<DimCustomer> findAll() {
        Query<DimCustomer> query = getCurrentSession()
                .createQuery("FROM DimCustomer ORDER BY customerId", DimCustomer.class);
        return query.getResultList();
    }

    public List<DimCustomer> findTop(int limit) {
        Query<DimCustomer> query = getCurrentSession()
                .createQuery("FROM DimCustomer ORDER BY customerId", DimCustomer.class);
        query.setMaxResults(limit);
        return query.getResultList();
    }

    public List<DimCustomer> findByRegion(String region) {
        Query<DimCustomer> query = getCurrentSession()
                .createQuery("FROM DimCustomer WHERE region = :region ORDER BY customerId", DimCustomer.class);
        query.setParameter("region", region);
        return query.getResultList();
    }

    public List<DimCustomer> findBySegment(String segment) {
        Query<DimCustomer> query = getCurrentSession()
                .createQuery("FROM DimCustomer WHERE segment = :segment ORDER BY customerId", DimCustomer.class);
        query.setParameter("segment", segment);
        return query.getResultList();
    }

    public List<DimCustomer> findByRegionAndSegment(String region, String segment) {
        Query<DimCustomer> query = getCurrentSession()
                .createQuery("FROM DimCustomer WHERE region = :region AND segment = :segment ORDER BY customerId", DimCustomer.class);
        query.setParameter("region", region);
        query.setParameter("segment", segment);
        return query.getResultList();
    }

    @Transactional
    public DimCustomer save(DimCustomer customer) {
        getCurrentSession().saveOrUpdate(customer);
        return customer;
    }

    @Transactional
    public void delete(Long id) {
        DimCustomer customer = getCurrentSession().get(DimCustomer.class, id);
        if (customer != null) {
            getCurrentSession().delete(customer);
        }
    }

    public long count() {
        Query<Long> query = getCurrentSession()
                .createQuery("SELECT COUNT(c) FROM DimCustomer c", Long.class);
        return query.uniqueResult();
    }
}
