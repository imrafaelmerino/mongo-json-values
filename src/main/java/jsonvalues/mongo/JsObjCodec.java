package jsonvalues.mongo;

import io.vavr.Tuple2;
import jsonvalues.JsObj;
import jsonvalues.JsValue;
import org.bson.BsonReader;
import org.bson.BsonType;
import org.bson.BsonWriter;
import org.bson.codecs.BsonTypeClassMap;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.types.ObjectId;

import java.util.Iterator;


class JsObjCodec extends JsonCodec implements Codec<JsObj> {

  static final String ID_FIELD_NAME = "_id";

  public JsObjCodec(final CodecRegistry registry,
                    final BsonTypeClassMap bsonTypeClassMap) {
    super(registry,
          bsonTypeClassMap
         );
  }

  @Override
  public JsObj decode(final BsonReader reader,
                      final DecoderContext context) {

    JsObj obj = JsObj.empty();

    reader.readStartDocument();
    while (reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
      String fieldName = reader.readName();
      obj = obj.set(fieldName,
                    readValue(reader,
                              context
                             )
                   );
    }

    reader.readEndDocument();

    return obj;
  }

  @Override
  public void encode(final BsonWriter writer,
                     final JsObj obj,
                     final EncoderContext context) {

    writer.writeStartDocument();

    JsValue id = obj.get(ID_FIELD_NAME);

    if(id.isNothing()){
      encodeObj(writer,
                obj,
                context
               );
    }
    else{
      encodeObjId(writer,
                 id
                 );
      encodeObj(writer,
                obj.delete(ID_FIELD_NAME),
                context
               );
    }
    writer.writeEndDocument();
  }

  private void encodeObjId(final BsonWriter writer,
                           final JsValue value) {
    writer.writeName(ID_FIELD_NAME);
    if(value.isObj(o->o.containsKey("$oid"))){
      writer.writeObjectId(new ObjectId(value.toJsObj().getStr("$oid")));
    }
    else{
      writer.writeObjectId(new ObjectId(value.toString()));
    }
  }

  private void encodeObj(final BsonWriter writer,
                      final JsObj obj,
                      final EncoderContext context) {
    for (Iterator<Tuple2<String, JsValue>> it = obj.delete(ID_FIELD_NAME).iterator(); it.hasNext(); ) {
      final Tuple2<String, JsValue> entry = it.next();
      writer.writeName(entry._1);
      Codec codec = registry.get(entry._2.getClass());
      context.encodeWithChildContext(codec,
                                     writer,
                                     entry._2
                                    );
    }
  }

  //creo que si el servidor lo añade si no lo hace, para que meter más lógica
  private void writeIdIfNotPresent(final BsonWriter writer,
                                   final JsObj obj) {
    String   id       = obj.getStr(ID_FIELD_NAME);
    ObjectId objectId = id == null ? new ObjectId() : new ObjectId(id);
    writer.writeName(ID_FIELD_NAME);
    writer.writeObjectId(objectId);
  }

  @Override
  public Class<JsObj> getEncoderClass() {
    return JsObj.class;
  }
}
