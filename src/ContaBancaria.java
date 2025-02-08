import java.io.Serializable;

public class ContaBancaria implements Serializable {
    private String titular;
    private int numeroConta;
    private double saldo;

    public ContaBancaria(String titular, int numeroConta, double saldoInicial) {
        this.titular = titular;
        this.numeroConta = numeroConta;
        this.saldo = saldoInicial;
    }

        public int getNumeroConta() {
            return numeroConta;
        }

        public String getTitular() {
        return titular;
        }

    public double getSaldo() {
        return saldo;
    }

    public void depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
            System.out.println("Depósito de R$" + valor + "Realizado com sucesso.");
        } else {
            System.out.println("Saldo inválido para depósito.");
        }
    }
        public boolean sacar(double valor) {
        if (valor >0 && saldo >=valor) {
            saldo -= valor;
            System.out.println("Saque de R$" + valor + "Realizado com sucesso.");
        } else {
            System.out.print("Saldo insuficiente ou valor inválido");
        }
            return false;
        }
        public boolean transferir(ContaBancaria destino, double valor) {
        if (this.saldo >= valor) {
            this.saldo -= valor;
            destino.depositar(valor);
            System.out.println("Transferência de R$" + valor + " para " + destino.getTitular() + "Realizada com sucesso.");
            return true;
        }
        return false;
        }
    public void consultarSaldo() {
        System.out.println("Saldo atual R$" + saldo);
    }
}
