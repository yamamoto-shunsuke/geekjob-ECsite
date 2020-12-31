package kagoyume;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Item
 */
@WebServlet("/Item")
public class Item extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Item() {
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
		try {

			//コードから商品詳細を取得
			String code = request.getParameter("code");

			//コードが入っていない（直リンク）ならエラー
//			if(code == null) {
//
//				throw new Exception("・不正なアクセスです。");
//			}

			//WebAPIHelperのpdbにマッピング
			WebAPIHelper wah = new WebAPIHelper();
			wah.lookup(code);

			//アクセスルートチェック用の数字を生成してセッションに格納
			int ac = (int)(Math.random()*1000);
			session.setAttribute("ac", ac);

			//parameterからattributeに変更
			request.setAttribute("word", request.getParameter("word"));


			session.setAttribute("pdb", wah.pdb);
			request.getRequestDispatcher("/item.jsp").forward(request, response);
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