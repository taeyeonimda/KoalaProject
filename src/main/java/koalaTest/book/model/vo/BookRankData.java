package koalaTest.book.model.vo;

import java.util.ArrayList;

public class BookRankData {
	private ArrayList<BookRank> salesRank;
	private ArrayList<BookRank> starRank;
	private ArrayList<BookRank> reviewRank;
	
	public BookRankData() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public BookRankData(ArrayList<BookRank> salesRank, ArrayList<BookRank> starRank, ArrayList<BookRank> reviewRank) {
		super();
		this.salesRank = salesRank;
		this.starRank = starRank;
		this.reviewRank = reviewRank;
	}
	
	public ArrayList<BookRank> getSalesRank() {
		return salesRank;
	}
	public void setSalesRank(ArrayList<BookRank> salesRank) {
		this.salesRank = salesRank;
	}
	public ArrayList<BookRank> getStarRank() {
		return starRank;
	}
	public void setStarRank(ArrayList<BookRank> starRank) {
		this.starRank = starRank;
	}
	public ArrayList<BookRank> getReviewRank() {
		return reviewRank;
	}
	public void setReviewRank(ArrayList<BookRank> reviewRank) {
		this.reviewRank = reviewRank;
	}
	
}
