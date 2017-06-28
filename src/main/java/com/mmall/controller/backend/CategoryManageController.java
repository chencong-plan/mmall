package com.mmall.controller.backend;

import com.mmall.common.Const;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.User;
import com.mmall.service.ICategoryService;
import com.mmall.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

import static com.mmall.common.Const.CURRENT_USER;

/**
 * 项目：  mmall
 * 包名：  com.mmall.controller.backend
 * 作者：  chencong
 * 时间：  2017/6/28 10:20.
 * 描述：  后台管理
 * 部门分类
 */
@Controller
@RequestMapping("/manage/category")
public class CategoryManageController {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private ICategoryService iCategoryService;

    /**
     * 管理员增加分类信息
     *
     * @param session      管理员登录session
     * @param categoryName 分类名称
     * @param parentId     父节点id
     * @return 返回服务器响应
     */
    @RequestMapping("add_category.do")
    @ResponseBody
    public ServerResponse addCategory(HttpSession session, String categoryName, @RequestParam(value = "parentId", defaultValue = "0") int parentId) {
        User user = (User) session.getAttribute(CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录");
        }
        //校验一下user是否是管理员
        if (iUserService.checkAdminRole(user).isSuccess()) {
            //是管理员 增加分类信息 否则返回拒绝操作信息
            //增加处理分类的逻辑
            return iCategoryService.addCategory(categoryName, parentId);
        } else {
            return ServerResponse.createByErrorMessage("无权操作，需要管理员权限");
        }
    }


    /**
     * 更新类别名称category的Name
     *
     * @param session      管理员登录session
     * @param categoryId   类别ID
     * @param categoryName 类别名称
     * @return 返回服务器响应
     */
    @RequestMapping("set_category_name.do")
    @ResponseBody
    public ServerResponse setCategoryName(HttpSession session, Integer categoryId, String categoryName) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请先登录");
        }
        //检查登录的用户是否是管理员
        if (iUserService.checkAdminRole(user).isSuccess()) {
            //是管理员 跟新categoryName
            return iCategoryService.updateCategoryName(categoryId, categoryName);

        } else {
            return ServerResponse.createByErrorMessage("无权操作，需要管理员权限");
        }
    }

    /**
     * 查询categoryId的子节点的category信息，并且保持平级不递归
     *
     * @param session    管理员登录session
     * @param categoryId 待查询的categoryId
     * @return 返回服务器响应
     */
    @RequestMapping("get_category.do")
    @ResponseBody
    public ServerResponse getChildrenParallelCategory(HttpSession session, @RequestParam(value = "categoryId", defaultValue = "0") Integer categoryId) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请先登录");
        }
        //检查登录的用户是否是管理员
        if (iUserService.checkAdminRole(user).isSuccess()) {
            //是管理员 查询子节点的category信息，并且保持平级不递归
            return iCategoryService.getChildrenParallelCategory(categoryId);
        } else {
            return ServerResponse.createByErrorMessage("无权操作，需要管理员权限");
        }
    }

    /**
     * 获取当前categoryId节点 并且递归查询子节点的id
     *
     * @param session    管理员登录session
     * @param categoryId 待查询categoryId
     * @return 返回服务器响应
     */
    @RequestMapping("get_deep_category.do")
    @ResponseBody
    public ServerResponse getCategoryAndDeepChildrenCategory(HttpSession session, @RequestParam(value = "categoryId", defaultValue = "0") Integer categoryId) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请先登录");
        }
        //检查登录的用户是否是管理员
        if (iUserService.checkAdminRole(user).isSuccess()) {
            //查询当前节点的ID和递归查询子节点ID
            return iCategoryService.selectCategoryAndChildrenById(categoryId);

        } else {
            return ServerResponse.createByErrorMessage("无权操作，需要管理员权限");
        }
    }
}
