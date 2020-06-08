package mongo;

import java.io.IOException;
import java.net.UnknownHostException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.MongoException;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class Update2Servlet
 */
@WebServlet("/Update2Servlet")
public class Update2Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Update2Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		boolean J1 = false ;
		
		try {

			mongoConnect up2mongo = new mongoConnect();
			up2mongo.dbConnect("goods");
			up2mongo.collectConnect("goodsData");

			String UpIDCode = request.getParameter("IDCode");

			up2mongo.collectDele("GoodsCode", UpIDCode);
			// 使用BasicDBObject对象创建一个mongodb的document,并给予赋值。
			up2mongo.documents = new BasicDBObject();
			up2mongo.documents.put("GoodsCode", request.getParameter("GoodsCode"));
			up2mongo.documents.put("GoodsName", request.getParameter("GoodsName"));
			up2mongo.documents.put("GoodsPrice", request.getParameter("GoodsPrice"));
			up2mongo.documents.put("GoodsQuality", request.getParameter("GoodsQuality"));
			// 将新建立的document保存到collection中去
			up2mongo.collection.insert(up2mongo.documents);			
			
			System.out.println("Done");
			
			request.getRequestDispatcher("/cart.jsp").forward(request, response);
			 
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (MongoException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

}
