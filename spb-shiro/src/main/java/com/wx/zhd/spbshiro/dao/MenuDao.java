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
     */
    Menu findById(Long id);

    /**
     * 根据条件查询所有菜单
     */
    List<Menu> findAll(Menu menu);

    /**
     * 根据角色id查询所有菜单
     */
    List<Menu> findByRoleId(Long roleId);

    /**
     * 根据menuId查询素有roleId
     */
    List<Long> findRoleByMenuId(@Param("menuId") Long menuId);

    /**
     * 保存一个菜单
     */
    void save(Menu menu);

    /**
     * 更新一个菜单
     */
    int update(Menu menu);

    /**
     * 保存角色权限
     */
    int savePermissions(Map<String, Object> permission);

    /**
     * 清除权限根据角色id
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
     */
    List<Long> getRoleIds(@Param("menuId") Long menuId);

}
