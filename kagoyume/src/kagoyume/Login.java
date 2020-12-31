package kagoyume;


import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		ArrayList<String> errors = new ArrayList<>();
		//login.jspで入力した名前とパスワードをrequestスコープから持ってきている。
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		System.out.println(session.getAttribute("referer"));
		try {

			//ユーザー名が間違っていればエラー文を格納して遷移
			//名前とパスワードで分岐を分けているのは、名前とパスワードのどちらが間違っているのかを明確にするため。
			if(!UserDataDAO.searchUser(name)) {
				errors.add("・そのユーザー名は登録されていません。");
				request.setAttribute("errors", errors);
				request.getRequestDispatcher("/login.jsp").forward(request, response);
				return;
			}

			//パスワードが間違っていればエラー文を格納して遷移
			if(!UserDataDAO.matchPassword(name, password)) {
				errors.add("・パスワードが間違っています。");
				request.setAttribute("errors", errors);
				request.getRequestDispatcher("/login.jsp").forward(request, response);
				return;
			}
			System.out.println(session.getAttribute("referer"));
			//ユーザー名とパスワードからuserIDを取得してセッションに格納
			int userID = UserDataDAO.getUserID(name, password);
			session.setAttribute("userID", userID);

			//ユーザー名をセッションに格納
			session.setAttribute("userName", name);

			//カート内アイテムのuserID=0のアイテムを現在のuserIDに変更
			UserDataDAO.updateCartItem(userID);

			//直前まで見ていたページに遷移
			String url = (String)session.getAttribute("referer");
			response.sendRedirect(url);
		}catch(SQLException e) {
			request.setAttribute("error", e.getMessage());
			System.out.println(session.getAttribute(e.getMessage()));
			request.getRequestDispatcher("error.jsp").forward(request, response);
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
