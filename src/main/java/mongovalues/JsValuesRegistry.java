package mongovalues;

import jsonvalues.*;
import org.bson.BsonType;
import org.bson.codecs.BsonTypeClassMap;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;

import java.util.HashMap;
import java.util.Map;

import static org.bson.codecs.configuration.CodecRegistries.fromCodecs;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;

public class JsValuesRegistry {


  public static CodecRegistry INSTANCE;
  static
  {
      BsonTypeClassMap bsonTypeClassMap = new BsonTypeClassMap(overwriteDefaults());

      INSTANCE = CodecRegistries.fromRegistries(fromCodecs(
                                            new JsBigDecCodec(),
                                            new JsBigIntCodec(),
                                            new JsStrCodec(),
                                            new JsBoolCodec(),
                                            new JsIntCodec(),
                                            new JsLongCodec(),
                                            new JsDoubleCodec(),
                                            new JsNullCodec(),
                                            new JsBinaryCodec(),
                                            new JsInstantCodec()
                                           ), fromProviders(new JsObjCodecProvider(bsonTypeClassMap),
                                                            new JsArrayCodecProvider(bsonTypeClassMap)));
  }



  private static Map<BsonType, Class<?>> overwriteDefaults() {
    Map<BsonType, Class<?>> map = new HashMap<>();
    map.put(BsonType.NULL,JsNull.class);
    map.put(BsonType.ARRAY, JsArray.class);
    map.put(BsonType.BINARY,JsBinary.class);
    map.put(BsonType.BOOLEAN, JsBool.class);
    map.put(BsonType.DATE_TIME,JsInstant.class);
    map.put(BsonType.DOCUMENT, JsObj.class);
    map.put(BsonType.DOUBLE, JsDouble.class);
    map.put(BsonType.INT32, JsInt.class);
    map.put(BsonType.INT64, JsLong.class);
    map.put(BsonType.DECIMAL128, JsBigDec.class);
    map.put(BsonType.STRING, JsStr.class);

    return map;
  }


}
