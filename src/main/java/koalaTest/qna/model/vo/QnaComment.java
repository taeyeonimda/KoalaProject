package koalaTest.qna.model.vo;

public class QnaComment {
	private int qnaCommentNo;
	private String qnaCommentWriter;
	private String qnaCommentContent;
	private String qnaCommentRegDate;
	private int qnaRef;
	private int qnaRefNo;
	public QnaComment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public QnaComment(int qnaCommentNo, String qnaCommentWriter, String qnaCommentContent, String qnaCommentRegDate,
			int qnaRef, int qnaRefNo) {
		super();
		this.qnaCommentNo = qnaCommentNo;
		this.qnaCommentWriter = qnaCommentWriter;
		this.qnaCommentContent = qnaCommentContent;
		this.qnaCommentRegDate = qnaCommentRegDate;
		this.qnaRef = qnaRef;
		this.qnaRefNo = qnaRefNo;
	}
	public String getQnaCommentContentBr() {
		return qnaCommentContent.replaceAll("\r\n", "<br>");
	}
	public int getQnaCommentNo() {
		return qnaCommentNo;
	}
	public void setQnaCommentNo(int qnaCommentNo) {
		this.qnaCommentNo = qnaCommentNo;
	}
	public String getQnaCommentWriter() {
		return qnaCommentWriter;
	}
	public void setQnaCommentWriter(String qnaCommentWriter) {
		this.qnaCommentWriter = qnaCommentWriter;
	}
	public String getQnaCommentContent() {
		return qnaCommentContent;
	}
	public void setQnaCommentContent(String qnaCommentContent) {
		this.qnaCommentContent = qnaCommentContent;
	}
	public String getQnaCommentRegDate() {
		return qnaCommentRegDate;
	}
	public void setQnaCommentRegDate(String qnaCommentRegDate) {
		this.qnaCommentRegDate = qnaCommentRegDate;
	}
	public int getQnaRef() {
		return qnaRef;
	}
	public void setQnaRef(int qnaRef) {
		this.qnaRef = qnaRef;
	}
	public int getQnaRefNo() {
		return qnaRefNo;
	}
	public void setQnaRefNo(int qnaRefNo) {
		this.qnaRefNo = qnaRefNo;
	}
	
	
}
