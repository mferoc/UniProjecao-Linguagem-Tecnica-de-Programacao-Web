
package br.com.mongo.dao;

import br.com.mongo.modelo.Produto;
import br.com.mongo.util.ProdutoCodec;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;


public class ProdutoDAO {
    
    
    private MongoClient cliente;
    private MongoDatabase bancoDeDados;
    
    
    public void salvar(Produto prod) {
        abrirConexao();
        MongoCollection<Produto> produtos = this.bancoDeDados.getCollection("produtos", Produto.class);
        produtos.insertOne(prod);
        fecharConexao();
    }
    
    
     private void abrirConexao() {
        Codec<Document> codec = MongoClient.getDefaultCodecRegistry().get(Document.class);
        ProdutoCodec prodCodec = new ProdutoCodec(codec);

        CodecRegistry registro = CodecRegistries.fromRegistries(
                MongoClient.getDefaultCodecRegistry(),
                CodecRegistries.fromCodecs(prodCodec));

        MongoClientOptions options = MongoClientOptions.builder().codecRegistry(registro).build();

        cliente = new MongoClient("localhost:27017", options);
        bancoDeDados = cliente.getDatabase("test");
    }

    private void fecharConexao() {
        this.cliente.close();
    }
    
}
