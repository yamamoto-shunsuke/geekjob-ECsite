package kagoyume;

import java.io.Serializable;
import java.sql.Timestamp;

public class ProductDataBean implements Serializable{
	private String imgURL;
	private String thumbnail;
	private String name;
	private int price;
	private String code;
	private String review;
	private String count;
	private String description;
	private String storeName;
	private String storeReview;
	private String storeCount;
	private int type;
	private Timestamp date;

	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}
	public String getImgURL() {
		return imgURL;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public String getThumbnail() {
		return thumbnail;
	}


	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}

	public void setPrice(String price) {
		this.price = Integer.parseInt(price);
	}
	public int getPrice() {
		return price;
	}

	public void setCode(String code) {
		this.code = code;
	}
	public String getCode() {
		return code;
	}

	public void setReview(String review) {
		this.review = review;
	}
	public String getReview() {
		return review;
	}

	public void setCount(String count) {
		this.count = count;
	}
	public String getCount() {
		return count;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	public String getDescription() {
		return description;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getStoreName() {
		return storeName;
	}

	public void setStoreReview(String storeReview) {
		this.storeReview = storeReview;
	}
	public String getStoreReview() {
		return storeReview;
	}

	public void setStoreCount(String storeCount) {
		this.storeCount = storeCount;
	}
	public String getStoreCount() {
		return storeCount;
	}

	public void setType(int type) {
		this.type = type;
	}
	public int getType() {
		return type;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}
	public Timestamp getDate() {
		return date;
	}

}