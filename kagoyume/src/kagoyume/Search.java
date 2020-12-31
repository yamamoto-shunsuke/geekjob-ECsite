package kagoyume;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class Search
 */
@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Search() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//文字化けしないための処理
		request.setCharacterEncoding("UTF-8");
		//セッションのスタート
		HttpSession session = request.getSession();
		//フォームで入力した値を持ってくる。
		String word = request.getParameter("word");

		try {
			//wordが空ならエラー文と共にトップに遷移
			if(word == null || word.equals("")) {
				request.setAttribute("searchError", "・検索ワードを入力してください。");
				request.getRequestDispatcher("top.jsp").forward(request, response);
			}
			//wordをsessionに格納
			session.setAttribute("word", word);


			//検索処理
			
			//wahというインスタンスを作成している。
			WebAPIHelper wah = new WebAPIHelper();
			//インスタンスを用いて	WebAPIHelperにある、searchメソッドを呼び出している。
			//フォームで入力した値がwordに入っている。
			wah.search(word);
			//すべての検索結果。検索結果件数が入っている。
			request.setAttribute("totalResult", wah.totalResult);
			//検索ワードが入っている。
			request.setAttribute("requestQuery", wah.requestQuery);
			//複合検索結果。
			request.setAttribute("pdbList", wah.pdbList);
			
			request.getRequestDispatcher("/search.jsp").forward(request, response);
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