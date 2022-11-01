package applicacao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dominio.Pessoa;

public class Programa {

	public static void main(String[] args) {
		
		// -> COMO JÁ INSTANCIAMOS E SALVAMOS ESSES OBJETOS DAS LINHAS 14,15,16,21,22 E 23 NA BASE DE DADOS DO BANCO, IREMOS APENAS COMENTÁ-LOS NO CÓDIGO 
		//Pessoa p1 = new Pessoa(null, "Carlos da Silva", "carlos@gmail.com"); // DEIXAMOS O CAMPO id nulo(null), POIS O PRÓPRIO BANCO DE DADOS IRÁ AUTOINCREMENTAR OS id 
		//Pessoa p2 = new Pessoa(null, "Joaquim Torres", "joaquim@gmail.com");
		//Pessoa p3 = new Pessoa(null, "Ana Maria", "ana@gmail.com");
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("exemplo-jpa"); // ESSA LINHA INSTANCIA O EntityManagerFactory COM AQUELAS CONFIGURAÇÕES PRESENTES NO ARQUIVO persistence.xml
		EntityManager em = emf.createEntityManager(); // COM ESSAS DUAS INSTANCIAÇÕES, AUTOMATICAMENTE JÁ TEREMOS UMA CONEXÃO COM O BANCO DE DADOS E O CONTEXTO DE PERSISTÊNCIA IMPLEMENTADO.
		/*em.getTransaction().begin();	// QUANDO O JPA FAZ ALGUMA OPERAÇÃO QUE NÃO É UMA SIMPLES LEITURA DO BANCO DE DADOS, ELE PRECISA DE UMA TRANSAÇÃO. COM ESSA LINHA INICIAMOS UMA TRANSAÇÃO
		em.persist(p1); 	// persist() É O MÉTODO QUE PEGA O OBJETO E SALVA NO BANCO DE DADOS
		em.persist(p2);
		em.persist(p3);
		em.getTransaction().commit(); */	// QUANDO TERMINARMOS DE FAZER AQUILO QUE PRECISAMOS, APLICAMOS ESSA LINHA. QUE IRÁ CONFIRMAR AS ALTERAÇÕES QUE FIZEMOS
		
		/*Pessoa p = em.find(Pessoa.class, 2);	// find() É UMA FUNÇÃO PARA BUSCAR O OBJETO NO BANCO PELO SEU id
		
		System.out.println(p);*/
		
		Pessoa p = em.find(Pessoa.class, 2);
		
		em.getTransaction().begin();
		em.remove(p);	// remove() É UMA FUNÇÃO PARA REMOVER UM OBJETO DO BANCO DE DADOS. OBS: PARA REMOVERMOS OBJETOS A PARTIR DA EntityManager, ESSES OBJETOS PRECISAM ESTAR MONITORADOS
		em.getTransaction().commit();
		
		System.out.println("Pronto!");
		em.close();
		emf.close();
	}

}
