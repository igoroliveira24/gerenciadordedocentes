package controller;

import model.Lista;
import model.Cursos;
import java.io.*;

public class CursosController {
    private Lista<Cursos> lista = new Lista<>();
    private String arquivo = "src/main/resources/data/cursos.csv";

    public void carregarCSV() throws Exception {
        lista = new Lista<>();
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(",");
                Cursos c = new Cursos();
                c.setCodigo(dados[0]);
                c.setNome(dados[1]);
                c.setAreaConhecimento(dados[2]);
                lista.addLast(c);
            }
        }
    }

    private void salvarCSV() throws Exception {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo))) {
            for (int i = 0; i < lista.Size(); i++) {
                Cursos c = lista.get(i);
                bw.write(c.getCodigo() + "," + c.getNome() + "," + c.getAreaConhecimento());
                bw.newLine();
            }
        }
    }

    public void adicionarCurso(Cursos c) throws Exception {
        lista.addLast(c);
        salvarCSV();
    }

    public Cursos buscarPorCodigo(String codigo) throws Exception {
        for (int i = 0; i < lista.Size(); i++) {
            Cursos c = lista.get(i);
            if (c.getCodigo().equals(codigo)) return c;
        }
        return null;
    }

    public boolean atualizarCurso(String codigo, Cursos novo) throws Exception {
        for (int i = 0; i < lista.Size(); i++) {
            Cursos c = lista.get(i);
            if (c.getCodigo().equals(codigo)) {
                c.setNome(novo.getNome());
                c.setAreaConhecimento(novo.getAreaConhecimento());
                salvarCSV();
                return true;
            }
        }
        return false;
    }

    public boolean removerCurso(String codigo) throws Exception {
        for (int i = 0; i < lista.Size(); i++) {
            Cursos c = lista.get(i);
            if (c.getCodigo().equals(codigo)) {
                lista.remove(i);
                salvarCSV();
                return true;
            }
        }
        return false;
    }
}
