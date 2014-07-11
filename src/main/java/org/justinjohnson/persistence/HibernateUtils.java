package org.justinjohnson.persistence;

import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * @author johnsonj
 * @version 20140711 johnsonj
 */
public final class HibernateUtils {
	private static final SessionFactory sessionFactory;

	// There is no need to lazily initialize this singleton as it will always be used in the application at least once
	static {
		final Configuration configuration = new Configuration();

		configuration.configure();

		final Properties properties = configuration.getProperties();
		final ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(properties).build();

		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	}

	public static final SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static final void save(final Object object) {
		final Session session = sessionFactory.openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();
			
			session.save(object);
			transaction.commit();
		} catch (final HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			
			// TODO: Add logging
			throw e;
		} finally {
			session.close();
		}
	}
}
