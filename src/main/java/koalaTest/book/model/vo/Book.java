package koalaTest.book.model.vo;

public class Book {
	private int bookNo;
	private String bookName;
	private String bookWriter;
	private String bookPub;
	private String bookPubdate;
	private String bookImg;
	private int bookPrice;
	private String bookWonPrice;
	private String bookCate;
	private String bookInfo;
	private String bookInfo2;
	private String bookInfoMokcha;
	private String bookState;
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Book(int bookNo, String bookName, String bookWriter, String bookPub, String bookPubdate, String bookImg,
			int bookPrice, String bookWonPrice, String bookCate, String bookInfo, String bookInfo2,
			String bookInfoMokcha, String bookState) {
		super();
		this.bookNo = bookNo;
		this.bookName = bookName;
		this.bookWriter = bookWriter;
		this.bookPub = bookPub;
		this.bookPubdate = bookPubdate;
		this.bookImg = bookImg;
		this.bookPrice = bookPrice;
		this.bookWonPrice = bookWonPrice;
		this.bookCate = bookCate;
		this.bookInfo = bookInfo;
		this.bookInfo2 = bookInfo2;
		this.bookInfoMokcha = bookInfoMokcha;
		this.bookState = bookState;
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
	public String getBookWriter() {
		return bookWriter;
	}
	public void setBookWriter(String bookWriter) {
		this.bookWriter = bookWriter;
	}
	public String getBookPub() {
		return bookPub;
	}
	public void setBookPub(String bookPub) {
		this.bookPub = bookPub;
	}
	public String getBookPubdate() {
		return bookPubdate;
	}
	public void setBookPubdate(String bookPubdate) {
		this.bookPubdate = bookPubdate;
	}
	public String getBookImg() {
		return bookImg;
	}
	public void setBookImg(String bookImg) {
		this.bookImg = bookImg;
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
	public String getBookCate() {
		return bookCate;
	}
	public void setBookCate(String bookCate) {
		this.bookCate = bookCate;
	}
	public String getBookInfo() {
		return bookInfo;
	}
	public void setBookInfo(String bookInfo) {
		this.bookInfo = bookInfo;
	}
	public String getBookInfo2() {
		return bookInfo2;
	}
	public void setBookInfo2(String bookInfo2) {
		this.bookInfo2 = bookInfo2;
	}
	public String getBookInfoMokcha() {
		return bookInfoMokcha;
	}
	public void setBookInfoMokcha(String bookInfoMokcha) {
		this.bookInfoMokcha = bookInfoMokcha;
	}
	public String getBookState() {
		return bookState;
	}
	public void setBookState(String bookState) {
		this.bookState = bookState;
	}
}
