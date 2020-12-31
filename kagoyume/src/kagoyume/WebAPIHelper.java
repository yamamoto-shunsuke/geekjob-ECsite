package kagoyume;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class WebAPIHelper {
	//詳細検索用URL
	static String lookupURL = "https://shopping.yahooapis.jp/ShoppingWebService/V1/json/itemLookup";
	//検索用URL
	static String searchURL = "https://shopping.yahooapis.jp/ShoppingWebService/V1/json/itemSearch";

	static String appID = "dj00aiZpPUdPZjVHbkFFQ2VwYiZzPWNvbnN1bWVyc2VjcmV0Jng9OTk-";

	URL url;
	HttpURLConnection con;
	JsonNode root;
	ProductDataBean pdb;
	ArrayList<ProductDataBean> pdbList;
	String requestQuery;
	int totalResult;
	
	//コンストラクタ
	public WebAPIHelper(){
		url = null;
		con = null;
		root = null;
		pdb = null;
		pdbList = null;
		requestQuery = null;
		totalResult = 0;
	}

	//商品の詳細検索用のURLを返すメソッド
	//wordにはフォームで入力した値が入る。
	public URL getURL1(String word) throws Exception {
		try {
			return new URL(lookupURL+"?appid="+appID+"&itemcode="+word+"&responsegroup=large&image_size=600");

		} catch (MalformedURLException e) {
			throw new Exception(e.getMessage());
		}
	}
	//検索用URLを返している。
	//wordにはフォームで入力した値が入る。
	public URL getURL(String word) throws Exception {
		try {
			//検索したいURLを作成している。
			return new URL(searchURL+"?appid="+appID+"&query="+word);
		} catch (MalformedURLException e) {
			throw new Exception(e.getMessage());
		}
	}

	//接続用メソッド
	public HttpURLConnection connect(URL url)throws Exception {
		//接続
		try {
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("GET");
			con.connect();
			int responceCode = con.getResponseCode();

			//接続失敗ならエラー
			if(responceCode != HttpURLConnection.HTTP_OK) {
				throw new Exception("WebAPI Connection Error");
			}

			return con;
		}catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	//JsonNodeを返すメソッド
	public JsonNode getJsonNode(HttpURLConnection con) throws Exception {
		try {
			//JSON文字列の読み取り
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			//readLineはURL文字列を一行一行読み込んでいくシステム
			String json = br.readLine();
			br.close();
			//JSON文字列をJsonNodeに変換
			ObjectMapper mapper = new ObjectMapper();
			JsonNode root = mapper.readTree(json);

			return root;
		}catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	//pdbに商品詳細をマッピング
	public void pdbMapping(JsonNode root, String code) {
		pdb = new ProductDataBean();
		String imgURL = root.get("ResultSet").get("0").get("Result").get("0").get("ExImage").get("Url").textValue();
		String thumbnail = root.get("ResultSet").get("0").get("Result").get("0").get("Image").get("Small").textValue();
		String name = root.get("ResultSet").get("0").get("Result").get("0").get("Name").textValue();
		String price = root.get("ResultSet").get("0").get("Result").get("0").get("Price").get("_value").textValue();
		String description = root.get("ResultSet").get("0").get("Result").get("0").get("Description").textValue();
		String review = root.get("ResultSet").get("0").get("Result").get("0").get("Review").get("Rate").textValue();
		String count = root.get("ResultSet").get("0").get("Result").get("0").get("Review").get("Count").textValue();
		String storeName = root.get("ResultSet").get("0").get("Result").get("0").get("Store").get("Name").textValue();
		String storeReview = root.get("ResultSet").get("0").get("Result").get("0").get("Store").get("Ratings").get("Rate").textValue();
		String storeCount = root.get("ResultSet").get("0").get("Result").get("0").get("Store").get("Ratings").get("Count").textValue();

		pdb.setImgURL(imgURL);
		pdb.setThumbnail(thumbnail);
		pdb.setName(name);
		pdb.setCode(code);
		pdb.setPrice(price);
		pdb.setDescription(description);
		pdb.setReview(review);
		pdb.setCount(count);
		pdb.setStoreName(storeName);
		pdb.setStoreReview(storeReview);
		pdb.setStoreCount(storeCount);
	}

	//pdbListに商品詳細pdbを格納するメソッド
	public void pdbListMapping(JsonNode root) {
		//thisはメンバ変数とローカル変数を区別するために使う。
		this.pdbList = new ArrayList<ProductDataBean>();
		//検索結果数がtotlresultに入っている。
		totalResult = Integer.parseInt(root.get("ResultSet").get("totalResultsAvailable").textValue());
		//top.jspで検索したワードがrequestQueryに入っている。
		requestQuery = root.get("ResultSet").get("0").get("Result").get("Request").get("Query").textValue();
		//totalresultが0でないとき、0～20までの複合検索結果をJavaBeansに入れ、それをすべてpdbに入れたものを最後にpdbListMappingに入れている。
		if(totalResult != 0) {
			for(int i = 0; i < 20 && i < totalResult; i++) {
				String num = String.valueOf(i);
				//それぞれjsonNode化したものをpdbに入れて、pdblistに加えてる。
				String thumbnail = root.get("ResultSet").get("0").get("Result").get(num).get("Image").get("Small").textValue();
				String name = root.get("ResultSet").get("0").get("Result").get(num).get("Name").textValue();
				String code = root.get("ResultSet").get("0").get("Result").get(num).get("Code").textValue();
				String price = root.get("ResultSet").get("0").get("Result").get(num).get("Price").get("_value").textValue();

				pdb = new ProductDataBean();
				pdb.setThumbnail(thumbnail);
				pdb.setName(name);
				pdb.setPrice(price);
				pdb.setCode(code);
				pdbList.add(pdb);

			}

		}
	}
		public void lookup(String code) throws Exception {
			try {

			url = getURL1(code);
			//urlに接続している。
			con = connect(url);
			//getjsonNodeでjsonを取得している。
			root = getJsonNode(con);
			if(root.get("ResultSet").get("totalResultsReturned").intValue() > 0) {
				//pdpMappingでnum,name,price等を複合検索している。
				pdbMapping(root, code);
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
}

	public void search(String word) throws Exception {
		try {
			//int n = Integer.parseInt(num);
			
			
			//上で検索したいURLを作成したものを変数「url」に入れている。
			URL url = getURL(word);
			//urlに接続している。
			HttpURLConnection con = connect(url);
			//getjsonNodeでjsonを取得している。
			root = getJsonNode(con);
			//取得したjsonをpdbListMappingに入れている。
			pdbListMapping(root);

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
}