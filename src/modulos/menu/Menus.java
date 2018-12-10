package modulos.menu;

import modulos.administrativo.Caixa;
import modulos.administrativo.Produto;
import modulos.usuario.Usuario;
import settings.Config;

import java.util.ArrayList;
import java.util.Scanner;

public class Menus {
    private Config config = new Config();
    private Scanner scan = new Scanner(System.in);
    private String escolhaMenu;
    private int autenticado = 999;

    public void menuLogin(ArrayList<Usuario> listaUsuarios, ArrayList<Produto> listaProdutos, Caixa caixaAberto) {
        String login;
        String senha;

        System.out.println("-----------------------------");
        System.out.println("Bem vindo ao " + config.getNome());
        System.out.print("Por gentileza, nos informe o seu login: ");
        login = scan.nextLine();
        System.out.print("Ok, " + login + ", agora nos informe a sua senha: ");
        senha = scan.nextLine();

        int i = 0;
        while((i < listaUsuarios.size()) && (autenticado == 999)) {
            if(login.equals(listaUsuarios.get(i).getLogin()) && senha.equals(listaUsuarios.get(i).getSenha())) {
                autenticado = i;
                menuPrincipal(listaUsuarios, listaProdutos, caixaAberto);
            }
            i++;
        }
        config.limparTela();
        System.out.println("Senha incorreta! Por gentileza, tente novamente.");
        menuLogin(listaUsuarios, listaProdutos, caixaAberto);
    }

    public void menuPrincipal(ArrayList<Usuario> listaUsuarios, ArrayList<Produto> listaProdutos, Caixa caixaAberto) {
        config.limparTela();
        System.out.println(config.retornarTempo() + ", você está logado como: " + listaUsuarios.get(autenticado).getNome());
        System.out.println("-----------------------------");
        System.out.println("1. Venda de produto");
        System.out.println("2. Exibir produtos em estoque");
        if(listaUsuarios.get(autenticado).getPermissoes() == 2) {
            System.out.println("3. Adicionar produto em estoque");
            System.out.println("4. Gerenciar caixa aberto");
            System.out.println("5. Sair");
        } else {
            System.out.println("3. Sair");
        }

        escolhaMenu = scan.nextLine();
        switch (escolhaMenu) {
            case "1":
                escolhaMenu = "0";
                menuVendaProduto(listaUsuarios, listaProdutos, caixaAberto);
                break;
            case "2":
                escolhaMenu = "0";
                menuMostrarEstoque(listaUsuarios, listaProdutos, caixaAberto);
                break;
            case "3":
                if(listaUsuarios.get(autenticado).getPermissoes() == 2) {
                    escolhaMenu = "0";
                    menuAdicionarEstoque(listaUsuarios, listaProdutos, caixaAberto);
                    break;
                } else {
                    escolhaMenu = "0";
                    menuDesconectar();
                    break;
                }
            case "4":
                if(listaUsuarios.get(autenticado).getPermissoes() == 2) {
                    escolhaMenu = "0";
                    menuGerenciarCaixa(listaUsuarios, listaProdutos, caixaAberto);
                    break;
                } else {
                    escolhaMenu = "0";
                    menuPrincipal(listaUsuarios, listaProdutos, caixaAberto);
                    break;
                }
            case "5":
                escolhaMenu = "0";
                menuDesconectar();
                break;
            default:
                escolhaMenu = "0";
                menuPrincipal(listaUsuarios, listaProdutos, caixaAberto);
                break;
        }
    }

