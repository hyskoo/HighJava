package mvcTest;

import java.util.List;
import java.util.Scanner;

public class MvcBoard {

	BoardService  boardservice = BoardServiceImpl.getInstance();
	
	private Scanner scan = new Scanner(System.in); 
	
	/**
	 * 메뉴를 출력하는 메서드
	 */
	public void displayMenu(){
		System.out.println();
		System.out.println("----------------------");
		System.out.println("  === 작 업 선 택 ===");
		System.out.println("  1. 글 작성");
		System.out.println("  2. 글 수정");
		System.out.println("  3. 글 삭제");
		System.out.println("  4. 전체 목록 출력");
		System.out.println("  5. 글 검색");
		System.out.println("  9. 작업 끝.");
		System.out.println("----------------------");
		System.out.print("원하는 작업 선택 >> ");
	}
	
	/**
	 * 프로그램 시작메서드
	 */
	public void start(){
		int choice;
		do{
			displayMenu(); //메뉴 출력
			choice = Integer.parseInt(scan.nextLine()); // 메뉴번호 입력받기
			switch(choice){
				case 1 :  // 글 작성
					insertBoard();
					break;
				case 2 :  // 글 수정
					updateBoard();
					break;
				case 3 :  // 글 삭제
					deleteBoard();
					break;
				case 4 :  // 전체 목록 출력
					dispalyBoard();
					break;
				case 5 :  // 글 검색
					search();
					break;
				case 9 :  // 작업 끝
					System.out.println("작업을 마칩니다.");
					break;
				default :
					System.out.println("번호를 잘못 입력했습니다. 다시입력하세요");
			}
		}while(choice!=9);
	}

	private void insertBoard() {
		System.out.println();
		System.out.println("글작성을 시작합니다.");
		
		System.out.print("제목 : ");
		String title = scan.nextLine().trim();
		System.out.print("작성자 : ");
		String writer = scan.nextLine().replace(" ", "");
		System.out.print("내용 : ");
		String cont = scan.nextLine().trim();
		
		BoardVO bv = new BoardVO();
		bv.setBoard_title(title);
		bv.setBoard_writer(writer);
		bv.setBoard_content(cont);
		
		Object obj = boardservice.insertBoard(bv);
		if (obj == null) {
			System.out.println("글 작성에 성공하셨습니다.");
		} else {
			System.out.println("글 작성에 실패하셨습니다.");
		}
		
		System.out.println("--------------------------------------------------------");
		System.out.println("출력작업 끝...");
	}

	private void updateBoard() {
		BoardVO check = null;
		int boardNo;
		String title;
		String cont;
		System.out.println();
		
		do {
			System.out.println("수정할 글의 번호를 선택해주세요.");
			boardNo = Integer.parseInt(scan.nextLine());
			
			check = boardservice.getBoardId(boardNo);
			
			if (check == null) {
				System.out.println("수정할 글이 존재하지 않습니다.");
				return;
			}
		} while (check == null);
		
		System.out.print("제목 : ");
		title = scan.nextLine();
		System.out.print("내용 : ");
		cont = scan.nextLine();
		
		BoardVO bv = new BoardVO();
		bv.setBoard_no(boardNo);
		bv.setBoard_title(title);
		bv.setBoard_content(cont);
		
		int cnt = boardservice.updateBoard(bv);
		
		if (cnt > 0) {
			System.out.println("수정에 성공하셨습니다.");
		} else {
			System.out.println("수정에 실패하셨습니다.");
		}
		
		System.out.println("--------------------------------------------------------");
		System.out.println("출력작업 끝...");
		
	}
	
	private void deleteBoard() {
		BoardVO check = null;
		System.out.println();
		int boardNo;
		do {
			System.out.println("삭제할 글의 제목의 번호를 입력해주세요.");
			boardNo = Integer.parseInt(scan.nextLine());
			
			check = boardservice.getBoardId(boardNo);
			if (check == null) {
				System.out.println("삭제할 글이 존재하지 않습니다.");
				return;
			}
		} while (check == null);

		int cnt = boardservice.deleteBoard(boardNo);
		
		if (cnt > 0) {
			System.out.println("성공적으로 삭제하였습니다.");
		} else {
			System.out.println("삭제에 실패하셨습니다.");
		}
		
		System.out.println("--------------------------------------------------------");
		System.out.println("출력작업 끝...");

	}

	/*
	 * 전체 회원을 출력
	 */
	private void dispalyBoard() {
		System.out.println();
		System.out.println("--------------------------------------------------------");
		System.out.println("번호\t제목\t작성자\t작성날짜\t내용");
		System.out.println("--------------------------------------------------------");
		
		List<BoardVO> blist = boardservice.getBoardList();
		
		
		if (blist.size() == 0) {
			System.out.println("출력할 회원정보가 없습니다.");
		} else {
			for (BoardVO bv : blist) {
				System.out.println(
						bv.getBoard_no() + "\t" 
						+ bv.getBoard_title() + "\t" 
						+ bv.getBoard_writer() + "\t"
						+ bv.getBoard_date() + "\t"
						+ bv.getBoard_content() + "\t" );
			}
		}
			System.out.println("--------------------------------------------------------");
			System.out.println("출력작업 끝...");
	}
	
	private void search() {
		System.out.println();
		System.out.println("검색할 정보를 입력해주세요.");
		System.out.print("제목 : ");
		String title = scan.nextLine().trim();
		System.out.print("작성자 : ");
		String writer = scan.nextLine().replace(" ", "");
		System.out.print("내용 : ");
		String cont = scan.nextLine().trim();
		
		BoardVO bv = new BoardVO();
		bv.setBoard_title(title);
		bv.setBoard_writer(writer);
		bv.setBoard_content(cont);
		
		List<BoardVO> blist = boardservice.getSearchBoard(bv);
		
		System.out.println();
		System.out.println("--------------------------------------------------------");
		System.out.println("번호\t제목\t작성자\t작성날짜\t내용");
		System.out.println("--------------------------------------------------------");
		if (blist.size() == 0) {
			System.out.println("출력할 회원정보가 없습니다.");
		} else {
			for (BoardVO bv1 : blist) {
				System.out.println(
						bv1.getBoard_no() + "\t" 
						+ bv1.getBoard_title() + "\t" 
						+ bv1.getBoard_writer() + "\t"
						+ bv1.getBoard_date() + "\t"
						+ bv1.getBoard_content() + "\t" );
			}
		}
		
		System.out.println("---------------------------------");
		System.out.println("출력작업 끝");
		
		
	}

	public static void main(String[] args) {
		MvcBoard board = new MvcBoard();
		board.start();
	}
}
