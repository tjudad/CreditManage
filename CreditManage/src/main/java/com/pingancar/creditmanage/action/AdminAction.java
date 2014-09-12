package com.pingancar.creditmanage.action;

import com.opensymphony.xwork2.ActionSupport;
import com.pingancar.creditmanage.pojo.AdminPojo;
import com.pingancar.creditmanage.service.AdminService;

/**
 * Created by Administrator on 2014/9/9 0009.
 */
public class AdminAction extends ActionSupport {
    private AdminService adminService;
    private AdminPojo adminPojo;

    public String login(){
        boolean result = adminService.login(adminPojo);


        addActionError("错误代码1");
        if(result){
            return SUCCESS;
        } else {
            return ERROR;
        }
    }

    public AdminService getAdminService() {
        return adminService;
    }

    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }
}