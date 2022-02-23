package cg.repository.impl;

import cg.model.Product;
import cg.repository.IProductRepository;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;

public class ProductRepositoryImpl implements IProductRepository {
    private static SessionFactory sessionFactory;
    private static EntityManager entityManager;

    static {
        try {
            sessionFactory = new Configuration().configure("hibernate.conf.xml").buildSessionFactory();
            entityManager = sessionFactory.createEntityManager();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Product> findAll() {
        String queryStr = "SELECT p from Product as p WHERE p.id = :id";
        TypedQuery<Product> query = entityManager.createQuery(queryStr, Product.class);
        return (ArrayList<Product>) query.getResultList();
    }

    @Override
    public Product saveProduct(Product product) {
        Transaction transaction = null;
        Product origin;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            if (product.getId() != 0) {
                origin = findProductById(product.getId());
                origin.setPrice(product.getPrice());
                origin.setDescription(product.getDescription());
                origin.setImage(product.getImage());
            } else {
                origin = product;
            }
            session.saveOrUpdate(origin);
            transaction.commit();
            return origin;
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return null;
    }

    @Override
    public Product deleteProduct(int id) {
        Transaction transaction = null;
        Product origin = findProductById(id);
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            if (origin != null) {
                session.delete(origin);
            }
            transaction.commit();
            return origin;
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return null;
    }

    @Override
    public Product findProductByName(String name) {
        String queryStr = "SELECT p FROM Product AS p WHERE p.name = :name";
        TypedQuery<Product> query = entityManager.createQuery(queryStr, Product.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }

    @Override
    public Product findProductById(int id) {
        return null;
    }
}
