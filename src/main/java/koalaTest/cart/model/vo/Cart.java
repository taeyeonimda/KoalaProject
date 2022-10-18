package koalaTest.cart.model.vo;

public class Cart {
	private int cartNo;
	private int userNo;
	private int bookNo;
	private int cartQuan;
	private String bookImg;
	private String bookName;
	private int bookPrice;
	private String bookWonPrice;

	public Cart() {
		super();
	}

	public Cart(int cartNo, int userNo, int bookNo, int cartQuan, String bookImg, String bookName, int bookPrice,
			String bookWonPrice) {
		super();
		this.cartNo = cartNo;
		this.userNo = userNo;
		this.bookNo = bookNo;
		this.cartQuan = cartQuan;
		this.bookImg = bookImg;
		this.bookName = bookName;
		this.bookPrice = bookPrice;
		this.bookWonPrice = bookWonPrice;
	}

	public int getCartNo() {
		return cartNo;
	}

	public void setCartNo(int cartNo) {
		this.cartNo = cartNo;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public int getBookNo() {
		return bookNo;
	}

	public void setBookNo(int bookNo) {
		this.bookNo = bookNo;
	}

	public int getCartQuan() {
		return cartQuan;
	}

	public void setCartQuan(int cartQuan) {
		this.cartQuan = cartQuan;
	}

	public String getBookImg() {
		return bookImg;
	}

	public void setBookImg(String bookImg) {
		this.bookImg = bookImg;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public int getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(int bookPrice) {
		this.bookPrice = bookPrice;
	}

	public String getBookWonPrice() {
		return bookWonPrice;
	}

	public void setBookWonPrice(String bookWonPrice) {
		this.bookWonPrice = bookWonPrice;
	}
	
}
