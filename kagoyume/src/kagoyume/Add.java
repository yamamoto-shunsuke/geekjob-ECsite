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
 * Servlet implementation class Add
 */
@WebServlet("/Add")
public class Add extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Add() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		ProductDataBean pdb = (ProductDataBean)session.getAttribute("pdb");
		try{
			//アクセスルートをチェックして不正ならエラー、不正じゃなければsessionのacを削除
			int ac_session = (int)session.getAttribute("ac");
			int ac_request = Integer.parseInt(request.getParameter("ac"));
			if(ac_session != ac_request) {
				throw new Exception("・不正なアクセスです。");
			}else {
				session.setAttribute("ac", null);
			}
			//pdbにはitem.jspから送られてきた商品名、画像、値段等の情報が入っている。
			//カートはaddされたときに作られる。
			if(session.getAttribute("cart") != null) {
				//sessionに入っているcart情報をsessionから取り出す。
				ArrayList<ProductDataBean> cart = (ArrayList<ProductDataBean>) session.getAttribute("cart");
				//cartにpdb(商品情報)を加えている。
				cart.add(pdb);
				//加えたら、セッションにcartという名前で保存している。
				session.setAttribute("cart",cart );
			}else {
				ArrayList<ProductDataBean> cart = new ArrayList<ProductDataBean>();
				cart.add(pdb);
				session.setAttribute("cart",cart );
			}

			request.getRequestDispatcher("/add.jsp").forward(request, response);
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