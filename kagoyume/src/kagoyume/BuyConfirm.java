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
 * Servlet implementation class BuyConfirm
 */
@WebServlet("/BuyConfirm")
public class BuyConfirm extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BuyConfirm() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//購入確認画面。セッションをスタートさせている。
		HttpSession session = request.getSession();
		//ログインしていなければログインページに遷移
		if (session.getAttribute("userName") == null) {
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
		//セッションに保存したcart情報をセッションから持って来ている。
		ArrayList<ProductDataBean> cart = (ArrayList<ProductDataBean>) session.getAttribute("cart");
		//requestスコープからsumをもってきて、それをrequestスコープに送っている。
		int sum = Integer.parseInt(request.getParameter("sum"));
		request.setAttribute("sum", sum);
		request.getRequestDispatcher("/buy_confirm.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
