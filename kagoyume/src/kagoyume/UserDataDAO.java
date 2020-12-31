package kagoyume;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import base.DBManager;

public class UserDataDAO {

	//nameが登録されているか検索するメソッド。
	//存在すればtrue,しなければfalseを返す。
	public static boolean searchUser(String name) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement("select * from user_t where name = ? and deleteFlg = 0");
			ps.setString(1, name);
			rs = ps.executeQuery();
			return rs.next() ? true : false;
		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				throw new SQLException("close error");
			}
		}
	}

	//パスワードが一致するか確認するメソッド
	//一致すればture、しなければfalse
	public static boolean matchPassword(String name, String password) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement("select * from user_t where name = ?");
			ps.setString(1, name);
			rs = ps.executeQuery();
			rs.next();
			String rPass = rs.getString("password");
			return rPass.equals(password) ? true : false;
		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				throw new SQLException("close error");
			}
		}
	}

	//user名とパスワードからuserIDを特定して返すメソッド
	public static int getUserID(String name, String password) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DBManager.getConnection();
			//* from user_tで「user_ｔのすべてのカラムを」という意味になる。
			ps = con.prepareStatement("select * from user_t where name = ? and password = ? and deleteFlg = 0");
			ps.setString(1, name);
			ps.setString(2, password);
			rs = ps.executeQuery();
			rs.next();
			int userID = rs.getInt("userID");
			return userID;
		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				throw new SQLException("close error");
			}
		}
	}

	//DBにユーザーを新規登録するメソッド
	public static void insertUser(UserDataDTO udd) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement("insert into user_t(name, password, mail, address, total, newDate,deleteFlg) values(?,?,?,?,?,?,0)");
			ps.setString(1, udd.getName());
			ps.setString(2, udd.getPassword());
			ps.setString(3, udd.getMail());
			ps.setString(4, udd.getAddress());
			ps.setInt(5, 0);
			ps.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
			ps.executeUpdate();
			System.out.println("User Registered successfully");
		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				throw new SQLException("close error");
			}
		}
	}

	//ユーザー情報を更新するメソッド
	public static void updateUser(UserData ud) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(
					"update user_t set name = ?, password = ?, mail = ?, address = ? where userID = ?");
			ps.setString(1, ud.getName());
			ps.setString(2, ud.getPassword());
			ps.setString(3, ud.getMail());
			ps.setString(4, ud.getAddress());
			ps.setInt(5, ud.getUserID());
			ps.executeUpdate();
			System.out.println("Update User successfully");
		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				throw new SQLException("close error");
			}
		}
	}

	//ユーザーを削除するメソッド（実際はdeleteFlgを1にするメソッド）
	public static void deleteUser(int userID) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement("update user_t set deleteFlg = ? where userID = ?");
			ps.setInt(1, 1);
			ps.setInt(2, userID);
			ps.executeUpdate();
			System.out.println("Succeccfully Deleted");
		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				throw new SQLException("close error");
			}
		}
	}

	//DBに購入した商品を登録するメソッド
	public static void insertBuyItem(int userID, String itemCode, int type) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement("insert into buy_t(userID, itemCode, type, buyDate) values(?,?,?,?)");
			ps.setInt(1, userID);
			ps.setString(2, itemCode);
			ps.setInt(3, type);
			ps.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				throw new SQLException("close error");
			}
		}
	}

	//user_tのtotalを更新するメソッド
	public static void addTotal(int userID, int sum) throws SQLException {
		Connection con = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		ResultSet rs = null;
		try {
			con = DBManager.getConnection();
			ps1 = con.prepareStatement("select total from user_t where userID = ?");
			ps1.setInt(1, userID);
			rs = ps1.executeQuery();
			rs.next();
			int total = rs.getInt("total");

			ps2 = con.prepareStatement("update user_t set total = ? where userID = ?");
			ps2.setInt(1, total + sum);
			ps2.setInt(2, userID);
			ps2.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps1 != null) {
					ps1.close();
				}
				if (ps2 != null) {
					ps2.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				throw new SQLException("close error");
			}
		}
	}

	//userIDからデータを検索してudにセットするメソッド
	public static void setUserData(UserDataDTO udd, int userID) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement("select * from user_t where userID = ?");
			ps.setInt(1, userID);
			rs = ps.executeQuery();
			rs.next();

			udd.setName(rs.getString("name"));
			udd.setPassword(rs.getString("password"));
			udd.setMail(rs.getString("mail"));
			udd.setAddress(rs.getString("address"));
			udd.setTotal(rs.getInt("total"));
			udd.setNewDate(rs.getTimestamp("newDate"));

		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				throw new SQLException("close error");
			}
		}
	}

	//userIDから購入データを検索してリストにセットするメソッド
	public static ArrayList<ProductDataBean> getPurchaseHistory(int userID) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<ProductDataBean> list = new ArrayList<>();
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement("select * from buy_t where userID = ?");
			ps.setInt(1, userID);
			rs = ps.executeQuery();
			while (rs.next()) {
				ProductDataBean pdb = new ProductDataBean();
				pdb.setCode(rs.getString("itemCode"));
				pdb.setType(rs.getInt("type"));
				pdb.setDate(rs.getTimestamp("buyDate"));

				list.add(pdb);
			}
			return list;
		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				throw new SQLException("close error");
			}
		}
	}

	//cart_tに商品を登録するメソッド
	public static void insertCartItem(int userID, ProductDataBean pdb) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(
					"insert into cart_t(userID, itemName, itemCode, price, thumbnail, inCartDate) values(?,?,?,?,?,?)");
			ps.setInt(1, userID);
			ps.setString(2, pdb.getName());
			ps.setString(3, pdb.getCode());
			ps.setInt(4, pdb.getPrice());
			ps.setString(5, pdb.getThumbnail());
			ps.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				throw new SQLException("close error");
			}
		}
	}

	//userIDからカートデータを検索してリストにセットするメソッド
	public static ArrayList<ProductDataBean> getCartItem(int userID) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<ProductDataBean> list = new ArrayList<>();
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement("select * from cart_t where userID = ?");
			ps.setInt(1, userID);
			rs = ps.executeQuery();
			while (rs.next()) {
				ProductDataBean pdb = new ProductDataBean();
				pdb.setName(rs.getString("itemName"));
				pdb.setCode(rs.getString("itemCode"));
				pdb.setPrice(rs.getInt("Price") + "");
				pdb.setThumbnail(rs.getString("thumbnail"));
				pdb.setDate(rs.getTimestamp("inCartDate"));

				list.add(pdb);
			}
			return list;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (Exception e) {
				throw new Exception("close error");
			}
		}
	}

	//cart_tのuserIDを変更するメソッド
	public static void updateCartItem(int userID) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement("update cart_t set userID = ? where userID = ?");
			ps.setInt(1, userID);
			ps.setInt(2, 0);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				throw new SQLException("close error");
			}
		}
	}

	//cart_tからアイテムを削除するメソッド
	public static void deleteCartItem(int userID, String date) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement("delete from cart_t where userID = ? and inCartDate = ?");
			ps.setInt(1, userID);
			ps.setString(2, date);
			ps.executeUpdate();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (Exception e) {
				throw new Exception("close error");
			}
		}
	}

	//cart_tから特定のuserIDのアイテムを全削除するメソッド
	public static void deleteAllCartItem(int userID) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement("delete from cart_t where userID = ?");
			ps.setInt(1, userID);
			ps.executeUpdate();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (Exception e) {
				throw new Exception("close error");
			}
		}
	}
}
