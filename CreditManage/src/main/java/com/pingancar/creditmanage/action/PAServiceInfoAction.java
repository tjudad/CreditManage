package com.pingancar.creditmanage.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.pingancar.creditmanage.pojo.PAServiceInfoPojo;
import com.pingancar.creditmanage.pojo.PAServicePojo;
import com.pingancar.creditmanage.service.PAServiceInfoService;

import java.util.List;

/**
 * Created by kx on 2014/9/12.
 */
public class PAServiceInfoAction extends ActionSupport{
    private PAServiceInfoService paServiceInfoService;
    private PAServiceInfoPojo paServiceInfoPojo;
    private String paserviceinfoid;

    public String queryPAServiceInfo(){
        List<PAServiceInfoPojo> serviceInfo= paServiceInfoService.queryPAServiceInfo(paserviceinfoid);

        if(serviceInfo.size()!=0){
            ActionContext.getContext().getSession().put("serviceinfo", serviceInfo.get(0));
            ActionContext.getContext().getSession().put("paserviceinfoid",paserviceinfoid);
            return SUCCESS;
        }else {
            return ERROR;
        }
    }

    public String getPaserviceinfoid() {
        return paserviceinfoid;
    }

    public void setPaserviceinfoid(String paserviceinfoid) {
        this.paserviceinfoid = paserviceinfoid;
    }

    public PAServiceInfoPojo getPaServiceInfoPojo() {

        return paServiceInfoPojo;
    }

    public void setPaServiceInfoPojo(PAServiceInfoPojo paServiceInfoPojo) {
        this.paServiceInfoPojo = paServiceInfoPojo;
    }

    public String addPAServiceInfo(){
        boolean result= paServiceInfoService.addPAServiceInfo(paServiceInfoPojo);
        if (result){
            return SUCCESS;
        }else {

            addActionError("添加服务信息失败");
            return ERROR;
        }
    }
    public String updatePAServiceInfo(){
        boolean result = paServiceInfoService.updatePAServiceInfo(paServiceInfoPojo);
        if(result){
            return SUCCESS;
        } else{
            addActionError("更新服务信息失败");
            return ERROR;
        }
    }
    public String deletePAServiceInfo(){
        boolean result = paServiceInfoService.deletePAServiceInfo(paServiceInfoPojo);
        if(result){
            return SUCCESS;
        }else {
            addActionError("删除服务信息失败");
            return ERROR;
        }
    }

    public PAServiceInfoService getPaServiceInfoService() {
        return paServiceInfoService;
    }

    public void setPaServiceInfoService(PAServiceInfoService paServiceInfoService) {
        this.paServiceInfoService = paServiceInfoService;
    }
}
