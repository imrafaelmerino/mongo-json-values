package jsonvalues.mongo;

import jsonvalues.JsBinary;
import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.BinaryCodec;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.types.Binary;


class JsBinaryCodec implements Codec<JsBinary> {

 private static final BinaryCodec binaryCodec = new BinaryCodec();

  @Override
  public void encode(final BsonWriter writer,
                     final JsBinary jsBinary,
                     final EncoderContext context) {
    binaryCodec.encode(writer, new Binary(jsBinary.value), context);
  }

  @Override
  public JsBinary decode(final BsonReader reader,
                         final DecoderContext context) {
    return JsBinary.of(reader.readBinaryData().getData());
  }

  @Override
  public Class<JsBinary> getEncoderClass() {
    return JsBinary.class;
  }
}
