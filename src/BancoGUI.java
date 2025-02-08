import javax.swing.*;
import java.awt.*;

public class BancoGUI extends JFrame {
    private JTextField txtNumeroConta, txtNome, txtValor;
    private JButton btnCriarConta, btnDepositar, btnSacar, btnTransferir, btnConsultar, btnListar;
    private JTextArea txtSaida;
    private GerenciadorDeContas banco;

    public BancoGUI() {
        banco = new GerenciadorDeContas();

        setTitle("💰 Sistema Bancário");
        setSize(600, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Painel Superior (Dados da Conta)
        JPanel painelSuperior = new JPanel(new GridLayout(3, 2, 5, 5));
        painelSuperior.setBorder(BorderFactory.createTitledBorder("Dados da Conta"));

        txtNumeroConta = new JTextField();
        txtNome = new JTextField();
        txtValor = new JTextField();

        painelSuperior.add(new JLabel("Número da Conta:"));
        painelSuperior.add(txtNumeroConta);
        painelSuperior.add(new JLabel("Nome:"));
        painelSuperior.add(txtNome);
        painelSuperior.add(new JLabel("Valor:"));
        painelSuperior.add(txtValor);

        // Painel de Botões (Menores)
        JPanel painelBotoes = new JPanel(new GridLayout(2, 3, 5, 5));
        painelBotoes.setBorder(BorderFactory.createTitledBorder("Operações"));

        btnCriarConta = new JButton("Criar");
        btnDepositar = new JButton("Depositar");
        btnSacar = new JButton("Sacar");
        btnTransferir = new JButton("Transferir");
        btnConsultar = new JButton("Saldo");
        btnListar = new JButton("Listar");

        painelBotoes.add(btnCriarConta);
        painelBotoes.add(btnDepositar);
        painelBotoes.add(btnSacar);
        painelBotoes.add(btnTransferir);
        painelBotoes.add(btnConsultar);
        painelBotoes.add(btnListar);

        // Área de Histórico
        txtSaida = new JTextArea();
        txtSaida.setEditable(false);
        txtSaida.setFont(new Font("Arial", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(txtSaida);
        scrollPane.setBorder(BorderFactory.createTitledBorder("📜 Histórico"));
        scrollPane.setPreferredSize(new Dimension(600, 300));

        add(painelSuperior, BorderLayout.NORTH);
        add(painelBotoes, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);

        // Eventos dos Botões
        btnCriarConta.addActionListener(e -> criarConta());
        btnDepositar.addActionListener(e -> depositar());
        btnSacar.addActionListener(e -> sacar());
        btnTransferir.addActionListener(e -> transferir());
        btnConsultar.addActionListener(e -> consultarSaldo());
        btnListar.addActionListener(e -> listarContas());

        // Exibir a Interface
        setVisible(true);
    }

    private void criarConta() {
        String nome = txtNome.getText();
        int numeroConta = Integer.parseInt(txtNumeroConta.getText());
        banco.adicionarConta(new ContaBancaria(nome, numeroConta, 0));
        txtSaida.append("✅ Conta criada para " + nome + "\n");
        JOptionPane.showMessageDialog(this, "Conta criada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

    private void depositar() {
        int numeroConta = Integer.parseInt(txtNumeroConta.getText());
        double valor = Double.parseDouble(txtValor.getText());
        ContaBancaria conta = banco.buscarConta(numeroConta);
        if (conta != null) {
            conta.depositar(valor);
            txtSaida.append("💰 Depósito de R$" + valor + " realizado!\n");
        } else {
            JOptionPane.showMessageDialog(this, "Conta não encontrada!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void sacar() {
        int numeroConta = Integer.parseInt(txtNumeroConta.getText());
        double valor = Double.parseDouble(txtValor.getText());
        ContaBancaria conta = banco.buscarConta(numeroConta);
        if (conta != null) {
            conta.sacar(valor);
            txtSaida.append("💵 Saque de R$" + valor + " realizado!\n");
        } else {
            JOptionPane.showMessageDialog(this, "Conta não encontrada!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void transferir() {
        int origem = Integer.parseInt(txtNumeroConta.getText());
        double valor = Double.parseDouble(txtValor.getText());

        String destinoStr = JOptionPane.showInputDialog("Digite o número da conta de destino:");
        int destino = Integer.parseInt(destinoStr);

        ContaBancaria contaOrigem = banco.buscarConta(origem);
        ContaBancaria contaDestino = banco.buscarConta(destino);

        if (contaOrigem != null && contaDestino != null) {
            contaOrigem.transferir(contaDestino, valor);
            txtSaida.append("🔄 Transferência de R$" + valor + " realizada!\n");
        } else {
            JOptionPane.showMessageDialog(this, "Conta de origem ou destino não encontrada!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void consultarSaldo() {
        int numeroConta = Integer.parseInt(txtNumeroConta.getText());
        ContaBancaria conta = banco.buscarConta(numeroConta);
        if (conta != null) {
            JOptionPane.showMessageDialog(this, "Saldo: R$" + conta.getSaldo(), "Saldo Atual", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Conta não encontrada!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void listarContas() {
        txtSaida.setText("");
        txtSaida.append("\n--- 📋 Lista de Contas ---\n");

        for (ContaBancaria conta : banco.getListaContas()) {
            txtSaida.append("Conta: " + conta.getNumeroConta() +
                    " | Titular: " + conta.getTitular() +
                    " | Saldo: R$" + conta.getSaldo() + "\n");
        }

        if (banco.getListaContas().isEmpty()) {
            txtSaida.append("Nenhuma conta cadastrada.\n");
        }
    }

    public static void main(String[] args) {
        new BancoGUI();
    }
}
