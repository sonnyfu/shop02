package cn.info.dao;

import cn.info.model.Address;

public interface IAddressDao {
	public void add(Address address);
	public Address load(Address address); 
}
