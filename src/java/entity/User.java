/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

/**
 *
 * @author Thoai
 */
@Entity
@Table(name = "Users")
public class User {
    @Id
    @Column(name = "Username")
    @Size(min=3,max=30)
    private String username;
    @Column(name = "Password")
    
    private String password;
    @Column(name = "Fullname")
    private String fullname;
    private String cn;

    
    public String getUsername() {
        return username;
    }

    public String getCn() {
        return cn;
    }

    public void setCn(String cn) {
        this.cn = cn;
    }

   

   

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
    
}
