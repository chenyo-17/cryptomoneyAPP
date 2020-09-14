package servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.print.attribute.standard.MediaSize.Other;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Buffer;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.setContentType("text/html");
		response.setContentType("application/json;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		InputStream is = request.getInputStream();
		StringBuffer sb = new StringBuffer();
		int len = 0;
		byte[]buf = new byte[1024];
		while((len = is.read(buf)) != -1) {
			sb.append(new String(buf,0,len));
		}
		System.out.println(sb.toString());
		
		
//		String username = request.getParameter("username");
//        String password = request.getParameter("password");
//        System.out.println("�û�����"+username);
//        System.out.println("���룺"+password);
//        if ("123".equals(username)&&"123".equals(password)) {
//            System.out.println("��¼�ɹ�");	
//            response.getOutputStream().write("��¼�ɹ� ��������".getBytes("utf-8"));
//             
//        }else {
//            System.out.println("��¼ʧ��");
//            response.getOutputStream().write("��¼ʧ��... �����˺ź�����".getBytes("utf-8"));
//             
//        }

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
