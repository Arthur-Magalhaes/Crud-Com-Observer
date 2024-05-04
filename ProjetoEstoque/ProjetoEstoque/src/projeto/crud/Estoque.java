package projeto.crud;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import projeto.models.Produto;
import projeto.observers.Observer;

public class Estoque {
    private Map<String, Produto> produtos;
    private List<Observer> observers;
    private boolean notificado = false;

    public Estoque() {
        this.produtos = new HashMap<>();
        this.observers = new ArrayList<>();
    }

    public void adicionarProduto(String nome, double preco, int quantidade) {
        if (produtos.containsKey(nome)) {
            Produto produto = produtos.get(nome);
            produto.setQuantidade(produto.getQuantidade() + quantidade);
        } else {
            produtos.put(nome, new Produto(nome, preco, quantidade));
        }
        notificarObservers();
    }

    public void removerProduto(String nome) {
        if (produtos.containsKey(nome)) {
            produtos.remove(nome);
            notificarObservers();
        }
    }

    public void atualizarProduto(String nome, double preco, int quantidade) {
        if (produtos.containsKey(nome)) {
            Produto produto = produtos.get(nome);
            produto.setPreco(preco);
            produto.setQuantidade(quantidade);
            notificarObservers();
        }
    }

    public Map<String, Produto> listarProdutos() {
        return new HashMap<>(produtos);
    }

    public double calcularValorTotal() {
        double total = 0;
        for (Produto produto : produtos.values()) {
            total += produto.getPreco() * produto.getQuantidade();
        }
        return total;
    }

    public void adicionarObserver(Observer observer) {
        observers.add(observer);
    }

    private void notificarObservers() {
        double valorTotal = calcularValorTotal();
        if (valorTotal >= 1000 && !notificado) {
            notificado = true;
            for (Observer observer : observers) {
                observer.notificar(valorTotal);
            }
        }
    }
}
