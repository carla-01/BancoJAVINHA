package src.main.java;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

//interface para operações comuns das contas
interface OperacoesConta{
    void sacar(double valor, String data);
    void depositar(double valor, String data);
    boolean transferir(double valor, String data, String numeroConta);
    void mostrarTransferencia(double valor, String data, String numeroConta);
    void exibirExtrato();
}

//classe abstrata Conta
abstract class Conta implements OperacoesConta{
    protected String numeroConta;
    protected double saldo;
    private String senha;
    protected List<String> extrato;

    //metodo construtor
    public Conta(String numeroConta, double saldoInicial, String senha){
        this.numeroConta = numeroConta;
        this.saldo = saldoInicial;
        this.senha = senha;
        this.extrato = new ArrayList<>();

        registrarExtrato("Conta criada com saldo inicial de: " + formatarValor(saldoInicial));
    }

    //metodos getters e setters
    public String getNumeroConta(){
        return numeroConta;
    }

    public double getSaldo(){
        return saldo;
    }

    protected void setSaldo(double saldo){
        this.saldo = saldo;
    }

    public boolean validarSenha(String senha){
        return this.senha.equals(senha);
    }

    protected void registrarExtrato(String operacao){
        extrato.add(operacao);
    }

    @Override
    public void exibirExtrato(){
        System.out.println("Extrato da Conta " + numeroConta + ":");
        for(String operacao : extrato){
            System.out.println(operacao);
        }
    }

    protected String formatarValor(double valor){
        return NumberFormat.getCurrencyInstance().format(valor);
    }

    @Override
    public String toString(){
        return "Conta [Número=" + numeroConta + ", Saldo=" + formatarValor(saldo) + "]";
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if(obj == null || getClass() != obj.getClass()){
            return false;
        }
        Conta conta = (Conta) obj;
        return numeroConta.equals(conta.numeroConta);
    }
}

//classe ContaCorrente herda de Conta e implementa OperacoesConta
class ContaCorrente extends Conta{
    public ContaCorrente(String numeroConta, double saldoInicial, String senha){
        super(numeroConta, saldoInicial, senha);
    }

    @Override
    public void mostrarTransferencia(double valor, String data, String numeroConta){
        double saldoAntes = getSaldo();

        setSaldo(getSaldo() + valor);

        registrarExtrato("Transferência de " + formatarValor(valor) + " em " + data + " da conta " + numeroConta + ".\n " + "Saldo antes: " + formatarValor(saldoAntes) + "\n Saldo após: " + formatarValor(getSaldo()));
    }

    @Override
    public boolean transferir(double valor, String data, String numeroConta){
        if(getSaldo() >= valor){
            double saldoAntes = getSaldo();

            setSaldo(getSaldo() - valor);

            registrarExtrato("Transferência de " + formatarValor(valor) + " em " + data + " para a conta " + numeroConta + ".\n Saldo antes: " + formatarValor(saldoAntes) + "\n Saldo após: " + formatarValor(getSaldo()));

            return true;
        }
        else{
            System.out.println("Saldo insuficiente na Conta Corrente.");

            return false;
        }
    }

    @Override
    public void sacar(double valor, String data){
        if(getSaldo() >= valor){
            double saldoAntes = getSaldo();

            setSaldo(getSaldo() - valor);

            registrarExtrato("Saque de " + formatarValor(valor) + " em " + data + ".\n Saldo antes: " + formatarValor(saldoAntes) + "\n Saldo após: " + formatarValor(getSaldo()));
            System.out.println("Saque realizado com sucesso na Conta Corrente.");
        }
        else{
            System.out.println("Saldo insuficiente na Conta Corrente.");
        }
    }

    @Override
    public void depositar(double valor, String data){
        double saldoAntes = getSaldo();

        setSaldo(getSaldo() + valor);

        registrarExtrato("Depósito de " + formatarValor(valor) + " em " + data + ".\n Saldo antes: " + formatarValor(saldoAntes) + "\n Saldo após: " + formatarValor(getSaldo()));
        System.out.println("Depósito realizado com sucesso na Conta Corrente.");
    }
}

//classe ContaPoupanca herda de Conta e implementa OperacoesConta
class ContaPoupanca extends Conta{
    public ContaPoupanca(String numeroConta, double saldoInicial, String senha){
        super(numeroConta, saldoInicial, senha);
    }

