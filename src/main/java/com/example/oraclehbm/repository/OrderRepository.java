package com.example.oraclehbm.repository;

import com.example.oraclehbm.model.FactOrder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public class OrderRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public OrderRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public Optional<FactOrder> findById(Long id) {
        FactOrder order = getCurrentSession().get(FactOrder.class, id);
        return Optional.ofNullable(order);
    }

    public Optional<FactOrder> findByOrderNumber(String orderNumber) {
        Query<FactOrder> query = getCurrentSession()
                .createQuery("FROM FactOrder WHERE orderNumber = :orderNumber", FactOrder.class);
        query.setParameter("orderNumber", orderNumber);
        return query.uniqueResultOptional();
    }

    public List<FactOrder> findAll() {
        Query<FactOrder> query = getCurrentSession()
                .createQuery("FROM FactOrder ORDER BY orderId", FactOrder.class);
        return query.getResultList();
    }

    public List<FactOrder> findTop(int limit) {
        Query<FactOrder> query = getCurrentSession()
                .createQuery("FROM FactOrder ORDER BY orderId", FactOrder.class);
        query.setMaxResults(limit);
        return query.getResultList();
    }

    public List<FactOrder> findByCustomerId(Long customerId) {
        Query<FactOrder> query = getCurrentSession()
                .createQuery("FROM FactOrder WHERE customer.customerId = :customerId ORDER BY orderDate DESC", FactOrder.class);
        query.setParameter("customerId", customerId);
        return query.getResultList();
    }

    public List<FactOrder> findByStatus(String status) {
        Query<FactOrder> query = getCurrentSession()
                .createQuery("FROM FactOrder WHERE status = :status ORDER BY orderDate DESC", FactOrder.class);
        query.setParameter("status", status);
        return query.getResultList();
    }

    public List<FactOrder> findByDateRange(LocalDate startDate, LocalDate endDate) {
        Query<FactOrder> query = getCurrentSession()
                .createQuery("FROM FactOrder WHERE orderDate BETWEEN :startDate AND :endDate ORDER BY orderDate DESC", FactOrder.class);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        return query.getResultList();
    }

    @Transactional
    public FactOrder save(FactOrder order) {
        getCurrentSession().saveOrUpdate(order);
        return order;
    }

    @Transactional
    public void delete(Long id) {
        FactOrder order = getCurrentSession().get(FactOrder.class, id);
        if (order != null) {
            getCurrentSession().delete(order);
        }
    }

    public long count() {
        Query<Long> query = getCurrentSession()
                .createQuery("SELECT COUNT(o) FROM FactOrder o", Long.class);
        return query.uniqueResult();
    }
}
