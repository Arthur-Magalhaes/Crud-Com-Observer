package projeto.main;

import java.util.Map;

import projeto.crud.Estoque;
import projeto.models.Produto;
import projeto.observers.Gerente;

public class Main {
    public static void main(String[] args) {
        Estoque estoque = new Estoque();
        Gerente gerente = new Gerente("Adagoberto");
        estoque.adicionarObserver(gerente);

        estoque.adicionarProduto("P1", 300, 2);
        estoque.adicionarProduto("P2", 200, 3);
        estoque.adicionarProduto("P3", 200, 2);
        estoque.adicionarProduto("P4", 400, 1);

        estoque.atualizarProduto("P3", 400, 7);

        estoque.removerProduto("P4");

        Map<String, Produto> produtos = estoque.listarProdutos();
        for (Map.Entry<String, Produto> entry : produtos.entrySet()) {
            Produto produto = entry.getValue();
            System.out.printf("Produto: %s, Preço: %.2f, Quantidade: %d%n", produto.getNome(), produto.getPreco(), produto.getQuantidade());
        }

        System.out.printf("Valor total do estoque: %.2f reais%n", estoque.calcularValorTotal());
    }
}
