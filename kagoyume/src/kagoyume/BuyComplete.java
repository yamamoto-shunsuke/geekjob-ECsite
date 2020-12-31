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
 * Servlet implementation class BuyComplete
 */
@WebServlet("/BuyComplete")
public class BuyComplete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BuyComplete() {
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
		try {
			//buyConfirm.jspで選択した発送方法をrequestスコープから持って来ている。
			int type = Integer.parseInt(request.getParameter("type"));
			//Login.javaでセッションに格納したuserIDをセッションから持ってきている。
			int userID = (int) session.getAttribute("userID");
			//requestスコープに入れていたsum(合計金額をもってきている。)
			int sum = Integer.parseInt(request.getParameter("sum"));
			//セッションに保存したcart情報をセッションから持って来ている。
			ArrayList<ProductDataBean> cart = (ArrayList<ProductDataBean>) session.getAttribute("cart");

			//カートの中身をすべてDB(buy_t)に登録
			for (ProductDataBean pdb : cart) {
				String itemCode = pdb.getCode();
				//buy_tにuserID,itemCode,tyoe(発送方法)の情報を追加している。
				UserDataDAO.insertBuyItem(userID, itemCode, type);
			}

			//合計金額をuser_tのtotalに加算
			UserDataDAO.addTotal(userID, sum);

			//カートを空にする。
			session.setAttribute("cart", null);
			//遷移先を指定
			request.getRequestDispatcher("buy_complete.jsp").forward(request, response);
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
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
