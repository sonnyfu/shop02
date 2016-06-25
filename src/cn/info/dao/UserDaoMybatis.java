package cn.info.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import cn.info.model.Pager;
import cn.info.model.User;
import cn.info.utils.DBUtilMybatis;

public class UserDaoMybatis {
	
	public void add(User user){
		
		SqlSession sqlSession=null;
		try {
			sqlSession = DBUtilMybatis.createSession();
			sqlSession.insert(user.getNickname().getClass().getName() + ".add", user);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
		}
		finally{
			DBUtilMybatis.closeSession(sqlSession);
		}
		
	}
	
	public void delete(int id){
		User user=new User();
		SqlSession Session=DBUtilMybatis.createSession();
		Session.delete(user.getClass().getName()+".delete",id);
		Session.commit();
		DBUtilMybatis.closeSession(Session);
	}
	
	public User load(int id){
		User user=new User();
		SqlSession session=DBUtilMybatis.createSession();
		user=session.selectOne(user.getClass().getName()+".load", id);
		DBUtilMybatis.closeSession(session);
		return user;
	}
	
	public void update(User user){
		SqlSession session=DBUtilMybatis.createSession();
		session.update(user.getClass().getName()+".update",user);
		session.commit();
		DBUtilMybatis.closeSession(session);
	}
	

	
	/**输入：pageIndex，pageSize
	 * 处理：查询
	 * 输出:用户列表
	 * @return
	 */
	public Pager<User> pageList(int pageIndex,int pageSize){
		Pager<User> page=new Pager<User>();
		page.setPageIndex(pageIndex);
		page.setPageSize(pageSize);
		User user=new User();
		List<User> users=new ArrayList<User>();
		SqlSession session=DBUtilMybatis.createSession();
		users=session.selectList(user.getClass().getName()+".list", page);
		page.setDates(users);
		return page;
	}
	
}
