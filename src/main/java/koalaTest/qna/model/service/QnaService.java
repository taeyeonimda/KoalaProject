package koalaTest.qna.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import common.JDBCTemplate;
import koalaTest.qna.model.dao.QnaDao;
import koalaTest.qna.model.vo.Qna;
import koalaTest.qna.model.vo.QnaComment;
import koalaTest.qna.model.vo.QnaPageData;
import koalaTest.qna.model.vo.QnaViewData;

public class QnaService {
	private QnaDao dao;
	

	public QnaService() {
		super();
		dao = new QnaDao();
	}

	// 페이징
	public QnaPageData selectQnaList(int reqPage) {
		Connection conn = JDBCTemplate.getConnection();
		
		int numPerPage = 10;
		
		int end = numPerPage*reqPage;
		int start = end - numPerPage + 1;
		ArrayList<Qna> list = dao.selectQnaList(conn,start,end);
		
		int totalCount = dao.selectQnaCount(conn);
		
		//전체 페이지
		int totalPage = 0;
		if(totalCount%numPerPage == 0) {
			totalPage = totalCount/numPerPage;
		}else {
			totalPage = totalCount/numPerPage + 1;
		}
		
		//지정해줘야할 값 페이지 네비게이션 사이즈
		int pageNaviSize = 5;
		
		//페이지 네비게이션 시작번호 지정
		//reqPage 1~5 -> 1부터 시작해서 5개
		//reqPage 6~10-> 6부터 5개
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize + 1;
		
		//페이지 네비게이션 제작 시작
		String pageNavi = "<ul class='qna-pagination circle-style'>";
		
		//이전버튼
		if(pageNo != 1) {
			pageNavi += "<li>";
			pageNavi += "<a class='qna-page-item' href='/qnaList.do?reqPage="+(pageNo-1)+"'>";
			pageNavi += "<span class='material-icons'>chevron_left</span>";
			pageNavi += "</a></li>";
		}
		//페이지 숫자
		for(int i=0;i<pageNaviSize;i++) {
			if(pageNo == reqPage) {
				pageNavi += "<li>";
				pageNavi += "<a class='qna-page-item active-page' href='/qnaList.do?reqPage="+pageNo+"'>";
				pageNavi += pageNo;
				pageNavi += "</a></li>";
			}else {
				pageNavi += "<li>";
				pageNavi += "<a class='qna-page-item' href='/qnaList.do?reqPage="+pageNo+"'>";
				pageNavi += pageNo;
				pageNavi += "</a></li>";
			}
			pageNo++;
			if(pageNo > totalPage) {
				break;
			}
		}
		//다음버튼
		if(pageNo<=totalPage) {
			pageNavi += "<li>";
			pageNavi += "<a class='qna-page-item' href='/qnaList.do?reqPage="+pageNo+"'>";
			pageNavi += "<span class='material-icons'>chevron_right</span>";
			pageNavi += "</a></li>";
		}

		
		// +=을 안하면 위에서 아무리 로직을 돌려도 pageNavi에 저장을 하지않기 때문에
		// 하단버튼이 생성되지 않는다.]
		// pageNavi = "</ul>"; 로 하면 그냥 ul로 대체될뿐이다.
		pageNavi += "</ul>";
		
		QnaPageData qpd = new QnaPageData(list,pageNavi);
		JDBCTemplate.close(conn);
				
		return qpd;
	}

	public QnaViewData selectOneQna(int qnaNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.updateReadCount(conn,qnaNo);
		if(result>0) { // 업데이트 성공
			JDBCTemplate.commit(conn);
			
			
			Qna q = dao.selectOneQna(conn,qnaNo);
			
			ArrayList<QnaComment> commentList = dao.selectQnaCommentList(conn,qnaNo);
			QnaViewData qvd = new QnaViewData(q,commentList);
			JDBCTemplate.close(conn);
			return qvd;
		}else { // 업데이트 실패
			JDBCTemplate.rollback(conn);
			JDBCTemplate.close(conn);
			return null;
		}
	}

