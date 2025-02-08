import java.io.IOException;
import java.util.Scanner;

public class SistemaBancario {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        GerenciadorDeContas banco = new GerenciadorDeContas();
        
        int opcao;
        do {
            System.out.println("\n-- MENU --");
            System.out.println("1 - Criar Conta");
            System.out.println("2 - Depositar");
            System.out.println("3 - Sacar");
            System.out.println("4 - Transferir");
            System.out.println("5 - Consultar Saldo");
            System.out.println("6 - Listar Contas");
            System.out.println("7 - Sair");
            System.out.println("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.print("Digite seu nome: ");
                    scanner.nextLine();
                    String nome = scanner.nextLine();
                    System.out.print("Digite o número da conta: ");
                    int numeroConta = scanner.nextInt();
                    banco.adicionarConta(new ContaBancaria(nome, numeroConta, 0));
                    System.out.println("Conta criada com sucesso!");
                    break;

                case 2:
                    System.out.print("Número da conta: ");
                    numeroConta = scanner.nextInt();
                    ContaBancaria contaDep = banco.buscarConta(numeroConta);
                    if (contaDep != null) {
                        System.out.print("Valor do depósito: ");
                        double valor = scanner.nextDouble();
                        contaDep.depositar(valor);
                    } else {
                        System.out.println("Conta não encontrada.");
                    }
                    break;

                case 3:
                    System.out.print("Número da conta: ");
                    numeroConta = scanner.nextInt();
                    ContaBancaria contaSaq = banco.buscarConta(numeroConta);
                    if (contaSaq != null) {
                        System.out.print("Valor do saque: ");
                        double valor = scanner.nextDouble();
                        contaSaq.sacar(valor);
                    } else {
                        System.out.println("Conta não encontrada.");
                    }
                    break;

                case 4:
                    System.out.print("Número da conta de origem: ");
                    int origem = scanner.nextInt();
                    ContaBancaria contaOrigem = banco.buscarConta(origem);
                    if (contaOrigem != null) {
                        System.out.print("Número da conta de destino: ");
                        int destino = scanner.nextInt();
                        ContaBancaria contaDestino = banco.buscarConta(destino);
                        if (contaDestino != null) {
                            System.out.print("Valor da transferência: ");
                            double valor = scanner.nextDouble();
                            contaOrigem.transferir(contaDestino, valor);
                        } else {
                            System.out.println("Conta de destino não encontrada.");
                        }
                    } else {
                        System.out.println("Conta de origem não encontrada.");
                    }
                    break;

                case 5:
                    System.out.println("Número da conta: ");
                    numeroConta = scanner.nextInt();
                    ContaBancaria contaSaldo = banco.buscarConta(numeroConta);
                    if (contaSaldo != null) {
                        contaSaldo.consultarSaldo();
                    } else {
                        System.out.println("Conta não encontrada.");
                    }
                    break;

                case 6:
                    banco.listarContas();
                    break;

                case 7:
                    System.out.println("Obrigado por utilizar o sistema bancário!");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 7);
            scanner.close();
    }
}