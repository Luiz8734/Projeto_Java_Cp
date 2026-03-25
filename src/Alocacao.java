public class Alocacao {
    private final int colaboradorId;
    private final int recursoId;
    private final String data; // formato AAAA-MM-DD (exemplo)
    private final String observacao;

    public Alocacao(int colaboradorId, int recursoId, String data, String observacao) {
        this.colaboradorId = colaboradorId;
        this.recursoId = recursoId;
        this.data = data;
        this.observacao = observacao;
    }

    public int getColaboradorId() {
        return colaboradorId;
    }

    public int getRecursoId() {
        return recursoId;
    }

    public String getObservacao() {
        return observacao;
    }

    @Override
    public String toString() {
        return "Alocacao{colaboradorId=" + colaboradorId +
                ", recursoId=" + recursoId +
                ", data='" + data + '\'' +
                ", observacao='" + observacao + '\'' +
                '}';
    }
}

