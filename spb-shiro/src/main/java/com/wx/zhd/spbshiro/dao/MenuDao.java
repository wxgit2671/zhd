package com.wx.zhd.spbshiro.dao;

import com.wx.zhd.spbshiro.entity.Menu;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface MenuDao {
	/**
	 * 根据id获取一个菜单
	 * @param id
	 * @return
	 * @date: 2016年2月23日 上午11:02:18 
	 */
	Menu findById(Long id);
	/**
	 * 根据条件查询所有菜单
	 * @param menu
	 * @return
	 * @date: 2016年2月23日 上午11:02:28 
	 */
	List<Menu> findAll(Menu menu);
	/**
	 * 根据角色id查询所有菜单
	 * @param roleId
	 * @return
	 * @date: 2016年2月23日 上午11:02:41 
	 */
	List<Menu> findByRoleId(Long roleId);
	/**
	 * 根据menuId查询素有roleId
	 * @param menuId
	 * @return
	 * @date: 2016年3月2日 下午1:46:23
	 */
	List<Long> findRoleByMenuId(@Param("menuId") Long menuId);
	/**
	 * 保存一个菜单
	 * @param menu
	 * @return
	 * @date: 2016年2月23日 上午11:03:03
	 */
	void save(Menu menu);
	/**
	 * 更新一个菜单
	 * @param menu
	 * @return
	 * @date: 2016年2月23日 上午11:03:09
	 */
	int update(Menu menu);

	/**
	 * 保存角色权限
	 * @param permission
	 * @return
	 * @date: 2016年2月23日 上午11:03:20
	 */
	int savePermissions(Map<String, Object> permission);
	/**
	 * 清除权限根据角色id
	 * @param roleId
	 * @return
	 * @date: 2016年2月23日 上午11:03:27
	 */
	int clearPermissionsByRoleId(Long roleId);



	//  菜单管理 interface  # 增删改查(全查，id查)
	Long addMenuSubmit(Menu menu);
	void newMenuAddToPermission(Long menuId);
	void deleteMenuById(String id);
	void updateMenu(Menu menu);
	List<Menu> selectMenuInfo();
    Menu getMenuById(Long id);
    void deleteMenuPysicalById(String id);
    void deleteMenucascadePermission(String id);
    //design  for left jsp
    List<Map<String, Object>> getLeftMenu();
    List<Map<String, Object>> getCurrentLeftMenu(Long roleId);

	/**
	 * @Description:拥有该权限的角色
	 *
	 * @date 2017/6/7 20:36
	 */
	List<Long> getRoleIds(@Param("menuId") Long menuId);
    

}
