package br.com.teste.mongo.dao;

import br.com.teste.mongo.modelo.Aluno;
import br.com.teste.mongo.util.AlunoCodec;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.types.ObjectId;

public class AlunoDAO {

    private MongoClient cliente;
    private MongoDatabase bancoDeDados;

    public void salvar(Aluno aluno) {
        abrirConexao();
        MongoCollection<Aluno> alunos = this.bancoDeDados.getCollection("alunos", Aluno.class);
        alunos.insertOne(aluno);
        fecharConexao();
    }

    public List<Aluno> getTodosAlunos() {
        abrirConexao();
        MongoCollection<Aluno> alunos = this.bancoDeDados.getCollection("alunos", Aluno.class);
        MongoCursor<Aluno> resultado = alunos.find().iterator();
        List<Aluno> alunosEncontrados = new ArrayList<>();

        while (resultado.hasNext()) {
            Aluno aluno = resultado.next();
            alunosEncontrados.add(aluno);
        }

        fecharConexao();
        return alunosEncontrados;

    }

    public Aluno getAlunoPorId(String id) {
        abrirConexao();

        MongoCollection<Aluno> alunos = this.bancoDeDados.getCollection("alunos", Aluno.class);
        Aluno aluno = alunos.find(Filters.eq("_id", new ObjectId(id))).first();

        fecharConexao();
        return aluno;
    }

    public List<Aluno> getAlunoPorNome(String nome) {
        abrirConexao();

        MongoCollection<Aluno> alunosCollection = this.bancoDeDados.getCollection("alunos", Aluno.class);
        MongoCursor<Aluno> resultados = alunosCollection.find(Filters.eq("nome", nome), Aluno.class).iterator();
        List<Aluno>  alunos = new ArrayList<>();

        while (resultados.hasNext()) {
            alunos.add(resultados.next());
        }

        fecharConexao();
        return alunos;
    }

    private void abrirConexao() {
        Codec<Document> codec = MongoClient.getDefaultCodecRegistry().get(Document.class);
        AlunoCodec alunoCodec = new AlunoCodec(codec);

        CodecRegistry registro = CodecRegistries.fromRegistries(
                MongoClient.getDefaultCodecRegistry(),
                CodecRegistries.fromCodecs(alunoCodec));

        MongoClientOptions options = MongoClientOptions.builder().codecRegistry(registro).build();

        cliente = new MongoClient("localhost:27017", options);
        bancoDeDados = cliente.getDatabase("test");
    }

    private void fecharConexao() {
        this.cliente.close();
    }

}
