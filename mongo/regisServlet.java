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
 * Servlet implementation class regisServlet
 */
@WebServlet("/regisServlet")
public class regisServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public regisServlet() {
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
		
		mongoConnect login ;
		
		try {
			login = new mongoConnect();
			login.dbConnect("login");
			login.collectConnect("loginData");
			
			// 使用BasicDBObject对象创建一个mongodb的document,并给予赋值。
			login.documents = new BasicDBObject();
			login.documents.put("IDNumber", request.getParameter("IDNumber"));
			login.documents.put("Email", request.getParameter("Email"));
			login.documents.put("Password", request.getParameter("Password"));
			// 将新建立的document保存到collection中去
			login.collection.insert(login.documents);			
			
			System.out.println("Done");

			request.getRequestDispatcher("/login.jsp").forward(request, response);
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
