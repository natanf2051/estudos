package src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Admin {
    
    public void consultandoPorUser(String user2) {
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
