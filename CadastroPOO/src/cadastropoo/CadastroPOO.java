package cadastropoo;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Scanner;
import model.*;

public class CadastroPOO implements Serializable {
    private static final long serialVersionUID = 1L;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // PARTE 1
//        PessoaFisicaRepo repo1 = new PessoaFisicaRepo();
//        PessoaFisica pf1 = new PessoaFisica(1, "Ana", "11111111111", 25);
//        PessoaFisica pf2 = new PessoaFisica(2, "Carlos", "22222222222", 52);
//        repo1.inserir(pf1);
//        repo1.inserir(pf2);
//        repo1.persistir("repoFisico.dat");
//        System.out.println("Dados de Pessoa Fisica Armazenados.");
//        PessoaFisicaRepo repo2 = PessoaFisicaRepo.recuperar("repoFisico.dat");
//        System.out.println("Dados de Pessoa Fisica Recuperados.");
//        for(PessoaFisica pf : repo2.obterTodos()){
//            pf.exibir();
//        }
//        
//        PessoaJuridicaRepo repo3 = new PessoaJuridicaRepo();
//        PessoaJuridica pj1 = new PessoaJuridica(3, "XPTO Sales", "33333333333333");
//        PessoaJuridica pj2 = new PessoaJuridica(4, "XPTO Solutions", "444444444444444");
//        repo3.inserir(pj1);
//        repo3.inserir(pj2);
//        repo3.persistir("repoJuridico.dat");
//        System.out.println("Dados de Pessoa Juridica Armazenados.");
//        PessoaJuridicaRepo repo4 = PessoaJuridicaRepo.recuperar("repoJuridico.dat");
//        System.out.println("Dados de Pessoa Juridica Recuperados.");
//        for(PessoaJuridica pj : repo4.obterTodos()){
//            pj.exibir();
//        }
        
        ///////////////////////////////////////////////////////////////////////
        
        // PARTE 2
        
        PessoaFisicaRepo repositorioFisico = new PessoaFisicaRepo();
        PessoaJuridicaRepo repositorioJuridico = new PessoaJuridicaRepo();
        Scanner scan = new Scanner(System.in);

