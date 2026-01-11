package com.example.oraclehbm.repository;

import com.example.oraclehbm.dto.SalesByRegionCategory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Transactional(readOnly = true)
public class BenchmarkRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public BenchmarkRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    /**
     * Executes a heavy join query across FACT_ORDER_LINE, FACT_ORDER, DIM_CUSTOMER, and DIM_PRODUCT.
     * Aggregates net sales (qty * unit_price * (1 - discount_pct)) by region and category.
     *
     * @param limit maximum number of results to return
     * @return list of sales aggregations ordered by net_sales descending
     */
    public List<SalesByRegionCategory> findSalesByRegionAndCategory(int limit) {
        String hql = "SELECT c.state, p.category, " +
                "SUM(fol.quantity * fol.unitPrice * (1 - fol.discountPercent / 100)) " +
                "FROM FactOrderLine fol " +
                "JOIN fol.order fo " +
                "JOIN fo.customer c " +
                "JOIN fol.product p " +
                "GROUP BY c.state, p.category " +
                "ORDER BY SUM(fol.quantity * fol.unitPrice * (1 - fol.discountPercent / 100)) DESC";

        Query<Object[]> query = getCurrentSession().createQuery(hql, Object[].class);
        query.setMaxResults(limit);

        List<Object[]> results = query.getResultList();

        return results.stream()
                .map(row -> new SalesByRegionCategory(
                        (String) row[0],
                        (String) row[1],
                        (BigDecimal) row[2]
                ))
                .collect(Collectors.toList());
    }
}
