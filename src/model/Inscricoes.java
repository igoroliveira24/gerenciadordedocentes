package model;

public class Inscricoes {
    private String CPF;
    private Disciplina CodigoDisciplina;
    private String CodigoProfessor;

    public String getCPF() { return CPF; }
    public void setCPF(String CPF) { this.CPF = CPF; }

    public Disciplina getCodigoDisciplina() { return CodigoDisciplina; }
    public void setCodigoDisciplina(Disciplina CodigoDisciplina) { this.CodigoDisciplina = CodigoDisciplina; }

    public String getCodigoProfessor() { return CodigoProfessor; }
    public void setCodigoProfessor(String CodigoProfessor) { this.CodigoProfessor = CodigoProfessor; }

}