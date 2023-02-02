package DAO;

import java.sql.SQLException;

import src.Cadastro;

public interface ICadastroDAO {
    
    public Cadastro cadastrar(Cadastro cadastro);
   
    public void alterarSenha(String user2);
    
}
