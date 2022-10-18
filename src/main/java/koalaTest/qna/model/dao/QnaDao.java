package koalaTest.qna.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import common.JDBCTemplate;
import koalaTest.qna.model.vo.Qna;
import koalaTest.qna.model.vo.QnaComment;

public class QnaDao {

	public ArrayList<Qna> selectQnaList(Connection conn, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Qna> list = new ArrayList<Qna>();
//		String query ="select * from (select rownum as rnum, n.* from (SELECT * FROM QNA order by qna_no desc)n)where rnum between ? and ?";
		String query ="select * from (select rownum as rnum, n.* from (select qna_no, qna_title, qna_writer, qna_content, read_count, reg_date, (select count(*) from qna_comment qc where QNA_REF = qna_no) commentCnt FROM QNA order by qna_no desc)n)where rnum between ? and ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Qna q = new Qna();
				q.setQnaNo(rset.getInt("qna_no"));
				q.setQnaTitle(rset.getString("qna_title"));
				q.setQnaWriter(rset.getString("qna_writer"));
				q.setQnaContent(rset.getString("qna_content"));
				q.setReadCount(rset.getInt("read_count"));
				q.setRegDate(rset.getString("reg_date"));
				q.setCommentCnt(rset.getInt("commentCnt"));
				list.add(q);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public int selectQnaCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int totalCount = 0;
		//별칭을 지정해서
		//아래에서 별칭을 이용해서 받는다
		String query = "select count(*) as cnt from qna";
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				totalCount = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return totalCount;
	}

	public Qna selectOneQna(Connection conn, int qnaNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Qna q = null;
		String query = "select * from qna where qna_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, qnaNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				q = new Qna();
				q.setQnaContent(rset.getString("qna_content"));
				q.setQnaNo(rset.getInt("qna_no"));
				q.setQnaTitle(rset.getString("qna_title"));
				q.setQnaWriter(rset.getString("qna_writer"));
				q.setReadCount(rset.getInt("read_count"));
				q.setRegDate(rset.getString("reg_date"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return q;
	}

	// 조회수 + 1
	public int updateReadCount(Connection conn, int qnaNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update qna set read_count = read_count+1 where qna_no=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, qnaNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int insertQnaComment(Connection conn, QnaComment qc) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into qna_comment values(qna_comment_no_seq.nextval,?,?,to_char(sysdate,'yyyy-mm-dd'),?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, qc.getQnaCommentWriter());
			pstmt.setString(2, qc.getQnaCommentContent());
			pstmt.setInt(3, qc.getQnaRef());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public ArrayList<QnaComment> selectQnaCommentList(Connection conn, int qnaNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<QnaComment> list = new ArrayList<QnaComment>();
		String query = "select * from qna_comment where qna_ref=? order by 1";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, qnaNo);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				QnaComment qc = new QnaComment();
				qc.setQnaCommentContent(rset.getString("qna_comment_content"));
				qc.setQnaCommentRegDate(rset.getString("qna_comment_reg_date"));
				qc.setQnaCommentNo(rset.getInt("qna_comment_no"));
				qc.setQnaRef(rset.getInt("qna_ref"));
				qc.setQnaCommentWriter(rset.getString("qna_comment_writer"));
				list.add(qc);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public int insertQna(Connection conn, Qna q) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into qna values(qna_no_seq.nextval,?,?,?,0,to_char(sysdate,'yyyy-mm-dd'),?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, q.getQnaTitle());
			pstmt.setString(2, q.getQnaWriter());
			pstmt.setString(3, q.getQnaContent());
			pstmt.setString(4, q.getFilePath());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int selectAllComment(Connection conn, int qnaNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int commentCnt = 0;
		String query = "select count(*) as cnt from qna_comment where qna_ref=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				commentCnt = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return commentCnt;
	}

	public int qnaDeleteComment(Connection conn, int qnaCommentNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from qna_comment where qna_comment_no =?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, qnaCommentNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int qnaupdateComment(Connection conn, QnaComment qc) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update qna_comment set qna_comment_content=? where qna_comment_no=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, qc.getQnaCommentContent());
			pstmt.setInt(2, qc.getQnaCommentNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int deleteQna(Connection conn, int qnaNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from qna where qna_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, qnaNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public ArrayList<Qna> searchQnaList(Connection conn, int start, int end, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Qna> Qna = new ArrayList<Qna>();
		String query = "select * from (select rownum as rnum, n.* from (select * from (select qna_no, qna_title, qna_writer, qna_content, read_count, reg_date, (select count(*) from qna_comment qc where QNA_REF = qna_no) commentCnt from qna) qna where qna_title like '%'||?||'%' order by qna_no desc)n) where rnum between ? and ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, keyword);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			DecimalFormat decFormat = new DecimalFormat("###,###");

			while(rset.next()) {
				Qna q = new Qna();
				q.setQnaNo(rset.getInt("qna_no"));
				q.setQnaTitle(rset.getString("qna_title"));
				q.setQnaWriter(rset.getString("qna_writer"));
				q.setRegDate(rset.getString("reg_date"));
				q.setReadCount(rset.getInt("read_count"));
				q.setCommentCnt(rset.getInt("commentCnt"));
				Qna.add(q);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return Qna;
	}

	public int searchQnaCount(Connection conn, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int totalCount = 0;
		String query = "select count(*) as cnt from qna where qna_title like '%'||?||'%' ";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, keyword);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				totalCount = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return totalCount;
	}

}
