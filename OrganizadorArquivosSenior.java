import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

public class OrganizadorArquivosSenior {

    public static void main(String[] args) {
        // Usa a API moderna NIO.2
        Path caminhoBase = Paths.get("."); 
        Map<String, String> organizacao = carregarMapeamento();

        System.out.println("Iniciando organização dos arquivos... 🚀\n");

        try {
            criarPastasNecessarias(caminhoBase, organizacao);
            moverArquivos(caminhoBase, organizacao);
            System.out.println("\n🔥 Organização concluída com sucesso!");
        } catch (Exception e) {
            System.err.println("❌ Erro crítico durante a execução: " + e.getMessage());
        }
    }

    /**
     * Isola a configuração dos dados. Fica mais fácil de manter ou até
     * mesmo substituir por uma leitura de arquivo .json ou .csv no futuro.
     */
    private static Map<String, String> carregarMapeamento() {
        Map<String, String> map = new HashMap<>();

        // LOGICA BASICA
        map.put("Variavel.java", "logica-basica");
        map.put("Nome.java", "logica-basica");
        map.put("Nota.java", "logica-basica");
        map.put("NumeroAleatorio.java", "logica-basica");
        map.put("MediaEscolar.java", "logica-basica");
        map.put("Apresentar.java", "logica-basica");
        map.put("Text.java", "logica-basica");
        map.put("Textos.java", "logica-basica");

        // REPETICAO
        map.put("While.java", "estruturas-de-repeticao");
        map.put("TipoWhile.java", "estruturas-de-repeticao");
        map.put("TipoFor.java", "estruturas-de-repeticao");
        map.put("TipoForEach.java", "estruturas-de-repeticao");
        map.put("TipoDoWhile.java", "estruturas-de-repeticao");
        map.put("Repeticao.java", "estruturas-de-repeticao");
        map.put("Regressivo.java", "estruturas-de-repeticao");
        map.put("Rank.java", "estruturas-de-repeticao");

        // ENTRADA E SAIDA
        map.put("ScannerAula.java", "entrada-e-saida");
        map.put("InteracaoUsuario.java", "entrada-e-saida");
        map.put("TesteNextLIne.java", "entrada-e-saida");
        map.put("Show.java", "entrada-e-saida");
        map.put("Tela.java", "entrada-e-saida");

        // OOP
        map.put("Main.java", "orientacao-a-objetos");
        map.put("User.java", "orientacao-a-objetos");
        map.put("Person.java", "orientacao-a-objetos");
        map.put("Aluno.java", "orientacao-a-objetos");
        map.put("AlunoTreino.java", "orientacao-a-objetos");
        map.put("AlunosCadastro.java", "orientacao-a-objetos");
        map.put("AlunosTexto.java", "orientacao-a-objetos");
        map.put("Casa.java", "orientacao-a-objetos");
        map.put("Cozinha.java", "orientacao-a-objetos");
        map.put("Copo.java", "orientacao-a-objetos");
        map.put("Celular.java", "orientacao-a-objetos");
        map.put("Eletronico.java", "orientacao-a-objetos");
        map.put("Ventilador.java", "orientacao-a-objetos");
        map.put("PrimeiraClasse.java", "orientacao-a-objetos");

        // LISTAS
        map.put("Lista.java", "listas-e-colecoes");
        map.put("ListaAluno.java", "listas-e-colecoes");
        map.put("ListaNome.java", "listas-e-colecoes");
        map.put("ListaNomeResposta.java", "listas-e-colecoes");
        map.put("ListaNomeResolucao.java", "listas-e-colecoes");
        map.put("ExemploLista.java", "listas-e-colecoes");

        // PROJETOS
        map.put("IMC.java", "projetos-praticos");
        map.put("IMCAluno.java", "projetos-praticos");
        map.put("IMCUltima.java", "projetos-praticos");
        map.put("FolhaPagamento.java", "projetos-praticos");
        map.put("FolhaPagamentoGF.java", "projetos-praticos");
        map.put("Orcamento.java", "projetos-praticos");
        map.put("OrcamentoMensal.java", "projetos-praticos");
        map.put("CalculadoraData.java", "projetos-praticos");
        map.put("Conversor.java", "projetos-praticos");
        map.put("Energia.java", "projetos-praticos");
        map.put("EnergiaResposta.java", "projetos-praticos");
        map.put("EnergiaRespostaTarde.java", "projetos-praticos");
        map.put("Escolha.java", "projetos-praticos");
        map.put("EscolhaManha.java", "projetos-praticos");
        map.put("EscolhaTarde.java", "projetos-praticos");
        map.put("CadastroGf.java", "projetos-praticos");
        map.put("MetodoSalario.java", "projetos-praticos");
        map.put("MetodoPessoaTrabalho.java", "projetos-praticos");
        map.put("MetodoArea.java", "projetos-praticos");
        map.put("MetodoAreaDesafio.java", "projetos-praticos");
        map.put("MetodoSeparacao.java", "projetos-praticos");
        map.put("MetodoSeparacaoMedio.java", "projetos-praticos");
        map.put("MetodoSeparacaoMedioResposta.java", "projetos-praticos");
        map.put("MetodoSeparacaoDificil.java", "projetos-praticos");
        map.put("MetodoSeparacaoDificilResposta.java", "projetos-praticos");
        map.put("MetodoSeparacaoResposta.java", "projetos-praticos");
        map.put("MetodoAula.java", "projetos-praticos");
        map.put("MaiorIdade.java", "projetos-praticos");

        return map;
    }

    /**
     * Cria as pastas apenas uma vez, usando Streams para extrair nomes únicos.
     */
    private static void criarPastasNecessarias(Path caminhoBase, Map<String, String> organizacao) {
        organizacao.values().stream()
                .distinct() // Pega apenas os nomes de pastas únicos para não tentar criar a mesma pasta 10 vezes
                .map(caminhoBase::resolve)
                .forEach(pasta -> {
                    try {
                        if (Files.notExists(pasta)) {
                            Files.createDirectories(pasta);
                        }
                    } catch (IOException e) {
                        System.err.println("⚠️ Falha ao criar pasta: " + pasta + " - " + e.getMessage());
                    }
                });
    }

    /**
     * Move os arquivos utilizando a API NIO.2, que fornece logs reais de erro.
     */
    private static void moverArquivos(Path caminhoBase, Map<String, String> organizacao) {
        for (Map.Entry<String, String> entry : organizacao.entrySet()) {
            String nomeArquivo = entry.getKey();
            String nomePasta = entry.getValue();

            Path origem = caminhoBase.resolve(nomeArquivo);
            Path destino = caminhoBase.resolve(nomePasta).resolve(nomeArquivo);

            if (Files.exists(origem)) {
                try {
                    // StandardCopyOption.REPLACE_EXISTING garante que não vai dar erro se o arquivo já estiver lá
                    Files.move(origem, destino, StandardCopyOption.REPLACE_EXISTING);
                    System.out.println("✅ Movido: " + nomeArquivo + " -> /" + nomePasta);
                } catch (IOException e) {
                    // O Files.move lança exceções detalhadas (ex: AccessDeniedException)
                    System.err.println("❌ Erro ao mover: " + nomeArquivo + " | Motivo: " + e.getMessage());
                }
            } else {
                // Arquivos que não estão na pasta atual não precisam poluir o console como erro, 
                // mas é bom ter um aviso para debug se necessário.
                // System.out.println("Ignorado (não encontrado): " + nomeArquivo);
            }
        }
    }
}