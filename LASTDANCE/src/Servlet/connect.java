package Servlet;

import User.UserDAO;
import User.UserVO;
import Ranking.Rank;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class connect
 */
@WebServlet("/connect")
public class connect extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public connect() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// TODO Auto-generated method stub
		String age = request.getParameter("age");
		//String height = request.getParameter("height");
		//String sex = request.getParameter("sex");
		String height = request.getParameter("height");
		String sex = request.getParameter("sex");
		int cal_age = Integer.parseInt(age);
		double cal_height = Double.parseDouble(height);
		int cal_sex = Integer.parseInt(sex);
		
		
		UserVO vo = new UserVO(0, cal_sex, cal_age, cal_height, 1);
		UserDAO dao = new UserDAO();
		
		double[] result = new double[2];
		result = Rank.Return_Rank(vo, dao);
		
		response.sendRedirect("/LASTDANCE/index2.jsp?rank=" + result[0] + "&percentile=" + result[1]);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
