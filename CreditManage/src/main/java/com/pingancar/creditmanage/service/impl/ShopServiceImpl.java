package com.pingancar.creditmanage.service.impl;

import com.pingancar.creditmanage.dao.ShopInfoDao;
import com.pingancar.creditmanage.dao.ShopUserDao;
import com.pingancar.creditmanage.pojo.PAServicePojo;
import com.pingancar.creditmanage.pojo.ShopInfoPojo;
import com.pingancar.creditmanage.pojo.ShopUserPojo;
import com.pingancar.creditmanage.service.ShopService;
import com.pingancar.creditmanage.util.myenum.ShopInfoField;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2014/9/6 0006.
 */
public class ShopServiceImpl implements ShopService {

    ShopUserDao shopUserDao;
    ShopInfoDao shopInfoDao;

    @Override
    public List<String> getAllTags(String paserviceinfoid) {
        List<String> result = shopInfoDao.getAllTags(paserviceinfoid);
        if(result == null){
            result = new ArrayList<String>();
        }
        return result;
    }

    @Override
    public boolean login(ShopUserPojo shopUserPojo) {
        if(!checkUser(shopUserPojo)){
            return false;
        }
        List<ShopUserPojo> result=shopUserDao.findByUsername(shopUserPojo.getUsername());
        if(result == null){
            result = new ArrayList<ShopUserPojo>();
        }
        if(result.size()>0){
            if(result.get(0).getPasswd().equals(shopUserPojo.getPasswd())){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean addShopInfo(ShopInfoPojo shopInfoPojo) {
        shopInfoDao.save(shopInfoPojo);
        return true;
    }

    @Override
    public boolean updateShopInfo(ShopInfoPojo shopInfoPojo) {
       //返回值呀返回值
        List <ShopInfoPojo> result= shopInfoDao.findByShopid(shopInfoPojo.getShopid());
        if(result == null){
            result = new ArrayList<ShopInfoPojo>();
        }
        if(result.size()<1){
            return false;
        }
        ShopInfoPojo re=result.get(0);
        if(!shopInfoPojo.getName().equals("")){
           re.setName(shopInfoPojo.getName());
        }
        if(!shopInfoPojo.getLocation().equals("")){
            re.setLocation(shopInfoPojo.getLocation());
        }
        if(!shopInfoPojo.getXpos().equals("")){
            re.setXpos(shopInfoPojo.getXpos());
        }
        if(!shopInfoPojo.getYpos().equals("")){
            re.setYpos(shopInfoPojo.getYpos());
        }
        if(!shopInfoPojo.getInterf().equals("")){
            re.setInterf(shopInfoPojo.getInterf());
        }
        if(!shopInfoPojo.getTag2().equals("")){
            re.setTag2(shopInfoPojo.getTag2());
        }
        if(!shopInfoPojo.getTag1().equals("")){
            re.setTag1(shopInfoPojo.getTag1());
        }
        if(!shopInfoPojo.getCon().equals("")){
            re.setCon(shopInfoPojo.getCon());
        }
        if(!shopInfoPojo.getStatus().equals("")){
            re.setStatus(shopInfoPojo.getStatus());
        }
        return true;
    }

    @Override
    public boolean deleteShopInfo(ShopInfoPojo shopInfoPojo) {
        List <ShopInfoPojo> result= shopInfoDao.findByShopid(shopInfoPojo.getShopid());
        if(result == null){
            result = new ArrayList<ShopInfoPojo>();
        }
        if(result.size()<1){
            return false;
        }
        shopInfoDao.delete(result.get(0));
        return false;
    }

    @Override
    public List<ShopInfoPojo> queryShopInfo(List<ShopInfoField> shopInfoFieldList, List<String> valueList) {
        return shopInfoDao.queryShopInfo(shopInfoFieldList,valueList);

    }

    @Override
    public boolean addShopUser(ShopUserPojo shopUserPojo) {
        if(!checkUser(shopUserPojo)){
            return false;
        }
        List<ShopUserPojo> result=shopUserDao.findByUsername(shopUserPojo.getUsername());
        if(result == null){
            result = new ArrayList<ShopUserPojo>();
        }
        if(!(result.size()>0)){
            return false;

        }
        shopUserDao.save(shopUserPojo);
        return false;
    }

    @Override
    public boolean updateShopUser(ShopUserPojo shopUserPojo) {
       List<ShopUserPojo> result= shopUserDao.findByShopid(shopUserPojo.getShopid());
        if(result == null){
           return false;
        }
       if(result.size()<1){
           return false;
       }
        ShopUserPojo re= result.get(0);
        if(!shopUserPojo.getPasswd().equals("")){
            re.setPasswd(shopUserPojo.getPasswd());
        }
        if(!shopUserPojo.getUsername().equals("")){
            re.setUsername(shopUserPojo.getUsername());
        }
        if(!shopUserPojo.getUsername().equals("")){
            re.setUsername(shopUserPojo.getUsername());
        }
       shopUserDao.update(shopUserPojo);
        return true;
    }

    @Override
    public boolean deleteShopUser(ShopUserPojo shopUserPojo) {
        List<ShopUserPojo> result= shopUserDao.findByShopid(shopUserPojo.getShopid());
        if(result == null){
            result = new ArrayList<ShopUserPojo>();
        }
        if(result.size()<1){
            return false;
        }
        shopUserDao.delete(result.get(0));
        return true;
    }

    private boolean checkUser(ShopUserPojo shopUserPojo){
        //密码不能小于6位
        if(shopUserPojo.getPasswd().length()<6){
            return false;
        }
        //用户名不能小于4位
        if(shopUserPojo.getUsername().length()<4){
            return false;
        }
        //检验是否存在非法字符

        String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Pattern p = Pattern.compile(regEx);
        if(p.matcher(shopUserPojo.getPasswd()).find()||p.matcher(shopUserPojo.getUsername()).find()){
            return false;
        }

        return true;
    }

    public ShopUserDao getShopUserDao() {
        return shopUserDao;
    }

    public void setShopUserDao(ShopUserDao shopUserDao) {
        this.shopUserDao = shopUserDao;
    }

    public ShopInfoDao getShopInfoDao() {
        return shopInfoDao;
    }

    public void setShopInfoDao(ShopInfoDao shopInfoDao) {
        this.shopInfoDao = shopInfoDao;
    }
}
