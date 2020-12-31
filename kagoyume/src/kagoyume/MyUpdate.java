package kagoyume;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MyUpdate
 */
@WebServlet("/MyUpdate")
public class MyUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		//ログインしていなければログインページに遷移


		//更新ボタンから飛ばなかった場合にエラー
		if(request.getParameter("check") == null) {
			request.setAttribute("error", "不正なアクセスです。");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
			return;
		}
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String mail = request.getParameter("mail");
		String address = request.getParameter("address");
		ArrayList<String> formError = new ArrayList<>();

		//未入力ならformErrorにエラー文を格納
		UserData ud = new UserData();
		if(name == "") {
			formError.add("・名前を入力してください。");
		}

		if(password == "") {
			formError.add("・パスワードを入力してください。");
		}

		if(mail == "") {
			formError.add("・メールアドレスを入力してください。");
		}

		if(address == "") {
			formError.add("・住所を入力してください。");
		}

		//udに値を格納
		ud.setUserID((int)session.getAttribute("userID"));
		ud.setName(name);
		ud.setPassword(password);
		ud.setMail(mail);
		ud.setAddress(address);

		//formErrorにエラー文があれば更新画面に戻る
		if(formError.size() > 0) {
			//ud, formErrorをリクエストに格納
			request.setAttribute("ud", ud);
			request.setAttribute("formError", formError);
			request.getRequestDispatcher("/myupdate.jsp").forward(request, response);
			return;
		}

		try {
			UserDataDAO.updateUser(ud);
			request.setAttribute("ud", ud);
			session.setAttribute("userName", name);
			request.getRequestDispatcher("/myupdate_result.jsp").forward(request, response);
		}catch(Exception e) {
			request.setAttribute("error", e.getMessage());
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
