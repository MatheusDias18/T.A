package br.edu.ifpe.jpa.example;

import java.util.function.Consumer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.jinq.jpa.JPAJinqStream;
import org.jinq.jpa.JinqJPAStreamProvider;

public class EntityManagerHelper {

	private EntityManagerHelper() { }
	
	public static EntityManagerHelper getInstance() {
		return new EntityManagerHelper();
	}
	
	public <T> void execute(Class<T> classType, Consumer<JPAJinqStream<T>> action) {
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("MyPersicenceUnit");
		EntityManager em = fabrica.createEntityManager();

		JinqJPAStreamProvider streams = new JinqJPAStreamProvider(fabrica);

		try {
			action.accept(streams.streamAll(em, classType));
		} finally {

			em.close();
			fabrica.close();
		}
	}
}
