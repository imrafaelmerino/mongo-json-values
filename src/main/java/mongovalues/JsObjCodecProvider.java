package mongovalues;

import jsonvalues.JsObj;
import org.bson.codecs.BsonTypeClassMap;
import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;

class JsObjCodecProvider implements CodecProvider {

    private BsonTypeClassMap typeClassMap;

    public JsObjCodecProvider(final BsonTypeClassMap typeClassMap) {
        this.typeClassMap = typeClassMap;
    }

    @Override
    public <T> Codec<T> get(final Class<T> aclass,
                            final CodecRegistry codecRegistry) {
        if (aclass == JsObj.class) {
            return (Codec<T>) new JsObjCodec(codecRegistry, typeClassMap);
        }
        return null;
    }
}
