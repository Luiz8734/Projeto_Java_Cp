import java.util.Objects;

public class Colaborador {
    private final int id;
    private final String nome;
    private String cargo;
    private double salario;
    private final boolean ativo;
    private final String dataDeAdmissao; // formato esperado: AAAA-MM-DD

    public Colaborador(int id, String nome, String cargo, double salario, String dataDeAdmissao) {
        this.id = id;
        this.nome = nome;
        this.cargo = cargo;
        this.salario = salario;
        this.dataDeAdmissao = dataDeAdmissao;
        this.ativo = true; // regra: recém-admitido começa ativo = true
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCargo() {
        return cargo;
    }

    public double getSalario() {
        return salario;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public String getDataDeAdmissao() {
        return dataDeAdmissao;
    }

    public void promover(String novoCargo, double novoSalario) {
        this.cargo = novoCargo;
        this.salario = novoSalario;
    }

    public String toString() {
        return "Colaborador{id=" + getId() +
                ", nome='" + getNome() + '\'' +
                ", cargo='" + getCargo() + '\'' +
                ", salario=" + getSalario() +
                ", ativo=" + isAtivo() +
                ", dataDeAdmissao='" + getDataDeAdmissao() + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Colaborador that)) return false;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

