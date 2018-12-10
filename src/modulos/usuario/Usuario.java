package modulos.usuario;

public class Usuario {
    private String nome;
    private String login;
    private String senha;
    private int permissoes;

    public Usuario(String nome, String login, String senha, int permissoes){
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.permissoes = permissoes;
    }

    public String getNome() {
        return this.nome;
    }

    public String getLogin() {
        return this.login;
    }

    public String getSenha() {
        return this.senha;
    }

    public int getPermissoes() {
        return this.permissoes;
    }
}
