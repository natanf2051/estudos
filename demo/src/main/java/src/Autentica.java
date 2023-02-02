package src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import DAO.CadastroDAO;

public class Autentica {

    public void entrar() throws SQLException {
        System.out.println("digite 1 para entrar e 2 para cadastrar");
        int num = new Scanner(System.in).nextInt();
        if (num == 1) {
            String consulta;
            System.out.println("digite seu user para entrar");
            consultandoPorUser(consulta = new Scanner(System.in).nextLine());
        }

        if (num == 2) {
            cadastre(null);
        }
    }

    private void consultandoPorUser(String user2) throws SQLException {

        String user0 = user2;
        String url = "jdbc:postgresql://localhost:5432/teste";
        String usuario = "postgres";
        String senha = "admin";
        Connection conexao = DriverManager.getConnection(url, usuario, senha);

        Statement stm = conexao.createStatement();
        String sql = "SELECT pass from cadastro_user where usuario='" + user0 + "'";
        ResultSet resultados = stm.executeQuery(sql);

        if (resultados.next() == true) {
            System.out.println("DIGITE SUA SENHA: ");

        } else {
            System.out.println("usuario não cadastrado!");
            stm.close();
            conexao.close();
            System.exit(0);

        }

        String password1 = new Scanner(System.in).nextLine();
        String password = resultados.getString("pass");
        // System.out.println(resultados.getString("pass"));

        while (!password.equals(password1)) {
            System.out.println("SUA SENHA ESTA INCORRETA");
            password1 = new Scanner(System.in).nextLine();

        }
        String sql1 = "SELECT id, nome, usuario, pass from cadastro_user where usuario='" + user0 + "'";

        ResultSet resultados1 = stm.executeQuery(sql1);

        resultados1.next();
        System.out.println("");
        System.out.println("");
        System.out.println("********** BEM VINDO **********");
        System.out.println("LOGADO COM SUCESSO");
        System.out.println("SEUS DADOS SAO: ");
        System.out.println("Nome: " + resultados1.getString("nome"));
        System.out.println("Usuario: " + resultados1.getString("usuario"));
        System.out.println("Senha: " + resultados1.getString("pass"));

        stm.close();
        conexao.close();

    }

    private void cadastre(Cadastro cadastro) {
        CadastroDAO cad = new CadastroDAO();
        Cadastro cada = new Cadastro();

        cada.cadastrar();
        cad.cadastrar(cada);
    }
}
