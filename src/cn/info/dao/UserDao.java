package cn.info.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.info.model.Pager;
import cn.info.model.ShowException;
import cn.info.model.SystemContext;
import cn.info.model.User;
import cn.info.utils.DBUtil;
@SuppressWarnings("resource")
public class UserDao implements IUserDao{

	
	@Override
	public void add(User user) {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select count(*) from t_user where username=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			rs=ps.executeQuery();
			while(rs.next())
			{
				if(rs.getInt(1)>0) throw new ShowException("添加用户已存在，不能继续添加！");
			}
			sql="insert into t_user values (null,?,?,?,?,?)";
			ps=conn.prepareStatement(sql);
			ps.setString(1,user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getNickname());
			ps.setInt(4, user.getStatus());
			ps.setInt(5, user.getType());
			ps.executeUpdate();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			DBUtil.close(conn);
			DBUtil.close(ps);
			DBUtil.close(rs);
		}
	}

	@Override
	public void delete(int id) {
		Connection conn=null;
		PreparedStatement ps=null;
		try {
			User user=this.load(id);
			if(user.getUsername()=="admin")
				throw new ShowException("管理员不能删除！");
			conn=DBUtil.getConnection();
			String sql="delete from t_user where id=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, id);//convert to sql int value
			ps.executeUpdate();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			DBUtil.close(conn);
			DBUtil.close(ps);
		}		
	}

	@Override
	public void update(User user) {
		Connection conn=null;
		PreparedStatement ps=null;
		try {
			conn=DBUtil.getConnection();
			String sql="update t_user set username=?,nickname=?,status=?,type=? where id=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getNickname());
			ps.setInt(3, user.getStatus());
			ps.setInt(4, user.getType());
			ps.setInt(5, user.getId());
			ps.executeUpdate();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			DBUtil.close(conn);
			DBUtil.close(ps);
		}
	}
	//根据id获取用户信息，返回单个数据，便于使用；避免转换，提高效率
	@Override
	public User load(int id) {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		User u=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from t_user where id=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs=ps.executeQuery();
			while(rs.next())
			{
				u=new User();
				u.setId(rs.getInt("id"));
				u.setNickname(rs.getString("nickname"));
				u.setPassword(rs.getString("password"));
				u.setUsername(rs.getString("username"));
				u.setStatus(rs.getInt("status"));
				u.setType(rs.getInt("type"));
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			DBUtil.close(conn);
			DBUtil.close(ps);
			DBUtil.close(rs);
		}
		return u;
	}
	
	//用户列表,返回集合类型,全部信息（包括普通，管理员）
	@Override
	public Pager<User> list(String condition) {
		int pageOffset=SystemContext.getPageOffset();
		int pageSize=SystemContext.getPageSize();
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Pager<User> page=new Pager<User>();
		List<User> users=new ArrayList<>();//查询方便
		User u=null;
		try {
			/*用户传送的数据，引用pager框架后不需index
			if(pageIndex<=0) pageIndex=1;
			int start=(pageIndex-1)*pageSize;//limit的起点计算（注意从sql中0页码开始）
*/			conn=DBUtil.getConnection();
			String sql="select * from t_user";
			String sqlCount="select count(*) from t_user";
			if(condition!=null||!"".equals(condition)){
				sql+=" where username like '%"+condition+"%' or nickname like '%"+condition+"%'";
			}
			sql+="limit ?,?";//分页的sql语句，限定记录范围
			ps=conn.prepareStatement(sql);
			ps.setInt(1, pageOffset);
			ps.setInt(2, pageSize);
			rs=ps.executeQuery();
			//得到数据对应的对象
			while(rs.next())
			{
				u=new User();
				u.setId(rs.getInt("id"));
				u.setNickname(rs.getString("nickname"));
				u.setPassword(rs.getString("password"));
				u.setUsername(rs.getString("username"));
				u.setStatus(rs.getInt("status"));
				u.setType(rs.getInt("type"));
				users.add(u);
			}
			ps=conn.prepareStatement(sqlCount);
			rs=ps.executeQuery();
			int totalRecord=0;
			while(rs.next()){
				totalRecord=rs.getInt(1);//总页数
			}
			int totalPage=(totalRecord-1)/pageSize+1;
			page.setPageOffset(pageOffset);//在pager类中增加字段pageoffset又传递给list页面
			page.setPageSize(pageSize);
			page.setTotalPage(totalPage);
			page.setTotalRecord(totalRecord);
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			DBUtil.close(conn);
			DBUtil.close(ps);
			DBUtil.close(rs);
		}
		page.setDates(users);
		return page;
	}

	@Override//
	public User login(String username, String password) {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		User u=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from t_user where username=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1,username);//本身字符串，若添加双引号，则报错；
			rs=ps.executeQuery();
			while(rs.next())
			{
				u=new User();
				u.setId(rs.getInt("id"));
				u.setNickname(rs.getString("nickname"));
				u.setPassword(rs.getString("password"));
				u.setUsername(rs.getString("username"));
				u.setStatus(rs.getInt("status"));
				u.setType(rs.getInt("type"));
			}
			if(u==null) throw new ShowException("用户名不存在！");
			if(!u.getPassword().equals(password)) throw new ShowException("密码错误！");
			if(u.getStatus()==0) throw new ShowException("用户处于停用状态");
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			DBUtil.close(conn);
			DBUtil.close(ps);
			DBUtil.close(rs);
		}
		return u;
	}

	
}
