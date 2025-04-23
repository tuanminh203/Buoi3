package com.vti.backend.datalayer;

import com.vti.entity.Product;
import com.vti.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class ProductReponsitory implements IProductReponsitory {
    @Override
    public List<Product> getAllProducts() {
        try (Session session = HibernateUtils.openSession()) {
            Query<Product> query = session.createQuery("FROM Product", Product.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    @Override
    public Product getProductById(int id) {
        try (Session session = HibernateUtils.openSession()) {
            return session.get(Product.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void addProduct(Product product) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.openSession()) {
            transaction = session.beginTransaction();
            session.persist(product);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void updateProduct(Product product) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.openSession()) {
            transaction = session.beginTransaction();
            session.merge(product);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void deleteProduct(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.openSession()) {
            transaction = session.beginTransaction();
            Product product = session.get(Product.class, id);
            if (product != null) {
                session.remove(product);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> searchProductsByName(String name) {
        try (Session session = HibernateUtils.openSession()) {
            Query<Product> query = session.createQuery("FROM Product WHERE productName LIKE :name", Product.class);
            query.setParameter("name", "%" + name + "%");
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    @Override
    public List<Product> searchProductsByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        try (Session session = HibernateUtils.openSession()) {
            Query<Product> query = session.createQuery("FROM Product WHERE price >= :minPrice AND price <= :maxPrice", Product.class);
            query.setParameter("minPrice", minPrice);
            query.setParameter("maxPrice", maxPrice);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    @Override
    public List<Product> searchProductsByDateRange(LocalDate startDate, LocalDate endDate) {
        try (Session session = HibernateUtils.openSession()) {
            Query<Product> query = session.createQuery("FROM Product WHERE importDate >= :startDate AND importDate <= :endDate", Product.class);
            query.setParameter("startDate", startDate);
            query.setParameter("endDate", endDate);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }
}
