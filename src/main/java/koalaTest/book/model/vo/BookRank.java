package koalaTest.book.model.vo;

public class BookRank {
	private int bookNo;
	private String bookName;
	private String bookImg;
	private int salesQuan;
	private double starAvg;
	private int reviewQuan;
	private int bookRank;
	
	public BookRank() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BookRank(int bookNo, String bookName, String bookImg, int salesQuan, double starAvg, int reviewQuan,
			int bookRank) {
		super();
		this.bookNo = bookNo;
		this.bookName = bookName;
		this.bookImg = bookImg;
		this.salesQuan = salesQuan;
		this.starAvg = starAvg;
		this.reviewQuan = reviewQuan;
		this.bookRank = bookRank;
	}

	public int getBookNo() {
		return bookNo;
	}

	public void setBookNo(int bookNo) {
		this.bookNo = bookNo;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookImg() {
		return bookImg;
	}

	public void setBookImg(String bookImg) {
		this.bookImg = bookImg;
	}

	public int getSalesQuan() {
		return salesQuan;
	}

	public void setSalesQuan(int salesQuan) {
		this.salesQuan = salesQuan;
	}

	public double getStarAvg() {
		return starAvg;
	}

	public void setStarAvg(double starAvg) {
		this.starAvg = starAvg;
	}

	public int getReviewQuan() {
		return reviewQuan;
	}

	public void setReviewQuan(int reviewQuan) {
		this.reviewQuan = reviewQuan;
	}

	public int getBookRank() {
		return bookRank;
	}

	public void setBookRank(int bookRank) {
		this.bookRank = bookRank;
	}	
}
