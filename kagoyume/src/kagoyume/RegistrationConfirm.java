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
 * Servlet implementation class RegistrationConfirm
 */
@WebServlet("/RegistrationConfirm")
public class RegistrationConfirm extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationConfirm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		try {
			request.setCharacterEncoding("UTF-8");
			String name = request.getParameter("name") == null ? "" : request.getParameter("name");
			String password = request.getParameter("password") == null ? "" : request.getParameter("password");
			String mail = request.getParameter("mail") == null ? "" : request.getParameter("mail");
			String address = request.getParameter("address") == null ? "" : request.getParameter("address");
			ArrayList<String> formError = new ArrayList<>();


			//未入力ならformErrorにエラー文を格納
			UserData ud = new UserData();
			if(name == "") {
				formError.add("・ユーザー名を入力してください。");
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

			ud.setName(name);
			ud.setPassword(password);
			ud.setMail(mail);
			ud.setAddress(address);

			//sessionにudを格納
			session.setAttribute("ud", ud);

			//formErrorにエラー文があれば登録画面に、無ければ登録確認画面に遷移
			if(formError.size() > 0) {

				//formErrorをリクエストに格納
				request.setAttribute("formError", formError);
				request.getRequestDispatcher("/registration.jsp").forward(request, response);
			}else {
				//アクセスルートチェック用の乱数を格納

				session.setAttribute("ac", (int)(Math.random()*1000));
				request.getRequestDispatcher("/registration_confirm.jsp").forward(request, response);
			}
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
