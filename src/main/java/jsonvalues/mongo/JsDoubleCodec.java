package jsonvalues.mongo;

import jsonvalues.JsDouble;
import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.DoubleCodec;
import org.bson.codecs.EncoderContext;

class JsDoubleCodec implements Codec<JsDouble> {

  private static final DoubleCodec doubleCodec = new DoubleCodec();

  @Override
  public void encode(final BsonWriter writer,
                     final JsDouble jsDouble,
                     final EncoderContext encoderContext) {
    doubleCodec.encode(writer,
                       jsDouble.value,
                       encoderContext
                      );
  }

  @Override
  public JsDouble decode(final BsonReader reader,
                         final DecoderContext decoderContext) {
    return JsDouble.of(doubleCodec.decode(reader,
                                          decoderContext
                                         )
                      );
  }

  @Override
  public Class<JsDouble> getEncoderClass() {
    return JsDouble.class;
  }
}
