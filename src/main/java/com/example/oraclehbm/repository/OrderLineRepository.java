package com.example.oraclehbm.repository;

import com.example.oraclehbm.model.FactOrderLine;
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
public class OrderLineRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public OrderLineRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public Optional<FactOrderLine> findById(Long id) {
        FactOrderLine orderLine = getCurrentSession().get(FactOrderLine.class, id);
        return Optional.ofNullable(orderLine);
    }

    public List<FactOrderLine> findAll() {
        Query<FactOrderLine> query = getCurrentSession()
                .createQuery("FROM FactOrderLine ORDER BY orderLineId", FactOrderLine.class);
        return query.getResultList();
    }

    public List<FactOrderLine> findTop(int limit) {
        Query<FactOrderLine> query = getCurrentSession()
                .createQuery("FROM FactOrderLine ORDER BY orderLineId", FactOrderLine.class);
        query.setMaxResults(limit);
        return query.getResultList();
    }

    public List<FactOrderLine> findByOrderId(Long orderId) {
        Query<FactOrderLine> query = getCurrentSession()
                .createQuery("FROM FactOrderLine WHERE order.orderId = :orderId ORDER BY lineNumber", FactOrderLine.class);
        query.setParameter("orderId", orderId);
        return query.getResultList();
    }

    public List<FactOrderLine> findByOrderIdWithLimit(Long orderId, int limit) {
        Query<FactOrderLine> query = getCurrentSession()
                .createQuery("FROM FactOrderLine WHERE order.orderId = :orderId ORDER BY lineNumber", FactOrderLine.class);
        query.setParameter("orderId", orderId);
        query.setMaxResults(limit);
        return query.getResultList();
    }

    public List<FactOrderLine> findByProductId(Long productId) {
        Query<FactOrderLine> query = getCurrentSession()
                .createQuery("FROM FactOrderLine WHERE product.productId = :productId ORDER BY orderLineId", FactOrderLine.class);
        query.setParameter("productId", productId);
        return query.getResultList();
    }

    @Transactional
    public FactOrderLine save(FactOrderLine orderLine) {
        getCurrentSession().saveOrUpdate(orderLine);
        return orderLine;
    }

    @Transactional
    public void delete(Long id) {
        FactOrderLine orderLine = getCurrentSession().get(FactOrderLine.class, id);
        if (orderLine != null) {
            getCurrentSession().delete(orderLine);
        }
    }

    public long count() {
        Query<Long> query = getCurrentSession()
                .createQuery("SELECT COUNT(ol) FROM FactOrderLine ol", Long.class);
        return query.uniqueResult();
    }
}
