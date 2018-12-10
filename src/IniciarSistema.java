/*
Bruno Blanco Rovari                     21801006
Caio Rogério Ferreira Costa             21811015
Eder dos Santos Silva                   21811083
Paulo Phelipe da Silva Santos Zacarias  21801032
Vitor Vasconcellos dos Santos           21801106
 */

import modulos.administrativo.Caixa;
import modulos.usuario.Usuario;
import modulos.administrativo.Produto;
import modulos.menu.Menus;

import java.util.ArrayList;

public class IniciarSistema {
    public static void main(String[] args) {
        System.out.println("-----------------------------");
        System.out.println("Carregando sistema...");

        System.out.println("Carregando produtos...");
        ArrayList<Produto> listaProdutos = new ArrayList();
        Produto primeiroProduto = new Produto("Detergente Pimpão", 2.49, 10);
        Produto segundoProduto = new Produto("Café Neymar", 7.49, 10);
        Produto terceiroProduto = new Produto("Sabão em Pó", 5.49, 10);
        listaProdutos.add(primeiroProduto);
        listaProdutos.add(segundoProduto);
        listaProdutos.add(terceiroProduto);
        System.out.println("Produtos carregados.");

        ArrayList<Usuario> listaUsuarios = new ArrayList();
        /* Permissões de usuário:
            1 - Usuário comum
            2 - Gerente */
        Usuario primeiroUsuario = new Usuario("Vitor Vasconcellos", "vitorv", "123456", 2);
        Usuario segundoUsuario = new Usuario("Operador de caixa", "operador", "123456", 1);
        listaUsuarios.add(primeiroUsuario);
        listaUsuarios.add(segundoUsuario);
        System.out.println("Usuários carregados.");
        /*Métodos de pagamento:
           0 - Dinheiro
           1 - Cartão de Débito
           2 - Cartão de Crédito
         */
        System.out.println("Abrindo o caixa...");
        Caixa caixaAberto = new Caixa();
        caixaAberto.setTotalDosPagamentos(0, 0);
        caixaAberto.setTotalDosPagamentos(1, 0);
        caixaAberto.setTotalDosPagamentos(2, 0);
        System.out.println("Caixa aberto.");


        Menus menu = new Menus();
        menu.menuLogin(listaUsuarios, listaProdutos, caixaAberto);
    }
}
