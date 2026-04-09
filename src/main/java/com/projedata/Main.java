package com.projedata;

import com.projedata.model.Funcionario;
import com.projedata.model.enums.Funcao;
import com.projedata.service.FuncionarioService;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        FuncionarioService service = new FuncionarioService();

        // 3.1 – Inserir todos os funcionários (carregados via JSON)
        List<Funcionario> lista = service.carregarFuncionarios();

        // 3.2 – Remover o funcionário “João”
        service.removerFuncionario(lista, "João");

        // 3.3 – Imprimir todos os funcionários (formatação aplicada no service)
        service.imprimirTabela(lista);

        // 3.4 – Aplicar aumento de 10% nos salários
        service.aplicarAumento(lista);

        // 3.5 – Agrupar funcionários por função (MAP)
        Map<Funcao, List<Funcionario>> agrupados = service.agruparPorFuncao(lista);

        // 3.6 – Imprimir funcionários agrupados por função
        service.imprimirAgrupados(agrupados);

        // 3.8 – Imprimir funcionários que fazem aniversário nos meses 10 e 12
        service.imprimirAniversariantesTabela(
                service.buscarAniversariantes(lista)
        );

        // 3.9 – Imprimir funcionário com maior idade (nome e idade)
        Funcionario maisVelho = service.buscarMaisVelho(lista);

        int idade = Period.between(
                maisVelho.getDataNascimento(),
                LocalDate.now()
        ).getYears();

        List<String[]> dadosMaisVelho = new ArrayList<>();
        dadosMaisVelho.add(new String[]{
                maisVelho.getNome(),
                String.valueOf(idade)
        });

        service.imprimirTabelaGenerica(
                "MAIS VELHO",
                new String[]{"Nome", "Idade"},
                dadosMaisVelho
        );

        // 3.10 – Imprimir lista de funcionários em ordem alfabética
        service.imprimirOrdenadosTabela(
                service.ordenarPorNome(lista)
        );

        // 3.11 – Imprimir total dos salários
        service.imprimirTotalSalariosTabela(lista);

        // 3.12 – Imprimir quantos salários mínimos ganha cada funcionário
        service.imprimirSalariosMinimosTabela(lista);
    }
}