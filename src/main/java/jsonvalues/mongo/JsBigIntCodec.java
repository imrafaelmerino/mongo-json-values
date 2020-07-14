package jsonvalues.mongo;

import jsonvalues.JsBigInt;
import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

import java.math.BigDecimal;

class JsBigIntCodec implements Codec<JsBigInt> {


  //biginteger encoder missing in driver
  @Override
  public void encode(final BsonWriter writer,
                     final JsBigInt jsBigInt,
                     final EncoderContext context) {
    //todo probar si is integer or long
    JsBigDecCodec.bigDecCodec.encode(writer,
                                     new BigDecimal(jsBigInt.value),
                                     context
                                    );
  }

  @Override
  public JsBigInt decode(final BsonReader reader,
                         final DecoderContext context) {
    //no bson type equivalent to bigint
    return null;
  }

  @Override
  public Class<JsBigInt> getEncoderClass() {
    return JsBigInt.class;
  }
}