    @Override
    public void mostrarTransferencia(double valor, String data, String numeroConta){
        double saldoAntes = getSaldo();

        setSaldo(getSaldo() + valor);

        registrarExtrato("Transferência de " + formatarValor(valor) + " em " + data + " da conta " + numeroConta + ".\n " + "Saldo antes: " + formatarValor(saldoAntes) + "\n Saldo após: " + formatarValor(getSaldo()));
    }

    @Override
    public boolean transferir(double valor, String data, String numeroConta){
        if(getSaldo() >= valor){
            double saldoAntes = getSaldo();

            setSaldo(getSaldo() - valor);

            registrarExtrato("Transferência de " + formatarValor(valor) + " em " + data + " para a conta " + numeroConta + ".\n Saldo antes: " + formatarValor(saldoAntes) + "\n Saldo após: " + formatarValor(getSaldo()));

            return true;
        }
        else{
            System.out.println("Saldo insuficiente na Conta Poupança.");

            return false;
        }
    }

    @Override
    public void sacar(double valor, String data){
        if(getSaldo() >= valor){
            double saldoAntes = getSaldo();

            setSaldo(getSaldo() - valor);

            registrarExtrato("Saque de " + formatarValor(valor) + " em " + data + ".\n Saldo antes: " + formatarValor(saldoAntes) + "\n Saldo após: " + formatarValor(getSaldo()));
            System.out.println("Saque realizado com sucesso na Conta Poupança.");
        }
        else{
            System.out.println("Saldo insuficiente na Conta Poupança.");
        }
    }

    @Override
    public void depositar(double valor, String data){
        double saldoAntes = getSaldo();

        setSaldo(getSaldo() + valor);

        registrarExtrato("Depósito de " + formatarValor(valor) + " em " + data + ".\n Saldo antes: " + formatarValor(saldoAntes) + "\n Saldo após: " + formatarValor(getSaldo()));
        System.out.println("Depósito realizado com sucesso na Conta Poupança.");
    }
}

//classe Banco que gerencia as contas (salva)
class  Banco{
    private final List<Conta> contas;

    public Banco(){
        contas = new ArrayList<>();
    }

    public void adicionarConta(Conta conta){
        contas.add(conta);
    }

    public Conta buscarConta(String numeroConta){
        for(Conta conta : contas){
            if(conta.getNumeroConta().equals(numeroConta.trim())){
                //removendo espaços extras
                return conta;
            }
        }
        return null;
    }
}

//classe principal que vai interagir com o usuario
public class Main{
    private static final SimpleDateFormat FormatoData = new SimpleDateFormat("dd/MM/yyyy");

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        Banco banco = new Banco();
        int numeroCC = 1001;
        int numeroCP = 2001;

