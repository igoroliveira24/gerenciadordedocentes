package model;

public class Lista<T> {
    T data;
    Lista<T> next;
    Lista<T> primeiro;

    public boolean ListaVazia(){
        if(primeiro == null){
            return true;
        }else{
            return false;
        }
    }
    public int Size(){
        int cont = 0;
        if(!ListaVazia()){
            Lista<T> auxiliar = primeiro;
            while(auxiliar != null){
                cont = cont + 1;
                auxiliar = auxiliar.next;
            }
        }
        return cont;
    }
    public Lista<T> getNo(int pos) throws Exception{
        if(ListaVazia()){
            throw new Exception("Lista Vazia");
        }
        int tamanho = Size();
        if(pos < 0 || pos > tamanho - 1){
            throw new Exception("Posição invalida");
        }
        Lista<T> auxiliar = primeiro;
        int cont = 0;
        while(cont < pos){
            auxiliar = auxiliar.next;
            cont = cont + 1;
        }
        return auxiliar;
    }

    public void addFirst(T valor){
        Lista<T> elemento = new Lista<>();
        elemento.data = valor;
        elemento.next= primeiro;
        primeiro = elemento;
    }

    public void addLast(T valor) throws Exception {
        if(ListaVazia()){
            addFirst(valor);
        }
        int tamanho = Size();
        Lista<T> elemento = new Lista<>();
        elemento.data = valor;
        elemento.next = null;
        Lista<T> ultimo = getNo(tamanho - 1);
        ultimo.next = elemento;
    }

    public void add(T valor, int pos) throws Exception {
        int tamanho = Size();
        if (pos < 0 || pos > tamanho){
            throw new Exception("posição invalida");
        }
        if(pos == 0){
            addFirst(valor);
        } else if (pos == tamanho) {
            addLast(valor);
        }else{
            Lista<T> elemento = new Lista<>();
            elemento.data = valor;
            Lista<T> anterior = getNo(pos -1);
            elemento.next = anterior.next;
            anterior.next = elemento;
        }
    }

    public void removeFirst() throws Exception{
        if(ListaVazia()){
            throw new Exception("Lista Vazia");
        }
        primeiro = primeiro.next;
    }

    public void removeLast() throws Exception{
        if(ListaVazia()){
            throw new Exception("Lista Vazia");
        }
        int tamanho = Size();
        if(tamanho == 1){
            removeFirst();
        }else{
            Lista<T> penultimo = getNo(tamanho - 2);
            penultimo.next = null;
        }
    }

    public void remove(int pos) throws Exception{
        if(ListaVazia()){
            throw new Exception("Lista Vazia");
        }
        int tamanho = Size();
        if(pos < 0 || pos > tamanho - 1){
            throw new Exception("posição invalida");
        }
        if(pos == 0){
            removeFirst();
        } else if (pos == tamanho - 1) {
            removeLast();
        }else {
            Lista<T> anterior = getNo(pos - 1);
            Lista<T> atual = getNo(pos);
            anterior.next = atual.next;
        }
    }

    public T get (int pos) throws Exception{
        if(ListaVazia()){
            throw new Exception("Lista Vazia");
        }
        int tamanho = Size();
        if(pos < 0 || pos > tamanho - 1){
            throw new Exception("Posição invalida");
        }
        int cont = 0;
        Lista<T> auxiliar = primeiro;
        while(cont < pos){
            auxiliar = auxiliar.next;
            cont++;
        }
        return auxiliar.data;
    }

}
