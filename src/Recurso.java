public class Recurso {
    private final int id;
    private final String nomeDoRecurso;
    private final String categoria; 
    private boolean disponivel;
    private final double valorEstimado;

    public Recurso(int id, String nomeDoRecurso, String categoria, boolean disponivel, double valorEstimado) {
        this.id = id;
        this.nomeDoRecurso = nomeDoRecurso;
        this.categoria = categoria;
        this.disponivel = disponivel;
        this.valorEstimado = valorEstimado;
    }

    public int getId() {
        return id;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public double getValorEstimado() {
        return valorEstimado;
    }

    public boolean podeSerAlocado() {     return disponivel && valorEstimado <= 5000; }

    public String toString() {
        return "Recurso{id=" + id +
                ", nomeDoRecurso='" + nomeDoRecurso + '\'' +
                ", categoria='" + categoria + '\'' +
                ", disponivel=" + disponivel +
                ", valorEstimado=" + valorEstimado +
                '}';
    }
}

