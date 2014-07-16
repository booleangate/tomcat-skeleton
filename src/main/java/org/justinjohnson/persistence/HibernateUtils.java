package org.justinjohnson.persistence;

import java.io.Serializable;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.justinjohnson.models.PersistableModel;

/**
 * @author johnsonj
 * @version 20140715 johnsonj
 */
public final class HibernateUtils {
	private static final Logger   logger             = LogManager.getLogger(HibernateUtils.class);

	private static final Object   sessionFactoryLock = new Object();
	private static SessionFactory sessionFactory;

	public static final SessionFactory getSessionFactory() {
		// Only enter the synchronized block if sessionFactory needs to be initialized. This will avoid locking on every
		// call by avoiding unnecessary synchronization.
		if (sessionFactory == null) {
			// Now synchronize since we know that sessionFactory _might_ be null.
			synchronized (sessionFactoryLock) {
				// If it is still null, initialize it.
				if (sessionFactory == null) {
					final Configuration configuration = new Configuration();

					configuration.configure();

					final Properties properties = configuration.getProperties();
					final ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(properties).build();

					sessionFactory = configuration.buildSessionFactory(serviceRegistry);
				}
			}
		}

		return sessionFactory;
	}

	public static final void write(final Object object) {
		final Session session = getSessionFactory().openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();

			session.saveOrUpdate(object);
			transaction.commit();
		} catch (final HibernateException e) {
			logger.error("Error while trying to write " + object, e);

			if (transaction != null) {
				transaction.rollback();
			}

			throw e;
		} finally {
			session.close();
		}
	}

	public static final Object read(final PersistableModel model) {
		return read(model.getClass(), model.getPersistenceId());
	}

	@SuppressWarnings("unchecked")
	public static final <T> T read(final Class<T> clazz, final Serializable id) {
		final Session session = getSessionFactory().openSession();

		try {
			return (T)session.get(clazz, id);
		} catch (final HibernateException e) {
			logger.error("Error while trying to delete " + clazz.getSimpleName() + " with id " + id, e);

			throw e;
		} finally {
			session.close();
		}
	}

	public static final void delete(final PersistableModel model) {
		delete(model.getClass(), model.getPersistenceId());
	}

	public static final <T> void delete(final Class<T> clazz, final Serializable id) {
		final Session session = getSessionFactory().openSession();
		Transaction transaction = null;

		try {
			// Create the delete query
			final Query query = session.createQuery("delete " + clazz.getSimpleName() + " where id = :id");
			query.setParameter("id", id);

			// ...and execute it within a transaction
			transaction = session.beginTransaction();
			query.executeUpdate();
			transaction.commit();
		} catch (final HibernateException e) {
			logger.error("Error while trying to delete " + clazz.getSimpleName() + " with id " + id, e);

			if (transaction != null) {
				transaction.rollback();
			}

			throw e;
		} finally {
			session.close();
		}
	}
}
