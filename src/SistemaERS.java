import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SistemaERS {
    private final List<Colaborador> colaboradores;
    private final List<Recurso> recursos;
    private final List<Alocacao> alocacoes;

    private int proximoColaboradorId;
    private int proximoRecursoId;

    public SistemaERS() {
        this.colaboradores = new ArrayList<>();
        this.recursos = new ArrayList<>();
        this.alocacoes = new ArrayList<>();
        this.proximoColaboradorId = 1;
        this.proximoRecursoId = 1;
    }

    public void cadastrarColaborador(String nome, String cargo, double salario, String dataDeAdmissao) {
        int id = proximoColaboradorId++;
        Colaborador colaborador = new Colaborador(id, nome, cargo, salario, dataDeAdmissao);
        colaboradores.add(colaborador);
        System.out.println("Colaborador cadastrado com sucesso: " + colaborador.getId());
    }

    public void cadastrarRecurso(String nomeDoRecurso, String categoria, double valorEstimado) {
        int id = proximoRecursoId++;
        // Regra simples: ao cadastrar, o recurso começa disponível (podeSerAlocado depende também do valor).
        Recurso recurso = new Recurso(id, nomeDoRecurso, categoria, true, valorEstimado);
        recursos.add(recurso);
        System.out.println("Recurso cadastrado com sucesso: " + recurso.getId());
    }

    public void alocarRecurso(int colaboradorId, int recursoId) {
        Colaborador colaborador = buscarColaboradorPorId(colaboradorId);
        if (colaborador == null) {
            System.out.println("Colaborador não encontrado: " + colaboradorId);
            return;
        }
        if (!colaborador.isAtivo()) {
            System.out.println("Colaborador está inativo e não pode receber recursos.");
            return;
        }

        Recurso recurso = buscarRecursoPorId(recursoId);
        if (recurso == null) {
            System.out.println("Recurso não encontrado: " + recursoId);
            return;
        }

        if (!recurso.isDisponivel()) {
            System.out.println("Recurso já está alocado para outro colaborador.");
            return;
        }

        // Regra de negócio: recursos com valor acima de 5000 exigem autorização especial (ainda não implementada).
        if (recurso.getValorEstimado() > 5000) {
            System.out.println("Autorização especial necessária: valor estimado acima de 5000.");
            return;
        }

        // Obrigatório no desafio: podeSerAlocado usa disponivel && valorEstimado <= 5000
        if (!recurso.podeSerAlocado()) {
            System.out.println("Recurso não pode ser alocado conforme as regras do sistema.");
            return;
        }

        // Alocar: marca como indisponível e registra a movimentação.
        recurso.setDisponivel(false);
        String dataHoje = LocalDate.now().toString();
        alocacoes.add(new Alocacao(colaboradorId, recursoId, dataHoje, "ALOCADO"));
        System.out.println("Recurso alocado com sucesso.");
    }

    public void devolverRecurso(int colaboradorId, int recursoId) {
        Colaborador colaborador = buscarColaboradorPorId(colaboradorId);
        if (colaborador == null) {
            System.out.println("Colaborador não encontrado: " + colaboradorId);
            return;
        }

        Recurso recurso = buscarRecursoPorId(recursoId);
        if (recurso == null) {
            System.out.println("Recurso não encontrado: " + recursoId);
            return;
        }

        if (recurso.isDisponivel()) {
            System.out.println("Recurso já está disponível (nada a devolver).");
            return;
        }

        // Confirma se o recurso está atualmente alocado para esse colaborador.
        if (!recursoAtualmenteAlocadoParaColaborador(recursoId, colaboradorId)) {
            System.out.println("Este recurso não está registrado como alocado para o colaborador informado.");
            return;
        }

        recurso.setDisponivel(true);
        String dataHoje = LocalDate.now().toString();
        alocacoes.add(new Alocacao(colaboradorId, recursoId, dataHoje, "DEVOLVIDO"));
        System.out.println("Recurso devolvido com sucesso.");
    }

    public void promoverColaborador(int colaboradorId, String novoCargo, double novoSalario) {
        Colaborador colaborador = buscarColaboradorPorId(colaboradorId);
        if (colaborador == null) {
            System.out.println("Colaborador não encontrado: " + colaboradorId);
            return;
        }
        colaborador.promover(novoCargo, novoSalario);
        System.out.println("Promoção aplicada com sucesso.");
    }

    public Colaborador buscarColaboradorPorId(int colaboradorId) {
        for (Colaborador c : colaboradores) {
            if (c.getId() == colaboradorId) {
                return c;
            }
        }
        return null;
    }

    public Recurso buscarRecursoPorId(int recursoId) {
        for (Recurso r : recursos) {
            if (r.getId() == recursoId) {
                return r;
            }
        }
        return null;
    }

    public void exibirColaboradores() {
        if (colaboradores.isEmpty()) {
            System.out.println("Nenhum colaborador cadastrado.");
            return;
        }
        System.out.println("=== Colaboradores ===");
        for (Colaborador c : colaboradores) {
            System.out.println(c);
        }
    }

    public void exibirRecursos() {
        if (recursos.isEmpty()) {
            System.out.println("Nenhum recurso cadastrado.");
            return;
        }
        System.out.println("=== Recursos ===");
        for (Recurso r : recursos) {
            System.out.println(r);
        }
    }

    public void exibirAlocacoes() {
        if (alocacoes.isEmpty()) {
            System.out.println("Nenhuma alocação registrada.");
            return;
        }
        System.out.println("=== Alocações (histórico) ===");
        for (Alocacao a : alocacoes) {
            System.out.println(a);
        }
    }

    // Inovação: cálculo do custo total de equipamentos atualmente alocados por colaborador.
    public double calcularCustoTotalAlocadoPorColaborador(int colaboradorId) {
        Colaborador colaborador = buscarColaboradorPorId(colaboradorId);
        if (colaborador == null) {
            System.out.println("Colaborador não encontrado: " + colaboradorId);
            return 0.0;
        }

        double total = 0.0;
        for (Recurso r : recursos) {
            if (!r.isDisponivel() && recursoAtualmenteAlocadoParaColaborador(r.getId(), colaboradorId)) {
                total += r.getValorEstimado();
            }
        }

        return total;
    }

    private boolean recursoAtualmenteAlocadoParaColaborador(int recursoId, int colaboradorId) {
        Alocacao ultimaMovimentacao = obterUltimaMovimentacaoPorRecurso(recursoId);
        if (ultimaMovimentacao == null) {
            return false;
        }
        if (ultimaMovimentacao.getColaboradorId() != colaboradorId) {
            return false;
        }
        String observacao = ultimaMovimentacao.getObservacao();
        return observacao != null && observacao.equalsIgnoreCase("ALOCADO");
    }

    private Alocacao obterUltimaMovimentacaoPorRecurso(int recursoId) {
        for (int i = alocacoes.size() - 1; i >= 0; i--) {
            Alocacao a = alocacoes.get(i);
            if (a.getRecursoId() == recursoId) {
                return a;
            }
        }
        return null;
    }
    public void demitirColaborador(int colaboradorId) {
        Colaborador colaborador = buscarColaboradorPorId(colaboradorId);

        if (colaborador == null) {
            System.out.println("Colaborador não encontrado: " + colaboradorId);
            return;
        }

        if (!colaborador.isAtivo()) {
            System.out.println("Colaborador já está inativo.");
            return;
        }

        // Devolve automaticamente todos os recursos desse colaborador
        for (Recurso r : recursos) {
            if (!r.isDisponivel() && recursoAtualmenteAlocadoParaColaborador(r.getId(), colaboradorId)) {
                r.setDisponivel(true);
                String dataHoje = java.time.LocalDate.now().toString();
                alocacoes.add(new Alocacao(colaboradorId, r.getId(), dataHoje, "DEVOLVIDO (AUTO - DEMISSÃO)"));
            }
        }

        colaborador.desativar();

        System.out.println("Colaborador demitido e recursos devolvidos com sucesso.");
    }
}

