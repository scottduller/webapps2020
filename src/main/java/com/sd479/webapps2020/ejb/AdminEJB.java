/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sd479.webapps2020.ejb;

import com.sd479.webapps2020.entity.SystemUser;
import com.sd479.webapps2020.entity.SystemUserGroup;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Scott
 */
@Stateless
public class AdminEJB {

    @PersistenceContext
    EntityManager em;

    public AdminEJB() {
    }

    public void registerAdmin(String email, String firstName, String surname, String username, String password, String currency, BigDecimal balance) {
        try {
            SystemUser systemUser;
            SystemUserGroup systemUserGroup;

            MessageDigest md = MessageDigest.getInstance("SHA-256");
            String pw = password;
            md.update(pw.getBytes("UTF-8"));
            byte[] digest = md.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < digest.length; i++) {
                sb.append(Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1));
            }
            String passwordDB = sb.toString();

            systemUser = new SystemUser(email, firstName, surname, username, passwordDB, balance, currency);
            systemUserGroup = new SystemUserGroup(username, "admins");

            em.persist(systemUser);
            em.persist(systemUserGroup);

        } catch (Exception e) {
            Logger.getLogger(AdminEJB.class.getName()).log(Level.SEVERE, null, e);
        }
    }

}