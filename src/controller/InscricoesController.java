package controller;

import model.Lista;
import model.Inscricoes;
import java.io.*;
import model.Disciplina;

public class InscricoesController {
    private Lista<Inscricoes> lista = new Lista<>();
    private String arquivo = "src/main/resources/data/inscricoes.csv";

    public void carregarCSV() throws Exception {
        lista = new Lista<>();
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(",");
                Inscricoes i = new Inscricoes();

                // CPF do aluno
                i.setCPF(dados[0]);

                // Criar objeto Disciplina com base no código lido
                Disciplina d = new Disciplina();
                d.setCodigo(dados[1]); // supondo que no CSV você salva só o código da disciplina
                i.setCodigoDisciplina(d);

                // Código do professor
                i.setCodigoProfessor(dados[2]);

                // Adiciona à lista
                lista.addLast(i);
            }
        }
    }

    private void salvarCSV() throws Exception {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo))) {
            for (int j = 0; j < lista.Size(); j++) {
                Inscricoes i = lista.get(j);
                bw.write(i.getCPF() + "," +
                        i.getCodigoDisciplina().getCodigo() + "," +
                        i.getCodigoProfessor());
                bw.newLine();
            }
        }
    }

    public void adicionarInscricao(Inscricoes i) throws Exception {
        lista.addLast(i);
        salvarCSV();
    }

    public Inscricoes buscarPorCPF(String cpf) throws Exception {
        for (int j = 0; j < lista.Size(); j++) {
            Inscricoes i = lista.get(j);
            if (i.getCPF().equals(cpf)) {
                return i;
            }
        }
        return null;
    }

    public boolean atualizarInscricao(String cpf, Inscricoes nova) throws Exception {
        for (int j = 0; j < lista.Size(); j++) {
            Inscricoes i = lista.get(j);
            if (i.getCPF().equals(cpf)) {
                i.setCodigoDisciplina(nova.getCodigoDisciplina());
                i.setCodigoProfessor(nova.getCodigoProfessor());
                salvarCSV();
                return true;
            }
        }
        return false;
    }

    public boolean removerInscricao(String cpf) throws Exception {
        for (int j = 0; j < lista.Size(); j++) {
            Inscricoes i = lista.get(j);
            if (i.getCPF().equals(cpf)) {
                lista.remove(j);
                salvarCSV();
                return true;
            }
        }
        return false;
    }

    public void listarInscricoes() throws Exception {
        for (int j = 0; j < lista.Size(); j++) {
            Inscricoes i = lista.get(j);
            System.out.println("CPF: " + i.getCPF() +
                    ", Disciplina: " + i.getCodigoDisciplina().getCodigo() +
                    ", Professor: " + i.getCodigoProfessor());
        }
    }
}
