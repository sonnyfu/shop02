package cn.info.dao;
/**
 * 简单工厂（各种dao出现时：用统一的factory来统一调用，方便维护）
 * @author Administrator
 *
 */
public class DAOFactory {
	//类dao-类的接口（当做返回类型）-
	public static IUserDao getUserDao() {
		return new UserDao();
	}
	
}
