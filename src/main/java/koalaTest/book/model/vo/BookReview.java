package koalaTest.book.model.vo;

public class BookReview {
	private int bookNo;
	private int reviewNo;
	private String brContent;
	private String brDate;
	private int brStar;
	private String reviewWriter;
	public BookReview() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BookReview(int bookNo, int reviewNo, String brContent, String brDate, int brStar, String reviewWriter) {
		super();
		this.bookNo = bookNo;
		this.reviewNo = reviewNo;
		this.brContent = brContent;
		this.brDate = brDate;
		this.brStar = brStar;
		this.reviewWriter = reviewWriter;
	}
	public int getBookNo() {
		return bookNo;
	}
	public void setBookNo(int bookNo) {
		this.bookNo = bookNo;
	}
	public int getReviewNo() {
		return reviewNo;
	}
	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
	}
	public String getBrContent() {
		return brContent;
	}
	public void setBrContent(String brContent) {
		this.brContent = brContent;
	}
	public String getBrDate() {
		return brDate;
	}
	public void setBrDate(String brDate) {
		this.brDate = brDate;
	}
	public int getBrStar() {
		return brStar;
	}
	public void setBrStar(int brStar) {
		this.brStar = brStar;
	}
	public String getReviewWriter() {
		return reviewWriter;
	}
	public void setReviewWriter(String reviewWriter) {
		this.reviewWriter = reviewWriter;
	}
	
	
	
	
}
