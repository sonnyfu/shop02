package cn.info.dao;

import cn.info.model.Pager;
import cn.info.model.User;

public interface IUserDao {
	public void add(User user) ;
	public void delete(int id);
	public void update(User user);
	public User load(int id);//
	public Pager<User> list(String con);//列表
	public User login(String username,String password);
}
