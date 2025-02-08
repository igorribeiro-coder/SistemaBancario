import java.util.ArrayList;

public class GerenciadorDeContas {
    private ArrayList<ContaBancaria> contas = new ArrayList<>();

    public void adicionarConta(ContaBancaria conta) {
        contas.add(conta);
    }

    public ContaBancaria buscarConta(int numeroConta) {
        for (ContaBancaria conta : contas) {
            if (conta.getNumeroConta() == numeroConta) {
                return conta;
            }
        }
        return null;
    }

    public ArrayList<ContaBancaria> getListaContas() {
        return contas;
    }

    public void listarContas() {
        
    }
}
