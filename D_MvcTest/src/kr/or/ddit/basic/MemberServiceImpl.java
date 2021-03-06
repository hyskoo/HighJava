package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;

public class MemberServiceImpl implements IMemberService {

	private IMemberDao memDao;
	
	public MemberServiceImpl() {
		memDao = new MemberDaoImpl();
	}

	@Override
	public int insertMember(MemberVO mv) {
		return memDao.insertMember(mv);
	}

	@Override
	public boolean getMember(String memId) {
		return memDao.getMember(memId);
	}

	@Override
	public List<MemberVO> getAllMemberList() {
		return memDao.getAllMemberList();
	}

	@Override
	public int updateMember(MemberVO mv) {
		return memDao.updateMember(mv);
	}

	@Override
	public int deleteMember(String memId) {
		int cnt = memDao.deleteMember(memId);
		if (cnt > 0) {
			// 관리자에게 메일 발송하기..
		}
		return memDao.deleteMember(memId);
	}

	@Override
	public List<MemberVO> getSearchMember(MemberVO mv) {

		return memDao.getSearchMember(mv);
	}

}
