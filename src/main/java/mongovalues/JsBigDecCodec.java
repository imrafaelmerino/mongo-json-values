package mongovalues;

import jsonvalues.JsBigDec;
import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.BigDecimalCodec;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

class JsBigDecCodec implements Codec<JsBigDec> {

  static final BigDecimalCodec bigDecCodec = new BigDecimalCodec();

  @Override
  public void encode(final BsonWriter writer,
                     final JsBigDec jsBigDec,
                     final EncoderContext encoderContext) {
      //todo crear el decimal 28 y si no se puede representar devolver error mas claro que el del driver
      bigDecCodec.encode(writer,
                         jsBigDec.value,
                         encoderContext
                        );
  }

  @Override
  public JsBigDec decode(final BsonReader reader,
                         final DecoderContext decoderContext) {

      return JsBigDec.of(bigDecCodec.decode(reader,
                                            decoderContext
                                           )
                        );
  }

  @Override
  public Class<JsBigDec> getEncoderClass() {
    return JsBigDec.class;
  }
}
