package com.wx.zhd.spbshiro.utils;

import com.wx.zhd.spbshiro.entity.Menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 根据实体类生成属性表格菜单 实体类有两个必须属性（set get方法） 会自动按照（id/pid）顺序递归对list集合内容排序 String
 * id;//唯一id String fatherId;//父id
 */
public class TreeTableUtils {
    private List<Menu> resultNodes = new ArrayList<Menu>();//树形结构排序之后list内容
    private List<Menu> nodes; //传入list参数
    private List<Map<String, Object>> menusAlList;
    public TreeTableUtils(List<Menu> nodes) {//通过构造函数初始化
        this.nodes = nodes;
    }
    
    public TreeTableUtils(List<Map<String, Object>> menusMaps,Integer type) {
        menusAlList=menusMaps;
    }
//**************************************
//    /**
//     * 构建树形结构list         yuanshi
//     * @return 返回树形结构List列表
//     */
//    public List<Menu> buildTree() {
//        for (Menu node : nodes) {
//           // Long id = node.getId();
//            if (node.getFatherId() == 0) {//通过循环一级节点 就可以通过递归获取二级以下节点
//                resultNodes.add(node);//添加一级节点
//                build(node);//递归获取二级、三级、。。。节点
//            }
//        }
//        return resultNodes;
//    }
//***************************************   
    
    /**
     * 递归循环子节点
     *
     * @param node 当前节点
     */
//    private void build(Menu node) {
//        List<Menu> children = getChildren(node);
//        if (!children.isEmpty()) {//如果存在子节点
//            for (Menu child : children) {//将子节点遍历加入返回值中
//                resultNodes.add(child);
//                build(child);
//            }
//        }
//    }
//**************************************    
    /**
     * 构建树形结构list  
     * @return 返回树形结构List列表
     */
    public List<Menu> buildTree() {
        //最终组成一个数组，按照所以菜单展开后的顺序进行排序
        
        //获取1级排序后的菜单
        List<Menu> firstLevelMenus=getSortFirstLevel();
        for (Menu menu : firstLevelMenus) {
            resultNodes.add(menu);
          //获取当前菜单及其子菜单
            build(menu);
//            resultNodes.addAll(childrenMenus);
        }
        return resultNodes;
    }
    
    
    /**
     * 
    * @Title: getSortFirstLevel
    * @Description: TODO(获取排序后的以及菜单)
    * @param @return    设定文件
    * @return ArrayList<Menu>    返回类型
    * @throws
     */
    private ArrayList<Menu> getSortFirstLevel() {
        // TODO Auto-generated method stub
        ArrayList<Menu> result = new ArrayList<Menu>();
        for (Menu  menu : nodes) {
            if (menu.getFatherId()==0) {
                Integer currentSort=menu.getSort();
                if (currentSort==null) {
                    result.add(menu);
                    continue;
                }
                if (result.size()<1) {
                    result.add(menu);
                    continue;
                }else {
                    for (int i = 0; i < result.size(); i++) {
                        Integer listSort=result.get(i).getSort();
                        if (listSort==null) {
                            result.add(i,menu);
                            break;
                        }
                        if (currentSort<=listSort) {
                            result.add(i,menu);
                            break;
                        }else if (i==result.size()-1) {
                            result.add(i+1,menu);
                            break;
                        }
                    }
                }
                
            }
        }
        return result;
    }
    
    
    
//    /**
//     * 递归循环子节点
//     *
//     * @param node 当前节点
//     */
//    private List<Menu> build(Menu node) {
//        List<Menu> children = getChildren(node);
//        List<Menu> sortChildren=new ArrayList<Menu>();
//        if (!children.isEmpty()) {//如果存在子节点
////            List<Menu> childrenList = new ArrayList<Menu>();
//            for (Menu child : children) {
//                //将子节点遍历加入返回值中
////                Integer currentSort=  node.getSort();
//                Integer currentSort=child.getSort();
//                if (currentSort==null) {
//                    sortChildren.add(child);
////                    resultNodes.add(child);
////                    break;
//                      continue;
//                }if (sortChildren.size()<1) {
//                      sortChildren.add(child);
////                    resultNodes.add(node);
//                }else {
//                    for (int i=0;i<sortChildren.size();i++) {
//                        Integer mapSort =sortChildren.get(i).getSort();
//                        
//                        if (mapSort==null) {
//                            sortChildren.add(i,node);
////                            resultNodes.add(i,node);
//                            break;
//                        }
//                        
//                        if (currentSort<=mapSort) {
//                            resultNodes.add(i, node);
//                            break;
////                              continue;
//                        }else if (i==sortChildren.size()-1) {
//                            sortChildren.add(i+1, node);
//                            break;
//                        }
//                    }
//                }
////                resultNodes.add(child);
//                 List<Menu> childrenList=build(child);
//                 sortChildren.addAll(childrenList);
//                 
//            }
////            node.setChildren(childrenList);
//        }
//        return sortChildren;
//    }
    /**
     * 递归循环子节点    带排序
     *
     * @param node 当前节点
     */
    private void build(Menu node) {
        List<Menu> children = getChildren(node);
        List<Menu> sortMenu=null;
        if (!children.isEmpty()) {//如果存在子节点
            sortMenu=sortCurentMenus(children);
//            List<Menu> childrenList = new ArrayList<Menu>();
            for (Menu child : sortMenu) {
                //将子节点遍历加入返回值中
//                Integer currentSort=  node.getSort();
                resultNodes.add(child);
                build(child);
            }
        }
    }
    /**
     * @return 
     * 
    * @Title: sortCurentMenus
    * @Description: TODO(对当前菜单排序)
    * @return void    返回类型
    * @throws
     */
    private List<Menu> sortCurentMenus(List<Menu> children){
        List<Menu> resultlMenus= new ArrayList<Menu>();
        for (Menu menu : children) {
            Integer sort=menu.getSort();
            if (sort==null) {
                resultlMenus.add(menu);
                continue;
            }
            if (resultlMenus.size()<1) {
                resultlMenus.add(menu);
                continue;
            }else  {
                for (int i = 0; i < resultlMenus.size(); i++) {
                     Integer listSort=resultlMenus.get(i).getSort();
                     if (listSort==null) {
                        resultlMenus.add(i,menu);
                        break;
                    }
                    if (sort<=resultlMenus.get(i).getSort()) {
                        resultlMenus.add(i,menu);
                        break;
                    }else if (i==resultlMenus.size()-1) {
                        resultlMenus.add(i+1,menu);
                        break;
                    }
                }
            }
            
        }
        return resultlMenus;
    }
    
