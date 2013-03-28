package com.arcbees.carsample.shared.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User implements BaseEntity {
    private static final long serialVersionUID = -4927013195423066882L;

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(name = "hash_password")
    private String hashPassword;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;
    
    @Column(name = "account_id")
    private String accountid;

    public User() {
        firstName = "";
        lastName = "";
    }

    public User(String username, String hashPassword, String accountid, String firstName, String lastName) {
        this.username = username;
        this.hashPassword = hashPassword;
        this.firstName = firstName;
        this.accountid = accountid;
        this.lastName = lastName;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
    }
    
    

    public String getAccountid() {
		return accountid;
	}

	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}

	@Override
    public String toString() {
        String s = " { User ";
        s += "id=" + id + " ";
        s += "username=" + username + " ";
        s += "hasPassword=" + hashPassword + " ";
        s += "accountid=" + accountid + " ";
        s += "firstName=" + firstName + " ";
        s += "lastName=" + lastName + " ";  
        s += " User } ";
        return s;
    }

	
}
