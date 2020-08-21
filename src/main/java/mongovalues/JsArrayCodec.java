package mongovalues;

import jsonvalues.JsArray;
import jsonvalues.JsValue;
import org.bson.BsonReader;
import org.bson.BsonType;
import org.bson.BsonWriter;
import org.bson.codecs.BsonTypeClassMap;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.codecs.configuration.CodecRegistry;

import java.util.Iterator;

class JsArrayCodec extends JsonCodec implements Codec<JsArray>  {

  public JsArrayCodec(final CodecRegistry registry,
                      final BsonTypeClassMap bsonTypeClassMap) {
    super(registry,
          bsonTypeClassMap
         );
  }

  @Override
  public JsArray decode(final BsonReader reader,
                        final DecoderContext context) {
    reader.readStartArray();

    JsArray array = JsArray.empty();
    while (reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
      array = array.append((readValue(reader, context)));
    }

    reader.readEndArray();

    return array;
  }

  @Override
  public void encode(final BsonWriter writer,
                     final JsArray array,
                     final EncoderContext context) {

    writer.writeStartArray();

    for (Iterator<JsValue> it = array.iterator(); it.hasNext(); ) {
      final JsValue value = it.next();
      Codec codec = registry.get(value.getClass());
      context.encodeWithChildContext(codec,
                                     writer,
                                     value
                                    );
    }
    writer.writeEndArray();
  }

  @Override
  public Class<JsArray> getEncoderClass() {
    return JsArray.class;
  }
}
