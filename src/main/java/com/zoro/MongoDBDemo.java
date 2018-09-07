package com.zoro;

import com.mongodb.Block;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import static com.mongodb.client.model.Filters.*;

/**
 * Created on 2018/9/7.
 *
 * @author dubber
 */
public class MongoDBDemo {

    public static void main(String[] args) {
        // MongoClient mongodb客户端
        MongoClient mongoClient = MongoClients.create("mongodb://192.168.116.12:27017");
        // 数据库
        MongoDatabase database = mongoClient.getDatabase("testdb");
        // 创建表
        //database.createCollection("t_member2");
        MongoCollection<Document> collection = database.getCollection("t_member");
        /**
         {
         "name" : "MongoDB",
         "type" : "database",
         "count" : 1,
         "versions": [ "v3.2", "v3.0", "v2.6" ],
         "info" : { x : 203, y : 102 }
         }
         */
        // 如果显示插入 _id，则默认生成
        /*Document document = new Document("name","MongoDB")
                .append("type","database")
                .append("count",1)
                .append("versions", Arrays.asList("v3.2","v3.0","v2.2"))
                .append("info",new Document("x",203).append("y",102));

        collection.insertOne(document);

        List<Document> docList =  new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            docList.add(new Document("i",i));
        }
        collection.insertMany(docList);
        System.out.println("insertMany sucess ！！");*/
      /*  System.out.println(collection.countDocuments());

        //查询 document
        Document documentFirst = collection.find().first();
        System.out.println(documentFirst.toJson());*/

        //查询 documents
        /*MongoCursor<Document> mongoCursor = collection.find().iterator();
        while (mongoCursor.hasNext()) {
            System.out.println(mongoCursor.next().toJson());
        }*/

       /* try (MongoCursor<Document> cursor = collection.find().iterator()) {
            while (cursor.hasNext()) {
                System.out.println(cursor.next().toJson());
            }
        }*/

        // filters 查询
        /*Document documentFirstByFilters = collection.find(eq("$oid", 4)).first();
        System.out.println(documentFirstByFilters.toJson());*/


        Block<Document> block = new Block<Document>() {
            @Override
            public void apply(Document document) {
                System.out.println(document.toJson());
            }
        };
        // i> 8
        // collection.find(gt("i",8)).forEach(block);
        // 4 < i <= 7
        collection.find(and(gt("i", 4), lte("i", 7))).forEach(block);

    }
}