        while(true){
            System.out.print("\n_________________\n");

            System.out.println("\n--- ♥ Banco Javinha ♥ ---");
            System.out.print("Selecione uma das opcões abaixo.\n");

            System.out.println("1. Criar Conta");
            System.out.println("2. Sacar");
            System.out.println("3. Depositar");
            System.out.println("4. Transferência");
            System.out.println("5. Exibir Extrato");
            System.out.println("0. Sair");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            System.out.print("\n_________________");

            switch(opcao){

                case 0 ->{
                    System.out.println("\n Saindo do sistema...");
                    return;
                }

                case 1 ->{
                    System.out.print("\n_________________");

                    System.out.println("\n Escolha o tipo de conta:");
                    System.out.println("1. Conta Corrente");
                    System.out.println("2. Conta Poupança");

                    System.out.print("Opção: ");
                    int tipoConta = scanner.nextInt();
                    scanner.nextLine();

                    String numeroConta;
                    double saldoInicial;
                    String senha;

                    switch(tipoConta){
                        case 1 ->{
                            System.out.print("\n_________________");

                            numeroConta = "CC" + numeroCC++;

                            System.out.print("\n Saldo Inicial: ");
                            saldoInicial = scanner.nextDouble();
                            scanner.nextLine();

                            System.out.print("Senha: ");
                            senha = scanner.nextLine().trim();

                            ContaCorrente contaCorrente = new ContaCorrente(numeroConta, saldoInicial, senha);
                            banco.adicionarConta(contaCorrente);

                            System.out.println("Conta Corrente criada com sucesso. Número da conta: " + numeroConta);
                            System.out.print("\n_________________\n");
                        }

                        case 2 ->{
                            System.out.print("\n_________________");

                            numeroConta = "CP" + numeroCP++;

                            System.out.print("\n Saldo Inicial: ");
                            saldoInicial = scanner.nextDouble();
                            scanner.nextLine();

                            System.out.print("Senha: ");
                            senha = scanner.nextLine().trim();

                            ContaPoupanca contaPoupanca = new ContaPoupanca(numeroConta, saldoInicial, senha);
                            banco.adicionarConta(contaPoupanca);

                            System.out.println("Conta Poupança criada com sucesso. Número da conta: " + numeroConta);
                            System.out.print("\n_________________\n");
                        }
                        default -> System.out.println("Tipo de conta inválido.");
                    }
                }

                case 2, 3, 5 ->{
                    System.out.print("\n_________________");

                    System.out.print("\n Número da Conta: ");
                    String numeroConta = scanner.nextLine().trim();
                    Conta conta = banco.buscarConta(numeroConta);

                    if(conta == null){
                        System.out.println("Conta não encontrada. Verifique e tente novamente.");
                        continue;
                    }

                    System.out.print("Senha: ");
                    String senha = scanner.nextLine().trim();

                    if(!conta.validarSenha(senha)){
                        System.out.println("Senha incorreta. Tente novamente.");
                        continue;
                    }

                    String data;
                    while(true){
                        System.out.print("Data da operação (dd/mm/aaaa): ");
                        data = scanner.nextLine().trim();

                        if(isDataValida(data)){
                            break;
                        }
                        else{
                            System.out.println("Por favor, insira uma data válida (dd/mm/aaaa) e não anterior ao dia de hoje.");
                        }
                    }

                    switch(opcao){
                        case 2 ->{
                            System.out.print("\n_________________");

                            System.out.print("\n Valor do Saque: ");
                            double valorSaque = scanner.nextDouble();
                            scanner.nextLine();
                            conta.sacar(valorSaque, data);
                        }

                        case 3 ->{
                            System.out.print("\n_________________");

                            System.out.print("\n Valor do Depósito: ");
                            double valorDeposito = scanner.nextDouble();
                            scanner.nextLine();
                            conta.depositar(valorDeposito, data);
                        }

                        case 5 -> conta.exibirExtrato();
                    }
                }

                case 4 ->{
                    Conta conta = null;

                    while(conta == null){
                        System.out.print("\n_________________");

                        System.out.println("\n Número da sua conta: ");
                        String numeroConta = scanner.nextLine().trim();
                        conta = banco.buscarConta(numeroConta);

                        if(conta == null){
                            System.out.println("Conta não encontrada. Verifique e tente novamente.");
                        }
                    }

                    System.out.println("Digite a senha: ");
                    String senha = scanner.nextLine().trim();

                    if(!conta.validarSenha(senha)){
                        System.out.println("Senha incorreta. Tente novamente.");
                        continue;
                    }

                    String data;
                    while(true){
                        System.out.print("Data da operação (dd/mm/aaaa): ");
                        data = scanner.nextLine().trim();

                        if(isDataValida(data)){
                            break;
                        }
                        else{
                            System.out.println("Por favor, insira a data no formato (dd/mm/aaaa) e uma data não anterior ao dia de hoje.");
                        }
                    }
                    System.out.print("\n_________________");

                    System.out.println("\n Valor da transferência: ");
                    double valorTransferencia = scanner.nextDouble();
                    scanner.nextLine();

                    System.out.print("Número da conta destino: ");
                    String numeroContaDestino = scanner.nextLine().trim();

                    Conta contaDestino = banco.buscarConta(numeroContaDestino);

                    if(contaDestino == null){
                        System.out.println("Conta destino não encontrada. Verifique e tente novamente.");
                        continue;
                    }

                    if(conta.transferir(valorTransferencia, data, numeroContaDestino)){
                        contaDestino.mostrarTransferencia(valorTransferencia, data, conta.getNumeroConta());
                        System.out.println("Transferência realizada com sucesso!");
                    }
                    else{
                        System.out.println("Falha na transferência. Verifique o seu saldo e tente novamente.");
                    }
                }

                default -> System.out.println("Opção inválida.");
            }
        }
    }
    //ok
    //validação de data no formato correto, so data atual ou futuras
    private static boolean isDataValida(String data){

        try{
            Date dataInserida = FormatoData.parse(data);
            Date dataAtual = new Date();
            dataAtual = FormatoData.parse(FormatoData.format(dataAtual));
            return !dataInserida.before(dataAtual);
        }

        catch(ParseException e){
            return false;
        }
    }
}