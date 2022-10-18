package koalaTest.admin.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import koalaTest.book.model.service.BookService;
import koalaTest.book.model.vo.Book;

/**
 * Servlet implementation class UserRegServlet
 */
@WebServlet(name = "AddBook", urlPatterns = { "/addBook.do" })
public class AddBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddBookServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. 인코딩
		request.setCharacterEncoding("utf-8");
		// 2. 값추출
		String root = getServletContext().getRealPath("/");//webapp폴더의 절대 경로
		String saveDirectory = root+"img/book";
		int maxSize = 10*1024*1024;
		MultipartRequest mRequest=
				new MultipartRequest(request,saveDirectory,maxSize,"UTF-8",new DefaultFileRenamePolicy());
		String bookName = mRequest.getParameter("bookName");
		String bookWriter = mRequest.getParameter("bookWriter");
		String bookPub = mRequest.getParameter("bookPub");
		String bookPubDate = mRequest.getParameter("bookPubDate");
		String bookImg = mRequest.getFilesystemName("bookImg");
		int bookPrice = Integer.parseInt(mRequest.getParameter("bookPrice"));
		String bookCate = mRequest.getParameter("bookCate");
		String bookInfo = mRequest.getParameter("bookInfo");
		String bookInfo2 = mRequest.getParameter("bookInfo2");
		String bookInfoMokcha = mRequest.getParameter("bookInfoMokcha");
		// 3. 비즈니스로직
		Book book = new Book();
		book.setBookCate(bookCate);
		book.setBookImg(bookImg);
		book.setBookInfo(bookInfo);
		book.setBookInfo2(bookInfo2);
		book.setBookInfoMokcha(bookInfoMokcha);
		book.setBookName(bookName);
		book.setBookPrice(bookPrice);
		book.setBookPub(bookPub);
		book.setBookPubdate(bookPubDate);
		book.setBookWriter(bookWriter);
		BookService service = new BookService();
		int result = service.insertBook(book);
		// 4. 결과처리
		PrintWriter out = response.getWriter();
		if(result==1){
			out.print(1);
		}else{
			out.print(0);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
