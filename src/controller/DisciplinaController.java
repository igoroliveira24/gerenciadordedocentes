package controller;

import model.Lista;
import model.Disciplina;
import java.io.*;

public class DisciplinaController {
    private Lista<Disciplina> lista = new Lista<>();
    private String arquivo = "src/main/resources/data/disciplinas.csv";

    public void carregarCSV() throws Exception {
        lista = new Lista<>();
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(",");
                Disciplina d = new Disciplina();
                d.setCodigo(dados[0]);
                d.setNome(dados[1]);
                d.setDiaSemana(dados[2]);
                d.setHorarioInicial(dados[3]);
                d.setHorasDiarias(Integer.parseInt(dados[4]));
                d.setCodigoCurso(dados[5]);
                lista.addLast(d);
            }
        }
    }

    private void salvarCSV() throws Exception {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo))) {
            for (int i = 0; i < lista.Size(); i++) {
                Disciplina d = lista.get(i);
                bw.write(d.getCodigo() + "," + d.getNome() + "," + d.getDiaSemana() + "," +
                        d.getHorarioInicial() + "," + d.getHorasDiarias() + "," + d.getCodigoCurso());
                bw.newLine();
            }
        }
    }

    public void adicionarDisciplina(Disciplina d) throws Exception {
        lista.addLast(d);
        salvarCSV();
    }

    public Disciplina buscarPorCodigo(String codigo) throws Exception {
        for (int i = 0; i < lista.Size(); i++) {
            Disciplina d = lista.get(i);
            if (d.getCodigo().equals(codigo)) return d;
        }
        return null;
    }

    public boolean atualizarDisciplina(String codigo, Disciplina nova) throws Exception {
        for (int i = 0; i < lista.Size(); i++) {
            Disciplina d = lista.get(i);
            if (d.getCodigo().equals(codigo)) {
                d.setNome(nova.getNome());
                d.setDiaSemana(nova.getDiaSemana());
                d.setHorarioInicial(nova.getHorarioInicial());
                d.setHorasDiarias(nova.getHorasDiarias());
                d.setCodigoCurso(nova.getCodigoCurso());
                salvarCSV();
                return true;
            }
        }
        return false;
    }

    public boolean removerDisciplina(String codigo) throws Exception {
        for (int i = 0; i < lista.Size(); i++) {
            Disciplina d = lista.get(i);
            if (d.getCodigo().equals(codigo)) {
                lista.remove(i);
                salvarCSV();
                return true;
            }
        }
        return false;
    }
}
