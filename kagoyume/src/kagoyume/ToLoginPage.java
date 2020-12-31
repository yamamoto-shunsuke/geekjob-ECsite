package kagoyume;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ToLoginPage
 */
@WebServlet("/ToLoginPage")
public class ToLoginPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ToLoginPage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		//ログイン後の遷移用にセッションにリファラを格納
		//ログインに成功したら、ログイン前に開いていたページ(ログインボタンを押したページ)に遷移するために用意したもの。
		//リクエストのヘッダー情報の遷移元URLを取得して、リクエストのヘッダー情報の遷移元URLを取得しセッションに格納
		session.setAttribute("referer", request.getHeader("REFERER"));
		request.getRequestDispatcher("/login.jsp").forward(request, response);
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
