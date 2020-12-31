package kagoyume;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MyDelete
 */
@WebServlet("/MyDelete")
public class MyDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyDelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();

		//ログインしていなければログインページに遷移
		if(session.getAttribute("userName") == null){
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
		//リンクを踏まないで飛んだ場合エラー
		if(request.getParameter("check") == null) {
			request.setAttribute("error", "不正なアクセスです。");
			request.getRequestDispatcher("error.jsp").forward(request, response);
			return;
		}

		int userID = (int)session.getAttribute("userID");
		try {
			System.out.println(userID);
			UserDataDAO.deleteUser(userID);
			UserDataDAO.deleteAllCartItem(userID);
			session.invalidate();
			request.getRequestDispatcher("mydelete_result.jsp").forward(request, response);
		}catch(Exception e) {
			request.setAttribute("error", e.getMessage());
			request.getRequestDispatcher("error.jsp").forward(request, response);
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
