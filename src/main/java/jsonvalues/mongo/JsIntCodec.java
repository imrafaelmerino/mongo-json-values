package jsonvalues.mongo;
import jsonvalues.JsInt;
import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.codecs.IntegerCodec;

class JsIntCodec implements Codec<JsInt> {

    private static final IntegerCodec integerCodec = new IntegerCodec();
    @Override
    public void encode(final BsonWriter writer, final JsInt jsInt, final EncoderContext encoderContext) {
       integerCodec.encode(writer,jsInt.value,encoderContext);
    }

    @Override
    public JsInt decode(final BsonReader reader, final DecoderContext decoderContext) {
        return JsInt.of(integerCodec.decode(reader,
                                            decoderContext)
                       );
    }

    @Override
    public Class<JsInt> getEncoderClass() {
        return JsInt.class;
    }
}
