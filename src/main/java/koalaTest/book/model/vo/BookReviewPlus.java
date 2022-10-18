package koalaTest.book.model.vo;

import java.util.ArrayList;

public class BookReviewPlus {
	private Book result;
	private double avg;
	private ArrayList<BookReview> selectBookCommentList;
	public BookReviewPlus() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BookReviewPlus(Book result, double avg, ArrayList<BookReview> selectBookCommentList) {
		super();
		this.result = result;
		this.avg = avg;
		this.selectBookCommentList = selectBookCommentList;
	}
	public Book getResult() {
		return result;
	}
	public void setResult(Book result) {
		this.result = result;
	}
	public double getAvg() {
		return avg;
	}
	public void setAvg(double avg) {
		this.avg = avg;
	}
	public ArrayList<BookReview> getSelectBookCommentList() {
		return selectBookCommentList;
	}
	public void setSelectBookCommentList(ArrayList<BookReview> selectBookCommentList) {
		this.selectBookCommentList = selectBookCommentList;
	}
	
	
}
