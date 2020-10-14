package mongovalues;

import jsonvalues.JsInstant;
import jsonvalues.JsStr;
import jsonvalues.JsValue;
import org.bson.BsonReader;
import org.bson.BsonType;
import org.bson.codecs.BsonTypeClassMap;
import org.bson.codecs.BsonTypeCodecMap;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.types.ObjectId;

import java.time.Instant;


abstract class JsonCodec {


  CodecRegistry registry;
  BsonTypeClassMap bsonTypeClassMap;
  private BsonTypeCodecMap bsonTypeCodecMap;


  public JsonCodec(final CodecRegistry registry,
                   final BsonTypeClassMap bsonTypeClassMap) {
    this.registry = registry;
    this.bsonTypeClassMap = bsonTypeClassMap;
    this.bsonTypeCodecMap = new BsonTypeCodecMap(bsonTypeClassMap,
                                                 registry
    );
  }

  protected JsValue readValue(final BsonReader reader,
                              final DecoderContext context) {

    BsonType bsonType = reader.getCurrentBsonType();

    if (bsonType == BsonType.OBJECT_ID) {
      ObjectId objectId = reader.readObjectId();
      return JsStr.of(objectId.toHexString());
    }

    //not supported for writing, date-time is the format supported
    // for reading is transformed into an instant
    if(bsonType == BsonType.TIMESTAMP){
      long value = reader.readTimestamp()
                         .getValue();
      return JsInstant.of(Instant.ofEpochMilli(value));
    }

    Codec<JsValue> codec = (Codec<JsValue>) bsonTypeCodecMap.get(bsonType);
    if(codec==null) throw new IllegalArgumentException("The bson type "+bsonType.name()+" is not supported");
    return codec.decode(reader,
                        context
                       );
  }
}
