package com.sd479.webapps2020.entity;

import com.sd479.webapps2020.entity.SystemUser;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2021-03-30T19:22:00")
@StaticMetamodel(UserTransaction.class)
public class UserTransaction_ { 

    public static volatile SingularAttribute<UserTransaction, Double> amount;
    public static volatile SingularAttribute<UserTransaction, SystemUser> transactionOwner;
    public static volatile SingularAttribute<UserTransaction, SystemUser> userTransactionFrom;
    public static volatile SingularAttribute<UserTransaction, SystemUser> userTransactionTo;
    public static volatile SingularAttribute<UserTransaction, Long> id;

}