        while (true) {
            System.out.println("================================");
            System.out.println("1 - Incluir Pessoa");
            System.out.println("2 - Alterar Pessoa");
            System.out.println("3 - Excluir Pessoa");
            System.out.println("4 - Buscar pelo Id");
            System.out.println("5 - Exibir Todos");
            System.out.println("6 - Persistir Dados");
            System.out.println("7 - Recuperar Dados");
            System.out.println("0 - Finalizar Programa");
            System.out.println("================================");

            String userNum = scan.nextLine();

            switch (userNum) {
                case "1":
                    incluirPessoa(scan, repositorioFisico, repositorioJuridico);
                    break;
                case "2":
                    alterarPessoa(scan, repositorioFisico, repositorioJuridico);
                    break;
                case "3":
                    excluirPessoa(scan, repositorioFisico, repositorioJuridico);
                    break;
                case "4":
                    buscarPessoa(scan, repositorioFisico, repositorioJuridico);
                    break;
                case "5":
                    exibirTodos(scan, repositorioFisico, repositorioJuridico);
                    break;
                case "6":
                    salvar(scan, repositorioFisico, repositorioJuridico);
                    break;
                case "7":
                    recuperar(scan, repositorioFisico, repositorioJuridico);
                    break;
                case "0":
                    System.out.println("Finalizando o programa...");
                    scan.close();
                    return;
                default:
                    System.out.println("Opção Invalida. Tente novamente.");
            }
        }
    }

    private static void incluirPessoa(Scanner scan, PessoaFisicaRepo repositorioFisico, PessoaJuridicaRepo repositorioJuridico) {
        System.out.println("F - Pessoa Fisica | J - Pessoa Juridica");
        String incluirTipoPessoa = scan.nextLine().toUpperCase();
        if (incluirTipoPessoa.equals("F") || incluirTipoPessoa.equals("J")) {
            try {
                if (incluirTipoPessoa.equals("F")) {
                    System.out.println("Digite o Id da pessoa: ");
                    int PFnumId = Integer.parseInt(scan.nextLine());
                    System.out.println("Insira os dados...");
                    System.out.println("Nome: ");
                    String PFnome = scan.nextLine();
                    System.out.println("CPF: ");
                    String CPF = scan.nextLine();
                    System.out.println("Idade: ");
                    int PFIdade = Integer.parseInt(scan.nextLine());
                    PessoaFisica pf = new PessoaFisica(PFnumId, PFnome, CPF, PFIdade);
                    repositorioFisico.inserir(pf);
                    System.out.println("Incluido com sucesso!");
                } else if (incluirTipoPessoa.equals("J")) {
                    System.out.println("Digite o Id da pessoa: ");
                    int PJnumId = Integer.parseInt(scan.nextLine());
                    System.out.println("Insira os dados...");
                    System.out.println("Nome: ");
                    String PJnome = scan.nextLine();
                    System.out.println("CNPJ: ");
                    String CNPJ = scan.nextLine();
                    PessoaJuridica pj = new PessoaJuridica(PJnumId, PJnome, CNPJ);
                    repositorioJuridico.inserir(pj);
                    System.out.println("Incluido com sucesso!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro ao cadastrar ID ou Idade, favor usar numeros!");
                System.out.println("Erro: " + e.getMessage());
            }
        } else {
            System.out.println("Tipo Invalido. Por favor selecione com 'F' ou 'J'.");
        }
    }

    private static void alterarPessoa(Scanner scan, PessoaFisicaRepo repositorioFisico, PessoaJuridicaRepo repositorioJuridico) {
        System.out.println("F - Pessoa Fisica | J - Pessoa Juridica");
        String alterarTipoPessoa = scan.nextLine().toUpperCase();
        if (alterarTipoPessoa.equals("F") || alterarTipoPessoa.equals("J")) {
            try {
                if (alterarTipoPessoa.equals("F")) {
                    System.out.println("Informe o Id da pessoa: ");
                    int AltIdPF = Integer.parseInt(scan.nextLine());
                    System.out.println("Insira os dados...");
                    System.out.println("Nome: ");
                    String PFnome = scan.nextLine();
                    System.out.println("CPF: ");
                    String CPF = scan.nextLine();
                    System.out.println("Idade: ");
                    int PFIdade = Integer.parseInt(scan.nextLine());
                    PessoaFisica AltPF = new PessoaFisica(AltIdPF, PFnome, CPF, PFIdade);
                    repositorioFisico.alterar(AltIdPF, AltPF);
                    System.out.println("Alterado com sucesso!");
                } else if (alterarTipoPessoa.equals("J")) {
                    System.out.println("Digite o Id da pessoa: ");
                    int AltIdPJ = Integer.parseInt(scan.nextLine());
                    System.out.println("Insira os dados...");
                    System.out.println("Nome: ");
                    String PJnome = scan.nextLine();
                    System.out.println("CNPJ: ");
                    String CNPJ = scan.nextLine();
                    PessoaJuridica AltPJ = new PessoaJuridica(AltIdPJ, PJnome, CNPJ);
                    repositorioJuridico.alterar(AltIdPJ, AltPJ);
                    System.out.println("Alterado com sucesso!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        } else {
            System.out.println("Tipo Invalido. Por favor selecione com 'F' ou 'J'.");
        }
    }
    
    private static void excluirPessoa(Scanner scan, PessoaFisicaRepo repositorioFisico, PessoaJuridicaRepo repositorioJuridico) {
        System.out.println("F - Pessoa Fisica | J - Pessoa Juridica");
        String excluirTipoPessoa = scan.nextLine().toUpperCase();
        if (excluirTipoPessoa.equals("F") || excluirTipoPessoa.equals("J")) {
            try {
                if (excluirTipoPessoa.equals("F")) {
                    System.out.println("Informe o Id: ");
                    int ExcluirPF = Integer.parseInt(scan.nextLine());
                    repositorioFisico.excluir(ExcluirPF);
                    System.out.println("Excluido com sucesso!");
                } else if (excluirTipoPessoa.equals("J")) {
                    System.out.println("Informe o Id: ");
                    int ExcluirPJ = Integer.parseInt(scan.nextLine());
                    repositorioJuridico.excluir(ExcluirPJ);
                    System.out.println("Excluido com sucesso!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: O Id deve ser um numero\n" + e.getMessage());
            }
        } else {
            System.out.println("Tipo Invalido. Por favor selecione com 'F' ou 'J'.");
        }
    }

    private static void buscarPessoa(Scanner scan, PessoaFisicaRepo repositorioFisico, PessoaJuridicaRepo repositorioJuridico) {
        System.out.println("F - Pessoa Fisica | J - Pessoa Juridica");
        String obterTipoPessoa = scan.nextLine().toUpperCase();
        if (obterTipoPessoa.equals("F") || obterTipoPessoa.equals("J")) {
            try {
                if (obterTipoPessoa.equals("F")) {
                    System.out.println("Informe o Id: ");
                    int ObterPF = Integer.parseInt(scan.nextLine());
                    PessoaFisica pf = repositorioFisico.obter(ObterPF);
                    if (pf != null) {
                        pf.exibir();
                    } else {
                        System.out.println("Pessoa Física nao encontrada.");
                    }
                } else if (obterTipoPessoa.equals("J")) {
                    System.out.println("Informe o Id: ");
                    int ObterPJ = Integer.parseInt(scan.nextLine());
                    PessoaJuridica pj = repositorioJuridico.obter(ObterPJ);
                    if (pj != null) {
                        pj.exibir();
                    } else {
                        System.out.println("Pessoa Juridica nao encontrada.");
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: O Id deve ser um número\n" + e.getMessage());
            }
        } else {
            System.out.println("Tipo Invalido. Por favor selecione com 'F' ou 'J'.");
        }
    }

    private static void exibirTodos(Scanner scan, PessoaFisicaRepo repositorioFisico, PessoaJuridicaRepo repositorioJuridico) {
        System.out.println("F - Pessoa Fisica | J - Pessoa Juridica");
        String obterTodosTipo = scan.nextLine().toUpperCase();
        if (obterTodosTipo.equals("F") || obterTodosTipo.equals("J")) {
            if (obterTodosTipo.equals("F")) {
                List<PessoaFisica> todasPessoasFisicas = repositorioFisico.obterTodos();
                for (PessoaFisica pf : todasPessoasFisicas) {
                    pf.exibir();
                }
            } else if (obterTodosTipo.equals("J")) {
                List<PessoaJuridica> todasPessoasJuridicas = repositorioJuridico.obterTodos();
                for (PessoaJuridica pj : todasPessoasJuridicas) {
                    pj.exibir();
                }
            }
        } else {
            System.out.println("Tipo Invalido. Por favor selecione com 'F' ou 'J'.");
        }
    }
    
    private static void salvar(Scanner scan, PessoaFisicaRepo repositorioFisico, PessoaJuridicaRepo repositorioJuridico) throws IOException {
        System.out.println("Qual repositorio deseja salvar?");
        System.out.println("F - Repositorio P.Fisica | J - Repositorio P.Jurídica");
        String saveP = scan.nextLine().toUpperCase();
        if (saveP.equals("F") || saveP.equals("J")) {
            System.out.println("Nome do arquivo: ");
            String prefixo = scan.nextLine();
            if (saveP.equals("F")) {
                repositorioFisico.persistir(prefixo + ".fisica.bin");
                System.out.println("Salvo como: " + prefixo + ".fisica.bin");
            } else if (saveP.equals("J")) {
                repositorioJuridico.persistir(prefixo + ".juridica.bin");
                System.out.println("Salvo como: " + prefixo + ".juridica.bin");
            }
        } else {
            System.out.println("Tipo Invalido. Por favor selecione com 'F' ou 'J'.");
        }
    }
    
    private static void recuperar(Scanner scan, PessoaFisicaRepo repositorioFisico, PessoaJuridicaRepo repositorioJuridico) throws ClassNotFoundException, IOException {
        System.out.println("Qual repositorio deseja recuperar?");
        System.out.println("F - Repositorio P.Fisica | J - Repositorio P.Juridica");
        String tipo = scan.nextLine().toUpperCase();
        if (tipo.equals("F")) {
            System.out.println("Nome do arquivo: ");
            String nomeArquivo = scan.nextLine();
            PessoaFisicaRepo repositorioRecuperado = PessoaFisicaRepo.recuperar(nomeArquivo + ".fisica.bin");
            repositorioFisico.substituirRepositorio(repositorioRecuperado);
            System.out.println("Repositorio de Pessoas Fisicas recuperado com sucesso.");
        } else if (tipo.equals("J")) {
            System.out.println("Nome do arquivo: ");
            String nomeArquivo = scan.nextLine();
            PessoaJuridicaRepo repositorioRecuperado = PessoaJuridicaRepo.recuperar(nomeArquivo + ".juridica.bin");
            repositorioJuridico.substituirRepositorio(repositorioRecuperado);
            System.out.println("Repositorio de Pessoas Juridicas recuperado com sucesso.");
        } else {
            System.out.println("Tipo Invalido. Por favor selecione com 'F' ou 'J'.");
        }
    }
}
