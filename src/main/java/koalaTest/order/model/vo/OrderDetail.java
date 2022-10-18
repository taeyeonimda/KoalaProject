package koalaTest.order.model.vo;

public class OrderDetail {
	
	private int orderDetailNo;
	private int orderNo;
	private int bookNo;
	private int orderDetailQuan;
	private String bookImg;
	private String bookName;
	private String bookWonPrice;
		
	public OrderDetail() {
		super();
	}

	public OrderDetail(int orderDetailNo, int orderNo, int bookNo, int orderDetailQuan, String bookImg, String bookName,
			String bookWonPrice) {
		super();
		this.orderDetailNo = orderDetailNo;
		this.orderNo = orderNo;
		this.bookNo = bookNo;
		this.orderDetailQuan = orderDetailQuan;
		this.bookImg = bookImg;
		this.bookName = bookName;
		this.bookWonPrice = bookWonPrice;
	}

	public int getOrderDetailNo() {
		return orderDetailNo;
	}

	public void setOrderDetailNo(int orderDetailNo) {
		this.orderDetailNo = orderDetailNo;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public int getBookNo() {
		return bookNo;
	}

	public void setBookNo(int bookNo) {
		this.bookNo = bookNo;
	}

	public int getOrderDetailQuan() {
		return orderDetailQuan;
	}

	public void setOrderDetailQuan(int orderDetailQuan) {
		this.orderDetailQuan = orderDetailQuan;
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

	public String getBookWonPrice() {
		return bookWonPrice;
	}

	public void setBookWonPrice(String bookWonPrice) {
		this.bookWonPrice = bookWonPrice;
	}
	
}