    /**
     * 递归循环子节点  原始
     *
     * @param node 当前节点
     */
    private void buildright(Menu node) {
        List<Menu> children = getChildren(node);
        if (!children.isEmpty()) {//如果存在子节点
            for (Menu child : children) {//将子节点遍历加入返回值中
                resultNodes.add(child);
                buildright(child);
            }
        }
    }
    
    
    /**
     * @param node
     * @return 返回
     */
    private List<Menu> getChildren(Menu node) {
        List<Menu> children = new ArrayList<Menu>();
        long id = node.getId();
        for (Menu child : nodes) {
            if (id==child.getFatherId()) {//如果id等于父id
                children.add(child);//将该节点加入循环列表中
            }
        }
        return children;
    }
    /**
     * 
    * @Title: getChildren
    * @Description: TODO(获取 子目录)
    * @param @param node
    * @param @return    设定文件
    * @return List<Map<String,Object>>    返回类型
    * @throws
     */
    private List<Map<String, Object>> getChildren(Map<String, Object> node) {
        List<Map<String, Object>> children = new ArrayList<Map<String,Object>>();
        
        long id = Long.valueOf(node.get("id")+"");
        for (Map<String, Object> child : menusAlList) {
            if (id==Long.valueOf(child.get("fatherId")+"")) {//如果id等于父id
                 Object currentSortObj=child.get("sort");
                 if (currentSortObj==null) {
                     children.add(child);
//                     break;
                       continue;
                 }
                int currentSort=(int)currentSortObj;
                if (children.size()<1) {
                    children.add(child);
                }else {
                    for (int i = 0; i < children.size(); i++) {
                        Integer  mapSort=(Integer)children.get(i).get("sort");
                        if (mapSort==null) {
                            children.add(i,child);
                            break;
                        }
                        if (currentSort<=mapSort) {
                            children.add(i, child);
                            break;
//                            continue;
                        }else if (i==children.size()-1) {
                            children.add(i+1, child);
                            break;
                        }
                    }
                }
//                children.add(child);//将该节点加入循环列表中
            }
        }
        return children;
    }
    /**
     * 返回当前菜单及下级菜单
     * */
    private Menu addChildrenMenuToFather(Menu menu) {
        List<Menu> children=getChildren(menu);
        if (children!=null) {
            menu.setChildren(children);
            for (Menu iteMenu : children) {
                addChildrenMenuToFather(iteMenu);
            }
        }
       
        return menu;
    }
    /**
     * 返回当前菜单及下级菜单
     * */
    private void addChildrenMenuToFather(Map<String, Object> menu) {
        List<Map<String,Object>> children=getChildren(menu);
        if (children!=null) {
            menu.put("menus", children);
            for (Map<String, Object> iteMenu : children) {
                addChildrenMenuToFather(iteMenu);
            }
        }
       
        // menu;
    }
    /**
     * 返回一级菜单list树结构
     * */
    public List<Menu> getFirstLevelTree() {
        List<Menu> result= new ArrayList<Menu>();
        for (Menu menu : nodes) {
            if (menu.getFatherId()==0) {
                addChildrenMenuToFather(menu);
                result.add(menu);
            }
        }
        return result;
    }
    /**
     * 返回左侧菜单树结构
     * */
    public List<Map<String, Object>> createLeftMenu(List<Map<String, Object>> menus) {
        // TODO Auto-generated method stub
        List<Map<String, Object>> result= new ArrayList<Map<String,Object>>();
        for (Map<String, Object> menu : menus) {
           // String aString=menu.get("fatherId");
            if ((Integer)menu.get("fatherId")==0) {
                
              //递归获取下级菜单    
                addChildrenMenuToFather(menu);
//                int a=null;
              //思路： 当前的menu 均为1级菜单，  那么需要调用 list.add （角标，值） 插入
                Object currentSortObj=  menu.get("sort");
                if (currentSortObj==null) {
                    result.add(menu);
//                    break;
                      continue;
                }
                 int currentSort=(int)currentSortObj;
                if (result.size()<1) {
                    result.add(menu);
                }else {
                    for (int i=0;i<result.size();i++) {
                        int mapSort =(int)result.get(i).get("sort");
                        if (currentSort<=mapSort) {
                            result.add(i, menu);
                            break;
//                              continue;
                        }else if (i==result.size()-1) {
                            result.add(i+1, menu);
                            break;
                        }
                    }
                }
//                result.add(menu);
            }
        }
        return result;
    }
}
