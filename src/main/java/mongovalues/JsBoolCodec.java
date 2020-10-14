package mongovalues;

import jsonvalues.JsBool;
import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.BooleanCodec;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

class JsBoolCodec implements Codec<JsBool> {

    private static final BooleanCodec boolCodec = new BooleanCodec();
    @Override
    public void encode(final BsonWriter writer, final JsBool jsBool, final EncoderContext encoderContext) {
       boolCodec.encode(writer,jsBool.value,encoderContext);
    }

    @Override
    public JsBool decode(final BsonReader reader, final DecoderContext decoderContext) {
        return boolCodec.decode(reader,
                                decoderContext) ? JsBool.TRUE : JsBool.FALSE;

    }

    @Override
    public Class<JsBool> getEncoderClass() {
        return JsBool.class;
    }
}
