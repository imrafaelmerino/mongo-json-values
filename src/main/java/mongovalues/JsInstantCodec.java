package mongovalues;
import jsonvalues.JsInstant;
import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.codecs.jsr310.InstantCodec;

class JsInstantCodec implements Codec<JsInstant> {

    private static final InstantCodec instantCodec = new InstantCodec();
    @Override
    public void encode(final BsonWriter writer, final JsInstant jsInstant, final EncoderContext encoderContext) {

            instantCodec.encode(writer,jsInstant.value,encoderContext);

    }

    @Override
    public JsInstant decode(final BsonReader reader, final DecoderContext decoderContext) {

            return JsInstant.of(instantCodec.decode(reader,
                                                decoderContext)
                           );

    }

    @Override
    public Class<JsInstant> getEncoderClass() {
        return JsInstant.class;
    }
}
