package br.edu.ifpe.jpa.example;

import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.annotation.Generated;
import javax.naming.LimitExceededException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.eclipse.persistence.internal.queries.SortedCollectionContainerPolicy;
import org.jinq.jpa.JinqJPAStreamProvider;

import br.edu.ifpe.jpa.example.entities.Blog;
import br.edu.ifpe.jpa.example.entities.Car;
import br.edu.ifpe.jpa.example.entities.Post;

public class App {

	static EntityManagerHelper helper = EntityManagerHelper.getInstance();

	public static void main(String[] args) {
		helper.execute(Car.class, streams -> {
			streams
			.where(c -> c.getPrice() >= 3000)
			.sortedBy(c -> c.getName())
			.toList()
			.stream()
			.forEach(System.out::println);
		});

	}


	// 1. Imprima na tela todos os blogs que possuem o id maior que 10
	public void questaoUm() {
		helper.execute(Blog.class, streams -> {
			streams
			.where(c -> c.getIdentifier() > 10).toList().stream().forEach(System.out::println);
		});


	}

	// 2. Imprima na tela a descrição do blog que possui o nome "dia a dia, bit a bit"
	public void questaoDois() {
		helper.execute(Blog.class, streams -> {
			streams
			.where(c -> c.getName().equals("dia a dia, bit a bit")).toList().stream()
			.forEach(c-> System.out.println(c.getDescription()));
		});


	}

	// 3. Imprima na tela as decrições dos 5 primeiros blogs criados (considerar o atributo creationDate)
	public void questaoTres() {
		helper.execute(Blog.class, streams -> {
			streams
			.sortedDescendingBy(c-> c.getCreationDate()).limit(5).toList().stream()
			.forEach(c->System.out.println(c.getDescription()));
		});

	}

	// 4. Imprima na tela o título e conteúdo de todos os posts do blog com título recebido como parâmetro, 
	//ordenados alfabeticamente pelo título do post
	public void questaoQuatro(String titulo) {
		helper.execute(Post.class, streams -> {
			streams
			.where(c-> c.getTitle().equals(titulo)).sortedBy(c->c.getTitle()).toList()
			.stream().forEach(c-> System.out.println(c.getTitle() + c.getContent()));
		});

	}

	// 5. Imprima na tela o título do último post do blog com título "título"
	public void questaoCinco(String titulo) {
		helper.execute(Post.class, streams -> {
			streams
			.where(c-> c.getTitle().equals(titulo)).sortedBy(c->c.getTitle()).toList()
			.stream().forEach(c-> System.out.println(c.getTitle() + c.getContent()));
		});


	}

	// 6. Retorne uma lista com os títulos de todos os posts publicados no blog com título tituloBlog 
	//entre o período dataInicial e dataFinal.
	public List<String> questaoSeis(Date dataInicial, Date dataFinal, String tituloBlog) {

		helper.execute(Blog.class, streams -> {
			 return List<String> questaoSeis = streams
					    .where(c->c.getName().equals(tituloBlog))
						.collect(Collectors.toList())
						.get(0)
						.getPosts()
						.stream()
						.filter(c->c.getCreationDate().before(dataFinal)&&c.getCreationDate().after(dataInicial))
						.map(p->c.getTitle())
						.collect(Collectors.toList());
		
					});
	
	}

	// 7. Imprima na tela a média de posts existentes nos blogs
	public void questaoSete() {
		throw new UnsupportedOperationException();

	}

	
}
