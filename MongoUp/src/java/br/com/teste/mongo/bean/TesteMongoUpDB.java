package br.com.teste.mongo.bean;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javax.faces.bean.ManagedBean;
import org.bson.Document;

@ManagedBean(name = "mongoBean")
public class TesteMongoUpDB {

    String mensagem;
    /*MongoClient cliente;
    MongoDatabase database;
    MongoCollection<Document> colecao;*/

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public void conectar() {

        /*cliente = new MongoClient("localhost", 27017);
        database = cliente.getDatabase("test");
        colecao = database.getCollection("alunos");
        mensagem = "";

        Document aluno = colecao.find().first();
        mensagem = aluno.toJson();
        Document novoAluno = new Document("nome", "Joao")
                .append("data_nascimento", new Date(2003, 10, 10))
                .append("curso", new Document("nome", "Historia"))
                .append("notas", Arrays.asList(10, 9, 8))
                .append("habilidades", Arrays.asList(
                        new Document().append("nome", "Ingles").append("nível", "Básico"),
                        new Document().append("nome", "Espanhol").append("nível", "Básico")));
        
        colecao.insertOne(novoAluno);

        /* for (Document cur : colecao.find()) {
            mensagem += cur.toJson().toString()+"\n";
        }*/
        
        
        
        MongoClient cliente = new MongoClient();
        MongoDatabase bancoDeDados = cliente.getDatabase("test");
        MongoCollection<Document> alunos = bancoDeDados.getCollection("alunos");
        Document aluno = alunos.find().first();
        
        //inserir um novo registro no banco 
        /*Document novoAluno = new Document("nome", "Joao")
                .append("data_nascimento", new Date(2003, 10, 10))
                .append("curso", new Document("nome", "Historia"))
                .append("notas", Arrays.asList(10, 9, 8))
                .append("habilidades", Arrays.asList(
                        new Document().append("nome", "Ingles").append("nível", "Básico"),
                        new Document().append("nome", "Espanhol").append("nível", "Básico")));

        alunos.insertOne(novoAluno);*/
        
        //Atualizar 
       /* alunos.updateOne(
                Filters.eq("nome", "Joao"), 
                new Document("$set", new Document("nome", "Joao da Silva Lemos")));*/
        
        
        //Deletar
        // alunos.deleteOne(Filters.eq("nome", "Joao"));    
        
        //System.out.println(aluno);
        cliente.close();
        mensagem = aluno.toJson();
    }
}