	public int insertQnaComment(QnaComment qc) {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.insertQnaComment(conn,qc);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int insertQna(Qna q) {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.insertQna(conn, q);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int qnaDeleteComment(int qnaCommentNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.qnaDeleteComment(conn,qnaCommentNo);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int qnaUpdateComment(QnaComment qc) {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.qnaupdateComment(conn, qc);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public Qna deleteQna(int qnaNo) {
		Connection conn = JDBCTemplate.getConnection();
		Qna q = dao.selectOneQna(conn, qnaNo);
		int result = dao.deleteQna(conn,qnaNo);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
			q = null;
		}
		JDBCTemplate.close(conn);
		return q;
	}

	public QnaPageData searchQnaList(int reqPage, String keyword) {
		Connection conn = JDBCTemplate.getConnection();
		//1.한페이지당 게시물 수 지정 ->10개
		int numPerPage = 10;
		// 요청페이지 1 -> 가장 최신 글 1~10
		// 요청페이지 2 -> 가장 최신 글 11~20
		int end = numPerPage*reqPage;
		int start = end - numPerPage + 1;//20 - 10 +1
		ArrayList<Qna> list = dao.searchQnaList(conn,start,end,keyword);
		//페이징처리
		//전체페이지 수 계산 ->전체 게시물 수 조회
		int totalCount = dao.searchQnaCount(conn,keyword);
		int totalPage =0;
		 if(totalCount%numPerPage ==0) {
			 totalPage = totalCount/numPerPage;
		 }else {
			 totalPage = totalCount/numPerPage+1;
		 }
		 
		 //지정해줘야할 값 페이지 네비게이션 사이즈
		 int pageNaviSize =5;
		 
		 //페이지 네비게이션 시작번호지정
		 //reqPage 1~5 ->1부터 시작해서 5개
		 //reqPage 6~10 -> 6,7,8,9,10
		 int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize+1;
		 
		 //페이지 네비게이션 제작 시작
		 String pageNavi="<ul class='qna-pagination circle-style'>";
		 //이전버튼
		 if(pageNo !=1) {
			 pageNavi +="<li>";
			 pageNavi +="<a class='qna-page-item' href='/qnaSearch.do?qnaSearchKeword="+keyword+"&reqPage="+(pageNo-1)+"'>";
			 pageNavi +="<span class='material-icons'>chevron_left</span>";
			 pageNavi +="</a></li>";
		 }
		 //페이지숫자
		 for(int i =0;i<pageNaviSize;i++) {
			 if(pageNo == reqPage) {
				 pageNavi +="<li>";
				 pageNavi +="<a class='qna-page-item active-page' href='/qnaSearch.do?qnaSearchKeyword="+keyword+"&reqPage="+pageNo+"'>";
				 pageNavi += pageNo;
				 pageNavi +="<span class='material-icons'>chevron_left</span>";
				 pageNavi +="</a></li>";
			 }else {
				 pageNavi +="<li>";
				 pageNavi +="<a class='qna-page-item' href='/qnaSearch.do?qnaSearchKeyword="+keyword+"&reqPage="+pageNo+"'>";
				 pageNavi += pageNo;
				 pageNavi +="<span class='material-icons'>chevron_left</span>";
				 pageNavi +="</a></li>";
			 }
			 pageNo++;
			 if(pageNo>totalPage) {
				break; 
			 }
		 }
		 //다음버튼
		 if(pageNo<=totalPage) {
			 pageNavi +="<li>";
			 pageNavi +="<a class='qna-page-item' href='/qnaSearch.do?searchKeyword="+keyword+"reqPage="+pageNo+"'>";
			 pageNavi +="<span class='material-icons'>chevron_right</span>";
			 pageNavi +="</a></li>";
		 }
		 pageNavi += "</ul>";
		 QnaPageData qpd = new QnaPageData(list,pageNavi);
		 JDBCTemplate.close(conn);
		 
		 return qpd;
	}
}
