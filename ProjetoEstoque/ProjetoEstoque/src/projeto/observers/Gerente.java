package projeto.observers;

public class Gerente implements Observer{
    private String nome;

    public Gerente(String nome) {
        this.nome = nome;
    }

    @Override
    public void notificar(double valorTotal) {
        System.out.printf("Gerente %s: Valor do estoque atingiu %.2f reais%n", nome, valorTotal);
    }
}
