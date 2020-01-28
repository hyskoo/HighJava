package mvcTest;

import java.util.List;


public class BoardServiceImpl implements BoardService {
	
	private static BoardServiceImpl instance;
	private BoardServiceImpl(){}
	
	public static BoardService getInstance() {
		if (instance == null) {
			instance = new BoardServiceImpl();
		}
		return instance;
	}
	
	BoardDao  boardDao = BoardDaoImpl.getInstance();

	@Override
	public Object insertBoard(BoardVO bv) {
		return boardDao.insertBoard(bv);
	}

	@Override
	public BoardVO getBoardId(int boardNo) {
		return boardDao.getBoardId(boardNo);
	}

	@Override
	public List<BoardVO> getBoardList() {
		return boardDao.getBoardList();
	}

	@Override
	public int updateBoard(BoardVO bv) {
		return boardDao.updateBoard(bv);
	}

	@Override
	public int deleteBoard(int boardNo) {
		return boardDao.deleteBoard(boardNo);
	}

	@Override
	public List<BoardVO> getSearchBoard(BoardVO bv) {
		return boardDao.getSearchBoard(bv);
	}

}
