package model;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class PessoaFisicaRepo implements Serializable {
    private static final long serialVersionUID = 1L;
    List<PessoaFisica> pessoas;
    
    public PessoaFisicaRepo(){
        this.pessoas = new ArrayList<>();
    }
    
    public void inserir(PessoaFisica pessoaFisica){
        pessoas.add(pessoaFisica);
    }
    
    public boolean alterar(int id, PessoaFisica novaPessoaFisica){
        for(int i = 0; i < pessoas.size(); i++){
            PessoaFisica pessoa = pessoas.get(i);
            if(pessoa.getId() == id){
                pessoas.set(i, novaPessoaFisica);
                return true;
            }
        }
        return false;
    }
    
    public boolean excluir(int id){
        return pessoas.removeIf(pessoa -> pessoa.getId() == id);
    }
    
    public PessoaFisica obter(int id){
        for(PessoaFisica pessoa : pessoas){
            if(pessoa.getId() == id){
                return pessoa;
            }
        }
        return null;
    }
    
    public List<PessoaFisica> obterTodos(){
        return new ArrayList<>(pessoas);
    }
    
    public void persistir(String nomeArquivo) throws IOException{
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomeArquivo))){
            oos.writeObject(this);
        }
    }
    
    public static PessoaFisicaRepo recuperar(String nomeArquivo) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomeArquivo))) {
            return (PessoaFisicaRepo) ois.readObject();
        }
    }
    
    public void substituirRepositorio(PessoaFisicaRepo novoRepositorio) {
        this.pessoas = novoRepositorio.pessoas;
    }
}
