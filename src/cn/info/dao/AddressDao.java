package cn.info.dao;

import org.apache.ibatis.session.SqlSession;

import cn.info.model.Address;
import cn.info.utils.DBUtilMybatis;

public class AddressDao implements IAddressDao {

	@Override
	public void add(Address address) {
		SqlSession session=DBUtilMybatis.createSession();
		session.insert(address.getClass().getName()+".add", address);
		session.commit();
		DBUtilMybatis.closeSession(session);
	}
	
	/**输入：用户id
	 * 输出：某个用户的所有地址及其用户x
	 */
	@Override
	public Address load(Address address) {
		SqlSession session=DBUtilMybatis.createSession();
		session.insert(address.getClass().getName()+".load", address);
		return address;
		
	}

}
