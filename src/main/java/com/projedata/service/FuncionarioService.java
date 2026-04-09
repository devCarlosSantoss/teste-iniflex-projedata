package com.projedata.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.projedata.exception.FuncionarioException;
import com.projedata.model.Funcionario;
import com.projedata.model.enums.Funcao;

import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;


public class FuncionarioService {

    // 3.1 – Carrega funcionários
    public List<Funcionario> carregarFuncionarios() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());

            InputStream input = getClass().getClassLoader().getResourceAsStream("funcionarios.json");

            return new ArrayList<>(
                    Arrays.asList(mapper.readValue(input, Funcionario[].class))
            );
        } catch (Exception e) {
            throw new FuncionarioException("Erro ao carregar JSON: \n" + e.getMessage());
        }
    }

    // 3.2 – Remove funcionário por nome
    public void removerFuncionario(List<Funcionario> lista, String nome) {
        boolean removed = lista.removeIf(funcionario -> funcionario.getNome().equalsIgnoreCase(nome));

        if (!removed) {
            throw new FuncionarioException("Funcionário não encontrado: " + nome);
        }
    }

    // 3.4 – Aplica aumento de 10%
    public void aplicarAumento(List<Funcionario> lista) {
        lista.forEach(funcionario -> {
            funcionario.setSalario(funcionario.getSalario().multiply(new BigDecimal("1.10")));
        });
    }

    // 3.5 – Agrupa por função usando Map
    public Map<Funcao, List<Funcionario>> agruparPorFuncao(List<Funcionario> lista) {
        return lista.stream().collect(Collectors.groupingBy(Funcionario::getFuncao));
    }

    // 3.6 – Imprime agrupados por função
    public void imprimirAgrupados(Map<Funcao, List<Funcionario>> mapa) {
        mapa.forEach(((funcao, list) -> {
            System.out.println("\nFunção: " + funcao);

            list.forEach(funcionario -> System.out.println(funcionario.getNome()));
        }));
    }

    // 3.8 – Filtra aniversariantes mês 10 e 12
    public List<Funcionario> buscarAniversariantes(List<Funcionario> lista) {
        return lista.stream().filter(funcionario -> {
            int mes = funcionario.getDataNascimento().getMonthValue();
            return mes == 10 || mes == 12;
        }).toList();
    }

    // 3.9 – Busca funcionário mais velho
    public Funcionario buscarMaisVelho(List<Funcionario> lista) {
        return lista.stream()
                .min(Comparator.comparing(Funcionario::getDataNascimento))
                .orElseThrow(() -> new FuncionarioException("Lista vazia"));
    }

    // 3.10 – Ordena funcionários por nome
    public List<Funcionario> ordenarPorNome(List<Funcionario> lista) {
        return lista.stream()
                .sorted(Comparator.comparing(Funcionario::getNome)).toList();
    }

    // 3.11 – Calcula soma total dos salários
    public BigDecimal calcularSalarios(List<Funcionario> lista) {
        return lista.stream().map(Funcionario::getSalario).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    // 3.12 – Calcula salários mínimos e imprime em tabela
    public void imprimirSalariosMinimosTabela(List<Funcionario> lista) {

        BigDecimal salarioMinimo = new BigDecimal("1212.00");

        List<String[]> dados = new ArrayList<>();

        for (Funcionario f : lista) {
            BigDecimal qtd = f.getSalario()
                    .divide(salarioMinimo, 2, RoundingMode.HALF_UP);

            dados.add(new String[]{
                    f.getNome(),
                    qtd.toString()
            });
        }

        imprimirTabelaGenerica(
                "SALÁRIOS MÍNIMOS",
                new String[]{"Nome", "Qtd Salários"},
                dados
        );
    }

    // 3.3 – Impressão formatada
    public void imprimirTabela(List<Funcionario> lista) {

        String formatoLinha = "| %-15s | %-10s | %-15s |%n";

        System.out.println("+-----------------+------------+-----------------+");
        System.out.printf(formatoLinha, "Nome", "Idade", "Função");
        System.out.println("+-----------------+------------+-----------------+");

        lista.forEach(funcionario -> {

            int idade = java.time.Period
                    .between(funcionario.getDataNascimento(), java.time.LocalDate.now())
                    .getYears();

            System.out.printf(
                    formatoLinha,
                    funcionario.getNome(),
                    idade,
                    funcionario.getFuncao()
            );
        });

        System.out.println("+-----------------+------------+-----------------+");
    }

    public void imprimirTabelaGenerica(String titulo, String[] colunas, List<String[]> dados) {

        int[] larguras = new int[colunas.length];

        // Define largura com base no cabeçalho
        for (int i = 0; i < colunas.length; i++) {
            larguras[i] = colunas[i].length();
        }

        // Ajusta largura com base nos dados
        for (String[] linha : dados) {
            for (int i = 0; i < linha.length; i++) {
                larguras[i] = Math.max(larguras[i], linha[i].length());
            }
        }

        // Linha separadora
        StringBuilder separador = new StringBuilder("+");
        for (int largura : larguras) {
            separador.append("-".repeat(largura + 2)).append("+");
        }

        System.out.println("\n--- " + titulo + " ---");
        System.out.println(separador);

        // Cabeçalho
        System.out.print("|");
        for (int i = 0; i < colunas.length; i++) {
            System.out.printf(" %-" + larguras[i] + "s |", colunas[i]);
        }
        System.out.println();

        System.out.println(separador);

        // Dados
        for (String[] linha : dados) {
            System.out.print("|");
            for (int i = 0; i < linha.length; i++) {
                System.out.printf(" %-" + larguras[i] + "s |", linha[i]);
            }
            System.out.println();
        }

        System.out.println(separador);
    }

    public void imprimirAniversariantesTabela(List<Funcionario> lista) {

        List<String[]> dados = lista.stream()
                .map(f -> new String[]{
                        f.getNome(),
                        String.valueOf(f.getDataNascimento().getMonthValue())
                })
                .toList();

        imprimirTabelaGenerica(
                "ANIVERSARIANTES (10 e 12)",
                new String[]{"Nome", "Mês"},
                dados
        );
    }

    public void imprimirOrdenadosTabela(List<Funcionario> lista) {

        List<String[]> dados = lista.stream()
                .map(f -> new String[]{f.getNome()})
                .toList();

        imprimirTabelaGenerica(
                "FUNCIONÁRIOS ORDENADOS",
                new String[]{"Nome"},
                dados
        );
    }

    public void imprimirTotalSalariosTabela(List<Funcionario> lista) {

        BigDecimal total = calcularSalarios(lista);

        NumberFormat nf = NumberFormat.getInstance(new Locale("pt", "BR"));

        List<String[]> dados = new ArrayList<>();
        dados.add(new String[]{"Total", nf.format(total)});

        imprimirTabelaGenerica(
                "TOTAL SALÁRIOS",
                new String[]{"Descrição", "Valor"},
                dados
        );
    }
}
