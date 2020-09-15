package servlet;

import java.io.BufferedReader;
import java.io.IOException;
//import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.json.JSONArray;

import service.UserService;
import util.DBUtil;
import entity.Record;


/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserService userService = new UserService();
	
	// ���峣��
	public final static int TYPE_CONN_FAILED = -1;
	public final static int TYPE_LOGIN_FAILED = 0;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset = utf-8");
		response.setHeader("Content-type", "text/html;charset=utf-8");
		
//		String jsonString = getRequestBody(request);
//        if(jsonString.equals("")) {
//        	System.out.println("JsonObj is null.");
//        }
//        else {
//        	System.out.println(jsonString);
    //    }
//        PrintWriter out = response.getWriter();
		
		Connection conn = DBUtil.getConn();
		
		String userRequest = request.getParameter("request"); 
		
		switch (userRequest) {
		
			case "login":
				String username = request.getParameter("username");
				String password = request.getParameter("password");
				response.getOutputStream().write(Integer.toString(userService.login(conn,username, password)).getBytes("utf-8"));
				break;
				
			case "accountinfo":
				int id = Integer.parseInt(request.getParameter("id"));
//				System.out.println(id);
				Object[] account_info = new Object[5];
				account_info = userService.accountinfo(conn,id);
				JSONObject object = new JSONObject();
				object.put("id",account_info[0]);
				object.put("username",account_info[1]);
//				System.out.print(account_info[0]);
//				System.out.print(account_info[1]);
				object.put("balance",account_info[2]);
				object.put("email",account_info[3]);
				object.put("cellphone",account_info[4]);
				response.getWriter().write(object.toString());
				break;
				
			case "register":
				String username1 = request.getParameter("username");
				String password1 = request.getParameter("password");
				String email = request.getParameter("email");
				String cellphone = request.getParameter("cellphone");
				response.getOutputStream().write(Integer.toString(userService.register(conn,username1, password1,email,cellphone)).getBytes("utf-8"));
				break;
				
			case "transfer":
				int to_account = Integer.parseInt(request.getParameter("to_account"));
				int from_account = Integer.parseInt(request.getParameter("from_account"));
				double value = Double.parseDouble(request.getParameter("value"));
				response.getOutputStream().write(Integer.toString(userService.transfer(conn,from_account, to_account,value)).getBytes("utf-8"));
				break;
			
			case "transaction":
				int id1 = Integer.parseInt(request.getParameter("id"));
				List<Record> result = userService.transaction(conn, id1);
				JSONArray array = new JSONArray();
				for (int i = 0; i<result.size(); i++) {
					JSONObject object1 = new JSONObject();
					object1.put("index",result.get(i).getIndex());
					object1.put("from_account",result.get(i).getFrom());
					object1.put("to_account",result.get(i).getTo());
					object1.put("time",result.get(i).getTime());
//					System.out.print(result.get(i).getTime());
					object1.put("value",result.get(i).getValue());
					array.put(object1);
				}
				response.getWriter().write(array.toString());
				break;
				
			default:
				break;
				
		}
		
		DBUtil.closeConn(conn);
        

//        out.write("username:"+username+"  password:" + password + "\n");
//        out.close();
//		if(userService.login(username, password)) {
//			response.getOutputStream().write(("Login Success \n User: " + username + "\nPassword: " + password).getBytes("utf-8"));
//		}
//		else {
//			response.getOutputStream().write(("loginFailed").getBytes("utf-8"));
//		}	
		

	}
	
	public static String getRequestBody(HttpServletRequest req) throws IOException { 
			BufferedReader reader = req.getReader(); 
			String input = null; 
			StringBuffer requestBody = new StringBuffer(); 
			while((input = reader.readLine()) != null) { 
				requestBody.append(input); 
			} 
			return requestBody.toString(); 
		}

}
