/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sd479.webapps2020.jsf;

import com.sd479.webapps2020.dao.SystemUserDao;
import com.sd479.webapps2020.dao.UserTransactionDao;
import com.sd479.webapps2020.entity.SystemUser;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Scott
 */
@Named(value = "userBean")
@RequestScoped
public class UserBean {

    @EJB(name = "systemUserDao")
    SystemUserDao systemUserDao;

    @EJB(name = "userTransactionDao")
    UserTransactionDao userTransactionDao;

    private String userName;

    public UserBean() {
    }

    @RolesAllowed("users")
    public String getUserName() {
        return userName;
    }

    @RolesAllowed("users")
    public void setUserName(String userName) {
        this.userName = userName;
    }

    @RolesAllowed("users")
    public List<SystemUser> getUserList() {
        SystemUser currentUser = getLoggedInUser();

        List<SystemUser> users = systemUserDao.findAllSystemUsers();
        users.remove(currentUser);

        return users;
    }

    @RolesAllowed("users")
    public SystemUser getLoggedInUser() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getRemoteUser();

        String currentUserUsername = context.getExternalContext().getRemoteUser();

        SystemUser currentUser = systemUserDao.findSystemUserByUsername(currentUserUsername);

        return currentUser;
    }

    @RolesAllowed("users")
    public BigDecimal getCurrentUserBalance() {
        return getLoggedInUser().getBalance();
    }

    @RolesAllowed("users")
    public SystemUserDao getSystemUserDao() {
        return systemUserDao;
    }

    @RolesAllowed("users")
    public void setSystemUserDao(SystemUserDao systemUserDao) {
        this.systemUserDao = systemUserDao;
    }

    @RolesAllowed("users")
    public UserTransactionDao getUserTransactionDao() {
        return userTransactionDao;
    }

    @RolesAllowed("users")
    public void setUserTransactionDao(UserTransactionDao userTransactionDao) {
        this.userTransactionDao = userTransactionDao;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.systemUserDao);
        hash = 43 * hash + Objects.hashCode(this.userTransactionDao);
        hash = 43 * hash + Objects.hashCode(this.userName);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserBean other = (UserBean) obj;
        if (!Objects.equals(this.userName, other.userName)) {
            return false;
        }
        if (!Objects.equals(this.systemUserDao, other.systemUserDao)) {
            return false;
        }
        if (!Objects.equals(this.userTransactionDao, other.userTransactionDao)) {
            return false;
        }
        return true;
    }

}