    public void menuVendaProduto(ArrayList<Usuario> listaUsuarios, ArrayList<Produto> listaProdutos, Caixa caixaAberto) {
        ArrayList<Integer> comprasEfetuadas = new ArrayList<>();
        String opcaoEscolhida;
        double valorTotalCompra = 0.00;
        boolean caixaAtivo = true;

        do {
            config.limparTela();
            System.out.println("-----------------------------");
            mostrarEstoque(listaProdutos);
            System.out.println(" ");
            System.out.println("DIGITE 'f' PARA ENCERRAR A COMPRA.");
            System.out.print("Passe o produto no leitor ou digite o código: ");
            opcaoEscolhida = scan.nextLine();
            if(!opcaoEscolhida.equals("f")) {
                comprasEfetuadas.add(Integer.parseInt(opcaoEscolhida));
            } else {
                config.limparTela();
                caixaAtivo = false;

                System.out.println("Digite o método de pagamento:");
                System.out.println("1 - Dinheiro");
                System.out.println("2 - Cartão de Débito");
                System.out.println("3 - Cartão de Crédito");
                System.out.print("Opção escolhida: ");
                opcaoEscolhida = scan.nextLine();

                config.limparTela();
                System.out.println("Resumo da compra: ");
                System.out.println(" --- Produtos ---");
                for (Integer produtos:comprasEfetuadas) {
                    System.out.println("- " + listaProdutos.get(produtos).getNome() + " - Qtd: 1 - R$" + listaProdutos.get(produtos).getValor());
                    listaProdutos.get(produtos).subtrairEstoque(1);

                    caixaAberto.setFluxoTotal(caixaAberto.getFluxoTotal() + listaProdutos.get(produtos).getValor());
                    valorTotalCompra = valorTotalCompra + listaProdutos.get(produtos).getValor();
                }
                caixaAberto.setQtdVendas(caixaAberto.getQtdVendas() + 1);
                System.out.println(" --- -------- ---");
                System.out.printf("Valor total da compra: R$%.2f %n", valorTotalCompra);

                switch (opcaoEscolhida) {
                    case "1":
                        caixaAberto.setTotalDosPagamentos(0, (caixaAberto.getTotalDosPagamentos(0) + valorTotalCompra));
                        System.out.println("Método de pagamento: dinheiro");
                        break;
                    case "2":
                        caixaAberto.setTotalDosPagamentos(1, (caixaAberto.getTotalDosPagamentos(1) + valorTotalCompra));
                        System.out.println("Método de pagamento: cartão de débito");
                        break;
                    case "3":
                        caixaAberto.setTotalDosPagamentos(2, (caixaAberto.getTotalDosPagamentos(2) + valorTotalCompra));
                        System.out.println("Método de pagamento: cartão de crédito");
                        break;
                    default:
                        caixaAberto.setTotalDosPagamentos(0, (caixaAberto.getTotalDosPagamentos(0) + valorTotalCompra));
                        System.out.println("Método de pagamento: dinheiro");
                        break;
                }
            }
        } while(caixaAtivo);

        System.out.println("Pressione qualquer tecla para continuar...");
        scan.nextLine();
        menuPrincipal(listaUsuarios, listaProdutos, caixaAberto);
    }

    public void menuGerenciarCaixa(ArrayList<Usuario> listaUsuarios, ArrayList<Produto> listaProdutos, Caixa caixaAberto) {
        System.out.println("-----------------------------");
        System.out.println("Total de vendas realizadas: " + caixaAberto.getQtdVendas());
        System.out.println(" ");
        System.out.printf("- Dinheiro: R$%.2f %n", caixaAberto.getTotalDosPagamentos(0));
        System.out.printf("- Cartão de Débito: R$%.2f %n", caixaAberto.getTotalDosPagamentos(1));
        System.out.printf("- Cartão de Crédito: R$%.2f %n", caixaAberto.getTotalDosPagamentos(2));
        System.out.printf("Valor total das vendas: R$%.2f %n", caixaAberto.getFluxoTotal());
        System.out.println("-----------------------------");
        System.out.println("Pressione qualquer tecla para continuar...");
        scan.nextLine();
        menuPrincipal(listaUsuarios, listaProdutos, caixaAberto);
    }

    public void menuMostrarEstoque(ArrayList<Usuario> listaUsuarios, ArrayList<Produto> listaProdutos, Caixa caixaAberto) {
        mostrarEstoque(listaProdutos);
        System.out.println("Pressione qualquer tecla para continuar...");
        scan.nextLine();
        menuPrincipal(listaUsuarios, listaProdutos, caixaAberto);
    }

    public void menuAdicionarEstoque(ArrayList<Usuario> listaUsuarios, ArrayList<Produto> listaProdutos, Caixa caixaAberto) {
        if(listaUsuarios.get(autenticado).getPermissoes() != 2) {
            System.out.println("ERRO: Você não possui permissão para acessar este menu!");
            menuPrincipal(listaUsuarios, listaProdutos, caixaAberto);
        } else {
            int codProduto;

            config.limparTela();
            mostrarEstoque(listaProdutos);
            System.out.print("Por favor, digite o código do produto que deseja alterar: ");
            codProduto = scan.nextInt();

            config.limparTela();
            System.out.println("Produto: " + listaProdutos.get(codProduto).getNome());
            System.out.println("Qtd. estoque anterior: " + listaProdutos.get(codProduto).getNome());
            System.out.print("Digite a nova quantidade em estoque: ");
            listaProdutos.get(codProduto).setEstoque(scan.nextInt());

            config.limparTela();
            System.out.println("Produto: " + listaProdutos.get(codProduto).getNome());
            System.out.println("Qtd. estoque atual: " + listaProdutos.get(codProduto).getNome());
            System.out.println("-----------------------------");
            System.out.println("Pressione qualquer tecla para continuar...");
            scan.nextLine();
            menuPrincipal(listaUsuarios, listaProdutos, caixaAberto);
        }
    }

    public void menuDesconectar() {
        config.limparTela();
        System.out.println("Obrigado por utilizar o nosso sistema! Esperamos que volte em breve.");
        autenticado = 999;
        System.exit(1);
    }

    //Métodos privados
    private void mostrarEstoque(ArrayList<Produto> listaProdutos) {
        System.out.println("-----------------------------");
        for(int i = 0; i < listaProdutos.size(); i++) {
            System.out.println("Cód.: " + i +
                    " | " + listaProdutos.get(i).getNome() +
                    " | R$" + listaProdutos.get(i).getValor() +
                    " | Qtd.: " + listaProdutos.get(i).getQtdEstoque());
        }
        System.out.println("-----------------------------");
    }
}
