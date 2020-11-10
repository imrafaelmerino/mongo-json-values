[![Build Status](https://travis-ci.org/imrafaelmerino/mongo-values.svg?branch=master)](https://travis-ci.org/imrafaelmerino/mongo-values)
[![CircleCI](https://circleci.com/gh/imrafaelmerino/mongo-values/tree/master.svg)](https://circleci.com/gh/imrafaelmerino/mongo-values/tree/master)

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=imrafaelmerino_mongo-values&metric=alert_status)](https://sonarcloud.io/dashboard?id=imrafaelmerino_mongo-values)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=imrafaelmerino_mongo-values&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=imrafaelmerino_mongo-values)
[![Commitizen friendly](https://img.shields.io/badge/commitizen-friendly-brightgreen.svg)](http://commitizen.github.io/cz-cli/)

[![Javadocs](https://www.javadoc.io/badge/com.github.imrafaelmerino/mongo-values.svg)](https://www.javadoc.io/doc/com.github.imrafaelmerino/mongo-values)
[![Maven](https://img.shields.io/maven-central/v/com.github.imrafaelmerino/mongo-values/0.6)](https://search.maven.org/artifact/com.github.imrafaelmerino/mongo-values/0.6/jar)
[![](https://jitpack.io/v/imrafaelmerino/mongo-values.svg)](https://jitpack.io/#imrafaelmerino/mongo-values)

- [Introduction](#intro)
- [Requirements](#requirements)
- [Installation](#installation)

## <a name="intro"><a/> Introduction 

It provides a set of codecs to work with [json-values](https://github.com/imrafaelmerino/json-values)
The codecs abstracts the processes of decoding a BSON value into JsValue  using a 
BsonReader and encoding a JsValue into a BSON value using a BsonWriter.

Please find below the supported BSON types and their json-value equivalents:

```java    

Map<BsonType, Class<?>> map = new HashMap<>();
map.put(BsonType.NULL, JsNull.class);
map.put(BsonType.ARRAY, JsArray.class);
map.put(BsonType.BINARY, JsBinary.class);
map.put(BsonType.BOOLEAN, JsBool.class);
map.put(BsonType.DATE_TIME, JsInstant.class);
map.put(BsonType.DOCUMENT, JsObj.class);
map.put(BsonType.DOUBLE, JsDouble.class);
map.put(BsonType.INT32, JsInt.class);
map.put(BsonType.INT64, JsLong.class);
map.put(BsonType.DECIMAL128, JsBigDec.class);
map.put(BsonType.STRING, JsStr.class);

``` 

A CodecRegistry contains a set of Codec instances that are accessed according to the Java classes
that they encode from and decode to

Just register the registry **JsValuesRegistry**, that contains the necessary codecs, and
when defining the collection, specify the generic type **JsObj**.
You can use the mongo API **without doing any kind of conversion between BSON and JsObj**:


```java
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import mongovalues.JsValuesRegistry;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import jsonvalues.JsObj

ConnectionString connString = ???;
 
MongoClientSettings  settings =
             MongoClientSettings.builder()
                                .applyConnectionString(connString)
                                .codecRegistry(JsValuesRegistry.INSTANCE)
                                .build();

MongoClient mongoClient = result = MongoClients.create(settings);

MongoCollection<JsObj> collection = mongoClient.getCollection("db","collection");
                                                                                                      "Data"
``` 


## <a name="requirements"><a/> Requirements 

   -  Java 8 or greater
   -  [mongo driver sync](https://mongodb.github.io/mongo-java-driver/4.1/whats-new/)

## <a name="installation"><a/> Installation 


```xml

<dependency>
  <groupId>com.github.imrafaelmerino</groupId>
  <artifactId>mongo-values</artifactId>
  <version>0.6</version>
</dependency>

```