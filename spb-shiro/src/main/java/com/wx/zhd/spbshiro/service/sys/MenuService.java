package com.wx.zhd.spbshiro.service.sys;

import com.google.common.collect.Lists;

import com.wx.zhd.spbshiro.config.database.TargetDataSource;
import com.wx.zhd.spbshiro.dao.MenuDao;
import com.wx.zhd.spbshiro.entity.Manager;
import com.wx.zhd.spbshiro.entity.Menu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName:MenuService
 * @date: 2016年2月23日 下午2:53:24
 */
@Service
public class MenuService {
    private static final Logger logger = LoggerFactory.getLogger(MenuService.class);
    /**
     * @Dscrription:菜单数据层操作类
     * @date: 2016年2月23日 上午11:11:13
     */
    @Autowired
    private MenuDao menuDao;

    public List<Menu> findAll(Menu menu) {
        return menuDao.findAll(menu);
    }

    /**
     * 查询菜单返回map
     *
     * @param roleId 角色id，为空即查所有菜单，否则查角色拥有菜单
     * @date: 2016年2月23日 上午11:22:45
     */
    public Map<Long, Menu> findAllMenuAsMap(Long roleId) {
        Map<Long, Menu> result = new HashMap<>();
        List<Menu> menus = Lists.newArrayList();
        if (null == roleId) {
            menus = menuDao.findAll(new Menu());
        } else {
            menus = menuDao.findByRoleId(roleId);
        }
        menus.forEach((menu) -> {
            result.put(menu.getId(), menu);
        });
        return result;
    }

    /**
     * 返回json格式的菜单列表
     *
     * @date: 2016年2月23日 下午2:52:41
     */
    public List<Menu> findMenusAsJson(Long roleId, Manager currentUser) {
        List<Menu> menus = Lists.newArrayList();
        List<Menu> all = menuDao.findByRoleId(currentUser.getRole().getId());
        Map<Long, Menu> hasMenu = findAllMenuAsMap(roleId);
        all.forEach((menu) -> {
            if (menu.getFatherId().equals(0l)) {
                addSonList(all, menu, hasMenu);
                menu.setChecked(hasMenu.get(menu.getId()) != null);
                menus.add(menu);
            }
        });
        return menus;
    }

    /**
     * 为菜单添加子菜单列表
     *
     * @date: 2016年2月23日 下午2:52:57
     */
    private void addSonList(List<Menu> menus, Menu menu, Map<Long, Menu> hasMenus) {
        menu.setChecked(hasMenus.get(menu.getId()) != null);
        List<Menu> sons = findSonFormListByFatherId(menus, menu.getId());
        menu.setChildren(sons);
        if (sons != null) {
            sons.forEach((m) -> {
                addSonList(menus, m, hasMenus);
            });
        }
    }

    /**
     * 查询子菜单
     *
     * @date: 2016年2月23日 下午2:53:26
     */
    private List<Menu> findSonFormListByFatherId(List<Menu> list, Long menuId) {
        List<Menu> sonList = Lists.newArrayList();
        list.forEach((menu) -> {
            if (menu.getFatherId().equals(menuId)) {
                sonList.add(menu);
            }
        });
        return sonList.isEmpty() ? null : sonList;
    }

    /**
     * 保存角色权限
     *
     * @date: 2016年2月23日 上午11:27:34
     */
    @Transactional
    public boolean savePermissions(Long roleId, Long[] menuIds) {
        boolean flag = false;
        try {
            menuDao.clearPermissionsByRoleId(roleId);
            Map<String, Object> permission = new HashMap<>();
            permission.put("roleId", roleId);
            for (Long menuId : menuIds) {
                permission.put("menuId", menuId);
                menuDao.savePermissions(permission);
            }
            flag = true;
        } catch (Exception e) {
            logger.error("保存角色权限失败roleId--" + roleId, e);
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
        return flag;
    }

    /**
     * 返回所有roleId 通过菜单id
     *
     * @date: 2016年3月2日 下午1:48:35
     */
    public Long[] findRoleIdsByMenuId(Long menuId) {
        List<Long> roleIdList = menuDao.findRoleByMenuId(menuId);
        Long[] roleIds = new Long[roleIdList.size()];
        roleIdList.toArray(roleIds);
        return roleIds;
    }

    /**
     * 新增menu菜单
     *
     * @date: 2016年3月2日 下午1:48:35
     */
    public void saveMenu(Menu menu) {

        menuDao.save(menu);
    }


    @Transactional
    public void addMenuSubmit(Menu menu) {
        // TODO Auto-generated method stub
        Long insertId = menuDao.addMenuSubmit(menu);
        menuDao.newMenuAddToPermission(menu.getId());
    }

    /**
     * 删除menu菜单(逻辑删除)
     *
     * @date: 2016年3月2日 下午1:48:35
     */
    public void deleteMenu(String id) {

        menuDao.deleteMenuById(id);
    }

    /**
     * 删除菜单(物理删除)
     *
     * @date: 2016年3月2日 下午1:48:35
     */
    @Transactional
    public void deleteMenuPysical(String id) {
        // TODO Auto-generated method stub
        menuDao.deleteMenuPysicalById(id);
        menuDao.deleteMenucascadePermission(id);
    }

    /**
     * 修改menu菜单
     *
     * @date: 2016年3月2日 下午1:48:35
     */
    public void updateMenu(Menu menu) {
        menuDao.updateMenu(menu);
    }


    /**
     * 查询所有菜单
     */
    @TargetDataSource(name = "slave")
    public List<Menu> getAllMenu() {
        // TODO Auto-generated method stub
        return menuDao.selectMenuInfo();

    }

    /**
     * 根据id查询菜单信息
     */
    @TargetDataSource(name = "slave")
    public Menu getMenuById(Long id) {
        return menuDao.getMenuById(id);
    }

    /**
     * design for left jsp
     */
    @TargetDataSource(name = "slave")
    public List<Map<String, Object>> getLeftMenu() {
        // TODO Auto-generated method stub

        return menuDao.getLeftMenu();
    }

    /**
     * get the current manager`s menus
     */
    @TargetDataSource(name = "slave")
    public List<Map<String, Object>> getCurrentMangerMenu(Long roleId) {
        return menuDao.getCurrentLeftMenu(roleId);
    }

}
