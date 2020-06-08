package mongo;

import java.io.IOException;
import java.net.UnknownHostException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoException;

/**
 * Servlet implementation class AddingServlet
 */
@WebServlet("/AddingServlet")
public class AddingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddingServlet() {
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
		
		mongoConnect goods ;
		
		try {
			goods = new mongoConnect();
			goods.dbConnect("goods");
			goods.collectConnect("goodsData");
			
			// 使用BasicDBObject对象创建一个mongodb的document,并给予赋值。
			goods.documents = new BasicDBObject();
			goods.documents.put("GoodsCode", request.getParameter("GoodsCode"));
			goods.documents.put("GoodsName", request.getParameter("GoodsName"));
			goods.documents.put("GoodsPrice", request.getParameter("GoodsPrice"));
			goods.documents.put("GoodsQuality", request.getParameter("GoodsQuality"));
			// 将新建立的document保存到collection中去
			goods.collection.insert(goods.documents);			
			
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
