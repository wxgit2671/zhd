package com.wx.zhd.spbshiro.controller.sys;

import com.github.pagehelper.Page;
import com.wx.zhd.spbshiro.controller.BaseController;
import com.wx.zhd.spbshiro.entity.Manager;
import com.wx.zhd.spbshiro.entity.Menu;
import com.wx.zhd.spbshiro.service.sys.MenuService;
import com.wx.zhd.spbshiro.tp.Result;
import com.wx.zhd.spbshiro.utils.TreeTableUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sys/menu")
public class MenuController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(MenuController.class);
    @Autowired
    private MenuService menuService;

    /**
     * 展示menu信息
     * */
    @ResponseBody
    @RequestMapping("/leftjsp")
    public Result showMenu(Model model) {
        Manager currentManager = currentUser();
        Long roleId = currentManager.getRoleId();
        List<Map<String, Object>> menus = menuService.getCurrentMangerMenu(roleId);
        // List<Map<String, Object>> menus=menuService.getLeftMenu();
        TreeTableUtils treeTableUtils = new TreeTableUtils(menus, 1);
        menus = treeTableUtils.createLeftMenu(menus);
        model.addAttribute("menus", menus);
        return success("", menus);
    }

    /**
     * 查询所有menu信息
     * */
    @RequestMapping("/list")
    public String getMenuList(Model model) {
        List<Menu> menus = menuService.getAllMenu();
        TreeTableUtils treeTableUtils = new TreeTableUtils(menus);
        menus = treeTableUtils.buildTree();
        model.addAttribute("menuList", menus);
        return "sys/menu-list";// user/blacklist-list
    }

    /**
     * 根据ID 查询menu信息详情页
     * */
    @RequestMapping("/detail")
    public String getMenuById(Model model, long id) {
        Menu menu = menuService.getMenuById(id);
        model.addAttribute("menu", menu);
        return "sys/modal/menu-detail";// user/blacklist-list
    }

    /**
     * 新增菜单 添加页面
     * */
    @RequestMapping("/create")
    public String createMenu(Model model, long id) {
        logger.debug("return page：create");
        model.addAttribute("fatherId", id);
        return "sys/modal/menu-create";// user/blacklist-list
    }

    /**
     * 新增菜单submit
     * */
    @RequestMapping("/addMenuSubmit")
    public String addMenuSubmit(Page<Menu> page, Model model, Menu menu,
            RedirectAttributes redirectAttributes) {
        logger.debug("requestMapping:addMenuSubmit");
        String level;
        // 计算菜单级别
        if (menu.getFatherId() == 0) {// 一级菜单
            level = "1";
        } else {// 非一级菜单
                // 获取父级菜单级别
            String fatherLevel = menuService.getMenuById(menu.getFatherId()).getLevel();
            Integer temp = (Integer.parseInt(fatherLevel)) + 1;
            level = temp.toString();
        }
        logger.debug("curent menu level :" + level);
        menu.setLevel(level);

        menuService.addMenuSubmit(menu);
        addSuccessMessage(redirectAttributes, "添加菜单成功！");
        return "redirect:/sys/menu/list";// user/blacklist-list
    }

    /**
     * 新增菜单submit 弹窗化提交
     * */
    @ResponseBody
    @RequestMapping("/addMenuModalSubmit")
    public Result addMenuModalSubmit(Page<Menu> page, Model model, Menu menu,
            RedirectAttributes redirectAttributes) {
        logger.debug("requestMapping:addMenuModalSubmit");
        String level;
        // 计算菜单级别
        if (menu.getFatherId() == 0) {// 一级菜单
            level = "1";
        } else {// 非一级菜单
                // 获取父级菜单级别
            String fatherLevel = menuService.getMenuById(menu.getFatherId()).getLevel();
            Integer temp = (Integer.parseInt(fatherLevel)) + 1;
            level = temp.toString();
        }
        logger.debug("curent menu level :" + level);
        menu.setLevel(level);

        menuService.addMenuSubmit(menu);
        // addSuccessMessage(redirectAttributes, "添加菜单成功！");
        return success("", null);
        // return "redirect:/sys/menu/list";// user/blacklist-list
    }

    /**
     * 删除菜单（逻辑删除） deleteMenuPysical
     * */
    @RequestMapping("/deleteMenu")
    public String deleteMenu(String id, RedirectAttributes redirectAttributes) {

        menuService.deleteMenu(id);
        addSuccessMessage(redirectAttributes, "失效菜单成功！");
        return "redirect:/sys/menu/list";// user/blacklist-list
    }

    /**
     * 删除菜单（物理删除） deleteMenuPysical
     * */
    @RequestMapping("/deleteMenuPysical")
    public String deleteMenuPysical(String id, RedirectAttributes redirectAttributes) {

        menuService.deleteMenuPysical(id);
        addSuccessMessage(redirectAttributes, "删除菜单成功！");
        return "redirect:/sys/menu/list";// user/blacklist-list
    }

    /**
     * 修改录入页
     * */
    @RequestMapping("/updateMenuPre")
    public String updateMenuPre(Model model, long id) {
        Menu menu = menuService.getMenuById(id);
        model.addAttribute("menu", menu);
        return "sys/modal/menu-update";
    }

    /**
     * 修改菜单
     * */
    @ResponseBody
    @RequestMapping("/updateMenuSumbit")
    public Result updateMenuSumbit(Model model, Menu menu, RedirectAttributes redirectAttributes) {
        menuService.updateMenu(menu);
        // addSuccessMessage(redirectAttributes, "修改菜单成功！");
        return success("", null);
        // return "redirect:/sys/menu/list";
    }
}
