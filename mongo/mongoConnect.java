package mongo;
import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;



public class mongoConnect {
	MongoClient mongoClient ;
	DB db ;
	DBCollection collection;
	BasicDBObject document = new BasicDBObject();
	BasicDBObject searchQuery = new BasicDBObject();
	DBCursor cursor ;
	BasicDBObject documents;
	BasicDBObject queryObject;

	@SuppressWarnings("deprecation")
	public mongoConnect() throws UnknownHostException{
		try {
			this.mongoClient = new MongoClient("localhost", 27017);
		}catch(Exception e ) {
			System.out.println("¡¨Ω”27017ø‚ ß∞‹");
		}
	}

	public void dbConnect(String dbName) throws UnknownHostException{
		try {
			this.db = this.mongoClient.getDB(dbName);
		}catch(Exception e ) {
			System.out.println("¡¨Ω”mongoø‚ ß∞‹");
		}
	}
	
	public void collectConnect(String collectName) throws Exception{
		try {
			this.collection = this.db.getCollection(collectName);
		}catch(Exception e ) {
			System.out.println("¡¨Ω”collection ß∞‹");
		}
	}
	
	public DBCursor collectFind() throws Exception{
		try {
			this.cursor = this.collection.find();
		}catch(Exception e ) {
			System.out.println("≤È’“ ß∞‹");
		}
		return this.cursor ;
	}	
	
	public DBCursor collectFind(String condition1 , String condition2) throws Exception{
		try {
	        this.queryObject = new BasicDBObject(condition1,condition2);
	        this.cursor = this.collection.find(queryObject);
	        
		}catch(Exception e ) {
			System.out.println("≤È’“ ß∞‹");
		}
		return this.cursor ;
	}
	
	public void collectDele(String condition , String data) throws Exception{
		try {
	        this.queryObject = new BasicDBObject(condition,data);
			this.collection.remove(queryObject);
		}catch(Exception e ) {
			System.out.println("…æ≥˝ ß∞‹");
		}
	}
	
	public void collectUpdate(String condition , String data1, String data2 ) throws Exception{
		try {
	        this.queryObject = new BasicDBObject(condition,data1);	   
	        BasicDBObject up = new BasicDBObject("$set", new BasicDBObject(condition , data2));
	        this.collection.update(queryObject, up);
		}catch(Exception e ) {
			System.out.println("∏¸–¬ ß∞‹");
		}
	}

}
