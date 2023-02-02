package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import src.Cadastro;

public class CadastroDAO implements ICadastroDAO {

    @Override
    public Cadastro cadastrar(Cadastro cadastro) {

        EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("teste");

        EntityManager entityManager = managerFactory.createEntityManager();

        // primeira tentativa de inserir
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(cadastro);
            entityManager.getTransaction().commit();

            entityManager.close();
            managerFactory.close();
            System.out.println("CLIENTE CADASTRADO COM SUCESSO!");

            // caso o user seja repetido, sera lançada uma excecao e pedirar para cadastrar
            // novamente.
        } catch (Exception e) {
            e.getStackTrace();
            System.out.println("USUARIO OU EMAIL JA EXISTE! TENTE OUTRO.");
            CadastroDAO cada = new CadastroDAO();
            Cadastro cad = new Cadastro();
            cad.cadastrar();
            cada.cadastrar(cad);

        }

        return cadastro;
    }

    @Override
    public void alterarSenha(String user2) {
        consultandoPorUser(user2);
        Scanner input = new Scanner(System.in);
        try {

            String user0 = user2;
            String url = "jdbc:postgresql://localhost:5432/teste";
            String usuario = "postgres";
            String senha = "admin";
            Connection conexao = DriverManager.getConnection(url, usuario, senha);

            Statement stm = conexao.createStatement();
            System.out.println("ALTERAR SENHA");
            String passe = new Scanner(System.in).nextLine();
            configPass(passe);

            String sql = "UPDATE cadastro_user SET pass= '" + passe + "'" + "WHERE usuario='" + user0 + "'";

            // ResultSet resultados = stm.executeUpdate(sql, sql);
            int rowsAffected = stm.executeUpdate(sql);

            System.out.println("SENHA ALTERADA COM SUCESSO");
            stm.close();
            conexao.close();

        } catch (Exception e) {

            System.out.println("ERRO AO ALTERAR A SENHA");
            e.printStackTrace();
        }

    }

    private void configPass(String passe) {
        
        if (passe.length() < 6) {
            System.out.println("sua senha precisa ter no minimo 6 caracteres: ");
            passe = new Scanner(System.in).nextLine();
            configPass(passe);
        }
    }

    private void consultandoPorUser(String user2) {
        try {
            String user0 = user2;
            String url = "jdbc:postgresql://localhost:5432/teste";
            String usuario = "postgres";
            String senha = "admin";
            Connection conexao = DriverManager.getConnection(url, usuario, senha);

            Statement stm = conexao.createStatement();

            String sql = "SELECT id, nome, usuario, pass from cadastro_user where usuario='" + user0 + "'";

            ResultSet resultados = stm.executeQuery(sql);

            if (resultados.next() == true) {
                System.out.println("Nome: " + resultados.getString("nome"));
                System.out.println("Usuario: " + resultados.getString("usuario"));
                System.out.println("Senha: " + resultados.getString("pass"));
            } else {
                System.out.println("usuario não cadastrado!");
            }
            stm.close();
            conexao.close();

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

}