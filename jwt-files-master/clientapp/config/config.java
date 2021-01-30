/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;

/**
 *
 * @author LENOVO
 */
@ApplicationScoped
@DatabaseIdentityStoreDefinition(
        dataSourceLookup = "jdbc/test1_db",
        callerQuery = "select password from tbluser where username=?",
        groupsQuery = "select role from tbluser where username=?",
        hashAlgorithm = Pbkdf2PasswordHash.class,
        priority = 30
)
public class config {
    
}
