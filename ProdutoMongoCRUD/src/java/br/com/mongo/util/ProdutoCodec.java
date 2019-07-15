
package br.com.mongo.util;

import br.com.mongo.modelo.Produto;

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


public class ProdutoCodec  implements CollectibleCodec<Produto>{

    //criar um objeto codec do tipo Coded<Document>
    private Codec<Document> codec;
    
    //método construtor 
    public ProdutoCodec(Codec<Document> codec){
        this.codec  = codec;
    }
    
    @Override
    public Produto generateIdIfAbsentFromDocument(Produto prod) {
        return documentHasId(prod) ? prod.criaId() : prod;
    }

    @Override
    public boolean documentHasId(Produto prod) {
        return prod.getId() == null;
    }

    @Override
    public BsonValue getDocumentId(Produto prod) {
        if(!documentHasId(prod)){
            throw new IllegalStateException("Esse Documento não tem id");
        }
            return new BsonString(prod.getId().toHexString());
    
    }

    @Override
    public void encode(BsonWriter writer, Produto prod, EncoderContext ec) {
        ObjectId id = prod.getId();
        String codigo = prod.getCodigo();
        String nome = prod.getNome();
        String descricao = prod.getDescricao();
        Integer quantidade = prod.getQuantidade();
        Double preco = prod.getPreco();
        
        Document documento = new Document();
        documento.put("_id", id);
        documento.put("codigo",codigo);
        documento.put("nome", nome);
        documento.put("descricao", descricao);
        documento.put("quantidade", quantidade);
        documento.put("preco", preco);
        
        codec.encode(writer, documento, ec);
    }


    @Override
    public Class<Produto> getEncoderClass() {
        return Produto.class;
    }

    @Override
    public Produto decode(BsonReader reader, DecoderContext dc) {
        Document document = codec.decode(reader, dc);
        Produto prod = new Produto();

        prod.setId(document.getObjectId("_id"));
        prod.setNome(document.getString("nome"));
        prod.setDescricao(document.getString("descricao"));
        prod.setCodigo(document.getString("codigo"));
        prod.setQuantidade(document.getInteger("quantidade"));
        prod.setPreco(document.getDouble("preco"));
        return prod;
    }
    
}
