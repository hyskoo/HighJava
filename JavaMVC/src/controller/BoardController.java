package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import Service.BoardService;
import Service.BoardServiceImpl;

public class BoardController {
	private Connection conn;
	private Statement st;
	private PreparedStatement ps;
	private ResultSet rs;
	
	private Scanner scan = new Scanner(System.in); 
	
	BoardService service = BoardServiceImpl.getInstance();
	
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
					service.insertBoard();
					break;
				case 2 :  // 글 수정
					service.updateBoard();
					break;
				case 3 :  // 글 삭제
					service.deleteBoard();
					break;
				case 4 :  // 전체 목록 출력
					service.dispalyBoard();
					break;
				case 5 :  // 글 검색
					service.search();
					break;
				case 9 :  // 작업 끝
					System.out.println("작업을 마칩니다.");
					break;
				default :
					System.out.println("번호를 잘못 입력했습니다. 다시입력하세요");
			}
		}while(choice!=9);
	}
	
	public static void main(String[] args) {
		BoardController cont = new BoardController();
		
		cont.start();
	}
}
