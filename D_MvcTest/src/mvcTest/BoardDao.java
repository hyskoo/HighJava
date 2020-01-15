package mvcTest;

import java.util.List;

/**
 * 실제 DB와 연결해서 SQL문을 수행하며 결과를 작성하여 
 *  Service에 전달하는 DAO의 interface
 */
public interface BoardDao {
	
	/**
	 *  BoardVO에 담겨진 자료를 DB에 insert하는 메소드
	 * @param bv  DB에 insert할 자료가 저장된 BoardVO객체
	 * @return DB작업이 성공하면 1이상의 값이 반환되고 실패하면 0이 반환된다.
	 */
	public int insertBoard(BoardVO bv);
	
	/**
	 * 주어진 회원ID가 존재하는지 여부를 알아내는 메소드
	 * @param boardNo  검색할 회원 ID
	 * @return 해당 회원ID가 있으면 true 없으면 false
	 */
	public boolean getBoardId(int boardNo);
	
	
	
	/**
	 * DB의 jdbc_board테이블의 전체 레코드를 가져와서 List에 담아서 반환하는 메소드
	 * @return BoardVO객체를 담고있는 List객체
	 */
	public List<BoardVO> getBoardList();

	
	/**
	 * 하나의 BoardVO자료를 이용하여 DB를 update하는 메소드
	 * @param mv  update할 회원벙조가 들어있는 BoardVO객체
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	public int updateBoard(BoardVO bv);
	
	
	/**
	 * 회원 ID를 매개변수로 받아서 그 회원정보를 삭제하는 메소드
	 * @param memId 삭제할 회원 ID
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	public int deleteBoard(int boardNo);

	
	/**
	 * BoardVO 객체에 값을 담아서 Search하는 메소드
	 * @param bv 검색할 boardVO 객체
	 * @return BoardVO객체를 담고있는 List객체
	 */
	public List<BoardVO> getSearchBoard(BoardVO bv);
	
	
	
	
}
