package Dao;

public interface BoardDao {

	boolean getBoardNo(int boardNo);
	
	void insertBoard(String title, String writer, String cont);

	void updateBoard(String title, String cont, int boardNo);

	
}
