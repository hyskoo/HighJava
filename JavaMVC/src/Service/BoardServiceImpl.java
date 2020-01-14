package Service;

import java.sql.SQLException;
import java.util.Scanner;

import Dao.BoardDao;
import Dao.BoardDaoImpl;
import kr.or.ddit.util.DBUtil2;

public class BoardServiceImpl implements BoardService {
	private Scanner scan = new Scanner(System.in);
	private static BoardServiceImpl instance;
	private BoardServiceImpl(){}
	
	public static BoardService getInstance() {
		if (instance == null) {
			instance = new BoardServiceImpl();
		}
		return instance;
	}

	BoardDao dao = BoardDaoImpl.getInstance();
	
	@Override
	public void insertBoard() {
		String title;
		String writer;
		String cont;
		
		System.out.println();
		System.out.println("글작성을 시작합니다.");
		
		System.out.print("제목 : ");
		title = scan.nextLine().trim();
		System.out.print("작성자 : ");
		writer = scan.nextLine().replace(" ", "");
		System.out.print("내용 : ");
		cont = scan.nextLine().trim();
		
		dao.insertBoard(title, writer, cont);
	}

	@Override
	public void updateBoard() {
		boolean check = true;
		int BoardNo;
		String title;
		String cont;
		System.out.println();
		
		do {
			System.out.println("수정할 글의 번호를 선택해주세요.");
			BoardNo = Integer.parseInt(scan.nextLine());
			
			check = dao.getBoardNo(BoardNo);
			
			if (check == false) {
				System.out.println("수정할 글이 존재하지 않습니다.");
				return;
			}
		} while (check == false);
		
		System.out.print("제목 : ");
		title = scan.nextLine().trim();
		System.out.print("내용 : ");
		cont = scan.nextLine().trim();
		
		dao.updateBoard(title,cont, BoardNo);
	}

	@Override
	public void deleteBoard() {
		
	}

	@Override
	public void dispalyBoard() {
		
	}

	@Override
	public void search() {
		
	}
}
