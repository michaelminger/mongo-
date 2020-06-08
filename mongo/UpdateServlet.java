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
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
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

			mongoConnect upmongo = new mongoConnect();
			upmongo.dbConnect("goods");
			upmongo.collectConnect("goodsData");
			
			String IDcode = request.getParameter("IDCode");
			
			upmongo.cursor = upmongo.collectFind("GoodsCode", IDcode);
			System.out.println("Done");
            
	        while (upmongo.cursor.hasNext()){
	        	J1 = true ;
                DBObject obj = upmongo.cursor.next();
                JSONObject json = JSONObject.fromObject(obj); 
                
                request.setAttribute("GoodsCode", (String)json.get("GoodsCode"));
                request.setAttribute("GoodsName", (String)json.get("GoodsName"));
                request.setAttribute("GoodsPrice", (String)json.get("GoodsPrice"));
                request.setAttribute("GoodsQuality", (String)json.get("GoodsQuality") );
                request.getRequestDispatcher("/Update.jsp").forward(request, response);
            	
        	}
			if ( J1 == false ) {
				request.getRequestDispatcher("/cart.jsp").forward(request, response);
			}
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
