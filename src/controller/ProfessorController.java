package controller;

import model.Lista;
import model.Professor;
import java.io.*;

public class ProfessorController {
    private Lista<Professor> lista = new Lista<>();
    private String arquivo = "src/main/resources/data/professores.csv";

    public void carregarCSV() throws Exception {
        lista = new Lista<>();
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(",");
                Professor p = new Professor();
                p.setCPF(dados[0]);
                p.setNome(dados[1]);
                p.setArea(dados[2]);
                p.setPontos(dados[3]);
                lista.addLast(p);
            }
        }
    }

    private void salvarCSV() throws Exception {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo))) {
            for (int i = 0; i < lista.Size(); i++) {
                Professor p = lista.get(i);
                bw.write(p.getCPF() + "," + p.getNome() + "," + p.getArea() + "," + p.getPontos());
                bw.newLine();
            }
        }
    }

    public void adicionarProfessor(Professor p) throws Exception {
        lista.addLast(p);
        salvarCSV();
    }

    public Professor buscarPorCPF(String cpf) throws Exception {
        for (int i = 0; i < lista.Size(); i++) {
            Professor p = lista.get(i);
            if (p.getCPF().equals(cpf)) return p;
        }
        return null;
    }

    public boolean atualizarProfessor(String cpf, Professor novo) throws Exception {
        for (int i = 0; i < lista.Size(); i++) {
            Professor p = lista.get(i);
            if (p.getCPF().equals(cpf)) {
                p.setNome(novo.getNome());
                p.setArea(novo.getArea());
                p.setPontos(novo.getPontos());
                salvarCSV();
                return true;
            }
        }
        return false;
    }

    public boolean removerProfessor(String cpf) throws Exception {
        for (int i = 0; i < lista.Size(); i++) {
            Professor p = lista.get(i);
            if (p.getCPF().equals(cpf)) {
                lista.remove(i);
                salvarCSV();
                return true;
            }
        }
        return false;
    }
}
