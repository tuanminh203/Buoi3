package com.vti.backend.datalayer;

import com.vti.entity.Category;
import com.vti.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class CategoryReponsitory implements ICategoryReponsitory {

    @Override
    public List<Category> getAllCategories() {
        try (Session session = HibernateUtils.openSession()){
            Query<Category> query = session.createQuery("from Category", Category.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    @Override
    public Category getCategoryById(int id) {
        try (Session session = HibernateUtils.openSession()){
            Query<Category> query = session.createQuery("from Category where id = :id", Category.class);
            query.setParameter("id", id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return getCategoryById(id);
    }

    @Override
    public void addCategory(Category category) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.openSession()){
            transaction = session.beginTransaction();
            session.persist(category);
            transaction.commit();
        }catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void updateCategory(int id, String newName) {
       Transaction transaction = null;
       try (Session session = HibernateUtils.openSession()){
           transaction = session.beginTransaction();
           Category category = session.get(Category.class, id);
           if (category != null) {
               category.setCategoryName(newName);
           }
           transaction.commit();
       }catch (Exception e){
           if (transaction != null){
               transaction.rollback();
           }
           e.printStackTrace();
       }
    }

    @Override
    public void deleteCategory(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.openSession()) {
            transaction = session.beginTransaction();
            Category category = session.get(Category.class, id);
            if (category != null) {
                session.remove(category);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
