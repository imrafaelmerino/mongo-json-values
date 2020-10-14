package mongovalues;
import jsonvalues.JsNull;
import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

class JsNullCodec implements Codec<JsNull> {

    @Override
    public void encode(final BsonWriter writer, final JsNull jsNull, final EncoderContext encoderContext) {
      writer.writeNull();
    }

    @Override
    public JsNull decode(final BsonReader reader, final DecoderContext decoderContext) {
        reader.readNull();
        return JsNull.NULL;
    }

    @Override
    public Class<JsNull> getEncoderClass() {
        return JsNull.class;
    }
}
