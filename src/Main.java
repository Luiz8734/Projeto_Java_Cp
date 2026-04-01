import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SistemaERS sistema = new SistemaERS();

        while (true) {
            exibirMenu();
            int opcao = lerInt(scanner, "Escolha uma opção: ");

            switch (opcao) {
                case 1:
                    cadastrarColaborador(scanner, sistema);
                    break;
                case 2:
                    cadastrarRecurso(scanner, sistema);
                    break;
                case 3:
                    alocarRecurso(scanner, sistema);
                    break;
                case 4:
                    devolverRecurso(scanner, sistema);
                    break;
                case 5:
                    sistema.exibirColaboradores();
                    break;
                case 6:
                    sistema.exibirRecursos();
                    break;
                case 7:
                    sistema.exibirAlocacoes();
                    break;
                case 8:
                    calcularCustoTotal(scanner, sistema);
                    break;
                case 10:
                    demitirColaborador(scanner, sistema);
                    break;
                case 9:
                    promoverColaborador(scanner, sistema);
                    break;
                case 0:
                    System.out.println("Encerrando sistema...");
                    return;
                default:
                    System.out.println("Opção inválida.");
            }

            System.out.println();
        }
    }

    private static void exibirMenu() {
        System.out.println("===== ERS - Employee Resource System =====");
        System.out.println("1. Cadastrar Colaborador");
        System.out.println("2. Cadastrar Recurso");
        System.out.println("3. Alocar Recurso");
        System.out.println("4. Devolver Recurso");
        System.out.println("5. Exibir Colaboradores");
        System.out.println("6. Exibir Recursos");
        System.out.println("7. Exibir Alocações (histórico)");
        System.out.println("8. Inovação: Custo Total por Colaborador");
        System.out.println("9. Promover Colaborador");
        System.out.println("10. Demitir Colaborador");
        System.out.println("0. Sair");
    }

    private static void cadastrarColaborador(Scanner scanner, SistemaERS sistema) {
        String nome = lerTexto(scanner, "Nome do colaborador: ");
        String cargo = lerTexto(scanner, "Cargo: ");
        double salario = lerDouble(scanner, "Salário: ");
        String dataDeAdmissao = lerData(scanner);
        sistema.cadastrarColaborador(nome, cargo, salario, dataDeAdmissao);
    }

    private static void cadastrarRecurso(Scanner scanner, SistemaERS sistema) {
        String nomeDoRecurso = lerTexto(scanner, "Nome do recurso: ");
        String categoria = lerTexto(scanner, "Categoria ( Notebook, Monitor, Licença, Mouse): ");
        double valorEstimado = lerDouble(scanner, "Valor estimado: ");
        sistema.cadastrarRecurso(nomeDoRecurso, categoria, valorEstimado);
    }

    private static void alocarRecurso(Scanner scanner, SistemaERS sistema) {
        int colaboradorId = lerInt(scanner, "ID do colaborador: ");
        int recursoId = lerInt(scanner, "ID do recurso: ");
        sistema.alocarRecurso(colaboradorId, recursoId);
    }

    private static void devolverRecurso(Scanner scanner, SistemaERS sistema) {
        int colaboradorId = lerInt(scanner, "ID do colaborador: ");
        int recursoId = lerInt(scanner, "ID do recurso: ");
        sistema.devolverRecurso(colaboradorId, recursoId);
    }

    private static void calcularCustoTotal(Scanner scanner, SistemaERS sistema) {
        int colaboradorId = lerInt(scanner, "ID do colaborador: ");
        double total = sistema.calcularCustoTotalAlocadoPorColaborador(colaboradorId);
        System.out.println("Custo total de recursos atualmente alocados: " + total);
    }

    private static void promoverColaborador(Scanner scanner, SistemaERS sistema) {
        int colaboradorId = lerInt(scanner, "ID do colaborador: ");
        String novoCargo = lerTexto(scanner, "Novo cargo: ");
        double novoSalario = lerDouble(scanner, "Novo salário: ");
        sistema.promoverColaborador(colaboradorId, novoCargo, novoSalario);
    }

    private static int lerInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String linha = scanner.nextLine();
            try {
                return Integer.parseInt(linha.trim());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número inteiro.");
            }
        }
    }

    private static double lerDouble(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String linha = scanner.nextLine();
            try {
                return Double.parseDouble(linha.trim().replace(",", "."));
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número (ex: 234.56).");
            }
        }
    }

    private static String lerTexto(Scanner scanner, String prompt) {
        String texto = "";
        while (texto.isEmpty()) {
            System.out.print(prompt);
            texto = scanner.nextLine().trim();
            if (texto.isEmpty()) {
                System.out.println("Campo não pode ficar vazio.");
            }
        }
        return texto;
    }

    private static String lerData(Scanner scanner) {
        String data;
        while (!(data = lerTexto(scanner, "Data de admissão (DD/MM/AAAA): "))
                .matches("\\d{2}/\\d{2}/\\d{4}")) {
            System.out.println("Formato inválido. Use DD/MM/AAAA (ex: 25/03/2026).");
        }
        return data;
    }
    private static void demitirColaborador(Scanner scanner, SistemaERS sistema) {
        int colaboradorId = lerInt(scanner, "ID do colaborador: ");
        sistema.demitirColaborador(colaboradorId);
    }
}

