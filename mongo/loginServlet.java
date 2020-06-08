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
 * Servlet implementation class loginServlet
 */
@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginServlet() {
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
		boolean J1 = false ;
		
		try {
			login = new mongoConnect();
			login.dbConnect("login");
			login.collectConnect("loginData");
			
			String loginIDNumber = request.getParameter("IDNumber");
			String loginPassword = request.getParameter("Password");
			
			login.searchQuery.put("IDNumber", loginIDNumber);
			login.searchQuery.put("Password", loginPassword);

			login.cursor = login.collection.find(login.searchQuery);

			while(login.cursor.hasNext()) {
				J1 = true ;
				request.setAttribute("loginIDNumber",loginIDNumber);
				request.setAttribute("loginPassword",loginPassword);
				
				request.getRequestDispatcher("/category.jsp").forward(request, response);
			}
			if ( J1 == false ) {
				request.getRequestDispatcher("/login.jsp").forward(request, response);
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
