package jsonvalues.mongo;

import jsonvalues.JsArray;
import org.bson.codecs.BsonTypeClassMap;
import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;

class JsArrayCodecProvider implements CodecProvider {

    private BsonTypeClassMap typeClassMap;

    public JsArrayCodecProvider(final BsonTypeClassMap typeClassMap) {
        this.typeClassMap = typeClassMap;
    }

    @Override
    public <T> Codec<T> get(final Class<T> aclass,
                            final CodecRegistry codecRegistry) {
        if (aclass == JsArray.class) {
            return (Codec<T>) new JsArrayCodec(codecRegistry,typeClassMap);
        }
        return null;
    }
}
