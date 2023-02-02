package src;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Cadastro_user")
public class Cadastro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = true)
    @NotNull
    private String nome;

    @Column(nullable = false, unique = true)
    @NotNull
    private String email;

    @Column(name = "usuario", nullable = true, unique = true)
    @NotNull
    private String user;

    @Column(nullable = true)
    @NotNull
    private String pass;

    public void cadastrar() {

        System.out.println("digite seu nome: ");
        this.nome = new Scanner(System.in).nextLine();
        System.out.println("digite seu email: ");
        this.email = new Scanner(System.in).nextLine();
        System.out.println("digite seu usuario: ");
        this.user = new Scanner(System.in).nextLine();
        System.out.println("digite sua senha: ");
        this.pass = new Scanner(System.in).nextLine();
        notNull();
        configPass();
        validateEmail(this.email);
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getUser() {
        return user;
    }

    public String getPass() {
        return pass;
    }

    private void configPass() {
        Scanner input = new Scanner(System.in);
        while (this.pass.length() < 6) {
            System.out.println("sua senha precisa ter no minimo 6 caracteres: ");
            this.pass = input.nextLine();
        }
    }

    private void notNull() {
        Scanner input = new Scanner(System.in);
        while (nome.trim().isEmpty() || nome == null) {
            System.out.println("Nome não pode ser vazio ");
            this.nome = input.nextLine();
        }

        while (email.trim().isEmpty() || email == null) {
            System.out.println("Email não pode ser vazio");
            this.email = input.nextLine();
        }

        while (user.trim().isEmpty() || user == null) {
            System.out.println("User não pode ser vazio");
            this.user = input.nextLine();
        }

        while (pass.trim().isEmpty() || pass == null) {
            System.out.println("Password não pode ser vazio");
            this.pass = input.nextLine();
        }
    }

    private void validateEmail(String email) throws IllegalArgumentException {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(this.email);
        Scanner input = new Scanner(System.in);

        if (!matcher.matches()) {
            System.out.println("digite um email valido");
            this.email = input.nextLine();
            validateEmail(email);
        }
    }
}
