package cn.info.test;


import org.junit.Test;

import cn.info.dao.AddressDao;
import cn.info.dao.UserDaoMybatis;
import cn.info.model.Address;
import cn.info.model.Pager;
import cn.info.model.User;

public class Mybatis {

	@Test
	public void testAdd() {
		User user=new User();
		user.setId(1);
		Address u=new Address();
		u.setName("广州老王收");
		u.setPhone("1380008790");
		u.setPostcode("434301");
		u.setUser(user);
		AddressDao addressDao=new AddressDao();
		addressDao.add(u);
	}
	
	@Test
	public void testDelete(){
		User u=new User();
		u.setId(5);
		UserDaoMybatis daoMybatis=new UserDaoMybatis();
		daoMybatis.delete(u.getId());
	}
	
	@Test
	public void testLoad(){
		UserDaoMybatis daoMybatis=new UserDaoMybatis();
		User user=daoMybatis.load(1);
		System.out.println(user.getNickname());
	}
	
	
	@Test
	public void testUpdate(){
		User user=new User();
		user.setId(7);
		user.setUsername("user07");
		UserDaoMybatis daoMybatis=new UserDaoMybatis();
		daoMybatis.update(user);
	}
	
	@Test
	public void testPage(){
		int pageIndex=0;
		int pageSize=2;
		Pager<User> pg=new Pager<User>();
		UserDaoMybatis daoMybatis=new UserDaoMybatis();
		pg=daoMybatis.pageList(pageIndex, pageSize);
		for(User u:pg.getDates()){
			System.out.println(u.getNickname());
		}
		
	}
	
}
