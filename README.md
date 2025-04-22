# 💸 Banco Javinha
Este é um sistema bancário simples desenvolvido em Java. Ele permite ao usuário criar contas (corrente e poupança), realizar saques, depósitos, transferências e visualizar o extrato de movimentações.

## 🧠 Funcionalidades
    Criação de Conta Corrente e Conta Poupança

    Saque, Depósito e Transferência entre contas

    Validação de senha e data da operação

    Exibição de extrato com histórico de operações

    Diferenciação entre tipos de contas com mensagens personalizadas

## 🛠️ Tecnologias Utilizadas
    Java (console)

    java.util para manipulação de listas e datas

    java.text para formatação de moeda e datas

## 🧾 Como usar
1. Compile o projeto:

        javac Main.java
2. Execute o programa:

        java Main
3. Escolha as opções do menu para criar contas, fazer transações, etc.

## 📋 Estrutura do Projeto
    OperacoesConta: Interface com as operações básicas das contas

    Conta: Classe abstrata base com atributos e métodos comuns

    ContaCorrente / ContaPoupanca: Especializações com comportamentos específicos

    Banco: Gerencia todas as contas criadas

    Main: Classe principal com o menu de interação e execução do programa

## 📅 Validação de Data
O sistema exige que a data da operação seja informada no formato dd/MM/yyyy, e não aceita datas anteriores à atual.

## 🔐 Segurança
Cada conta possui uma senha que deve ser validada para realizar qualquer operação.

## 📈 Exemplo de Uso

--- ♥ Banco Javinha ♥ ---
1. Criar Conta
2. Sacar
3. Depositar
4. Transferência
5. Exibir Extrato
0. Sair


