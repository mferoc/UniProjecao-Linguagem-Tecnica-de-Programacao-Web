
package br.com.teste.mongo.util;

import br.com.teste.mongo.modelo.Aluno;
import br.com.teste.mongo.modelo.Curso;
import br.com.teste.mongo.modelo.Habilidade;
import br.com.teste.mongo.modelo.Nota;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.bson.BsonReader;
import org.bson.BsonString;
import org.bson.BsonValue;
import org.bson.BsonWriter;
import org.bson.Document;
import org.bson.codecs.Codec;
import org.bson.codecs.CollectibleCodec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.types.ObjectId;


public class AlunoCodec implements CollectibleCodec<Aluno>{
    
    private Codec<Document> codec;

    public AlunoCodec(Codec<Document> codec) {
        this.codec = codec;
    }


    @Override
    public Aluno generateIdIfAbsentFromDocument(Aluno aluno) {
        return documentHasId(aluno) ? aluno.criaId() : aluno;
    }

    @Override
    public boolean documentHasId(Aluno aluno) {
        return aluno.getId() == null;
    }

    @Override
    public BsonValue getDocumentId(Aluno aluno) {
        if(!documentHasId(aluno)){
            throw new IllegalStateException("Esse Documento não tem id");
        }
            return new BsonString(aluno.getId().toHexString());
    }

    @Override
    public void encode(BsonWriter writer, Aluno aluno, EncoderContext encoder) {
        ObjectId id = aluno.getId();
        String nome = aluno.getNome();
        String email = aluno.getEmail();
        Date dataNascimento = aluno.getDataNascimento();
        List<Habilidade> habilidades = aluno.getHabilidades();
        Curso curso = aluno.getCurso();
        List<Nota> notas = aluno.getNotas();
        //Contato contato = aluno.getContato();

        Document documento = new Document();
        documento.put("_id", id);
        documento.put("nome", nome);
        documento.put("email", email);
        documento.put("data_nascimento", dataNascimento);
        documento.put("curso", new Document("nome", curso.getNome()));
        
        if (habilidades != null) {
            List<Document> habilidadesDocument = new ArrayList<Document>();
            for (Habilidade habilidade : habilidades) {
                habilidadesDocument.add(
                        new Document().append("nome", habilidade.getNome()).append("nivel", habilidade.getNivel()));
            }
            documento.put("habilidades", habilidadesDocument);
        }

        if (notas != null) {
            List<Document> notasDocument = new ArrayList<Document>();
            for (Nota nota : notas) {
                notasDocument.add(new Document().append("valor", nota.getValor()));
            }
            documento.put("notas", notasDocument);

        }

        codec.encode(writer, documento, encoder);
    }


    @Override
    public Class<Aluno> getEncoderClass() {
        return Aluno.class;
    }

    @Override
    public Aluno decode(BsonReader reader, DecoderContext decoder) {
        Document document = codec.decode(reader, decoder);
        Aluno aluno = new Aluno();

        aluno.setId(document.getObjectId("_id"));
        aluno.setNome(document.getString("nome"));
        aluno.setDataNascimento(document.getDate("data_nascimento"));
        aluno.setEmail(document.getString("email"));
       
        return aluno;
    }
    
    
}
