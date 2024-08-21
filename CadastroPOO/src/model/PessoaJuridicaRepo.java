package model;
import java.util.ArrayList;
import java.util.List;
import java.io.*;


public class PessoaJuridicaRepo implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<PessoaJuridica> pessoas;
    
    public PessoaJuridicaRepo(){
        this.pessoas = new ArrayList<>();
    }
    
    public void inserir(PessoaJuridica pessoaJuridica){
        pessoas.add(pessoaJuridica);
    }
    
    public boolean alterar(int id, PessoaJuridica novaPessoaJuridica){
        for(int i = 0; i < pessoas.size(); i++){
            PessoaJuridica pessoa = pessoas.get(i);
            if(pessoa.getId() == id){
                pessoas.set(i, novaPessoaJuridica);
                return true;
            }
        }
        return false;
    }
    
    public boolean excluir(int id){
        return pessoas.removeIf(pessoa -> pessoa.getId() == id);
    }
    
    public PessoaJuridica obter(int id){
        for(PessoaJuridica pessoa : pessoas){
            if(pessoa.getId() == id){
                return pessoa;
            }
        }
        return null;
    }
    
    public List<PessoaJuridica> obterTodos(){
        return new ArrayList<>(pessoas);
    }
    
    public void persistir(String nomeArquivo) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
            oos.writeObject(this);
        }
    }
    
    public static PessoaJuridicaRepo recuperar(String nomeArquivo) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomeArquivo))) {
            return (PessoaJuridicaRepo) ois.readObject();
        }
    }
    
    public void substituirRepositorio(PessoaJuridicaRepo novoRepositorio) {
        this.pessoas = novoRepositorio.pessoas;
    }
}
