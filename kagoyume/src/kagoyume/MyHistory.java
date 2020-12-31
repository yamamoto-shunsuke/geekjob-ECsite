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
 * Servlet implementation class MyHistory
 */
@WebServlet("/MyHistory")
public class MyHistory extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyHistory() {
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
			int userID = (int)session.getAttribute("userID");
			//buy_tから購入履歴を取得
			ArrayList<ProductDataBean> list = UserDataDAO.getPurchaseHistory(userID);

			//購入履歴から商品詳細を取得
			ArrayList<ProductDataBean> historyList = new ArrayList<>();
			for(ProductDataBean pdb: list) {
				WebAPIHelper wah = new WebAPIHelper();
				wah.lookup(pdb.getCode());
				wah.pdb.setDate(pdb.getDate());
				historyList.add(wah.pdb);
			}
			session.setAttribute("historyList", historyList);
			request.getRequestDispatcher("/myhistory.jsp").forward(request, response);
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
