/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Calendar;
import java.util.Objects;
import java.util.Set;
import static javax.persistence.CascadeType.ALL;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


/**
 * Entity representing generic user of the application. It contains the following fields:
 * userId, login, email, password, telephone, lastPasswordChange, status, birthDate, fullName.
 * @author Miguel Ángel Sánchez
 */

//@MappedSuperclass
@NamedQueries({
    @NamedQuery(name="findUserByEmail", 
            query="SELECT u FROM User u WHERE u.email=:email"),
    @NamedQuery(name="findUserByPassword",
            query="SELECT u FROM User u WHERE u.password=:password and u.login=:login"),
    @NamedQuery(name="findExistingUser",
            query="SELECT u from User u WHERE u.login=:login or u.email=:email")
})
@Entity
@Table(name="user",schema="maz_solutions")
@Inheritance(strategy = InheritanceType.JOINED)
@XmlRootElement
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    /**
     * Identification field for the user.
     */
    private Long idUser;
    
    /**
     * Identification field for the user.
     */
    @Column(unique = true)
    private String login;
    /**
     * Email field for the user.
     */
    @Column(unique = true)
    private String email;
    /**
     * Password field for the user.
     */
    private String password;
    /**
     * Telephone field for the user.
     */
    private String telephone;
    /**
     * Last password change date field for the user.
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar lastPasswordChange;
    /**
     * Status field for the user(ENABLED, DISALBED).
     */
    @Enumerated(EnumType.ORDINAL)
    private UserStatus status;
    /**
     * Privileges field for the user(USER, ADMIN, STUDENT, TEACHER).
     */
    @Enumerated(EnumType.ORDINAL)
    private UserPrivilege privilege;
    /**
     * Birth date for the user.
     */
    @Temporal(TemporalType.DATE)
    private Date birthDate;
    /**
     * Full name for the user.
     */
    private String fullName;
    @OneToMany(cascade = ALL, mappedBy = "user",fetch=FetchType.EAGER)
    private Set<LastSignIn> lastSignIn;

    /**
     *
     * @return lastsignin
     */
    @XmlTransient
    public Set<LastSignIn> getLastSignIn() {
        return lastSignIn;
    }

    /**
     *
     * @param lastSignIn lastsignin to set
     */
    public void setLastSignIn(Set<LastSignIn> lastSignIn) {
        this.lastSignIn = lastSignIn;
    }
    
    /**
     * 
     * @return Return the id.
     */
    public Long getIdUser() {
        return idUser;
    }
    /**
     * 
     * @param idUser Method to set the id
     */
    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }
    /**
     * 
     * @return Return the login.
     */
    public String getLogin() {
        return login;
    }
    /**
     * 
     * @param login Method to set the login.
     */
    public void setLogin(String login) {
        this.login = login;
    }
    /**
     * 
     * @return Method wich returns the email.
     */
    public String getEmail() {
        return email;
    }
    /**
     * 
     * @param email Method to set the email.
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * 
     * @return Method wich returns the password.
     */
    public String getPassword() {
        return password;
    }
    /**
     * 
     * @param password Method to set the password.
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * 
     * @return Method wich returns the telephone.
     */
    public String getTelephone() {
        return telephone;
    }
    /**
     * 
     * @param telephone Method to set the telephone.
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    /**
     * 
     * @return Method wich return the last password change date.
     */
    public Calendar getLastPasswordChange() {    
        return lastPasswordChange;
    }

    /**
     * 
     * @param lastPasswordChange Method to set the last password change date.
     */
    public void setLastPasswordChange(Calendar lastPasswordChange) {
        this.lastPasswordChange = lastPasswordChange;
    }
    /**
     * 
     * @return Method wich returns the user status. 
     */
    public UserStatus getStatus() {
        return status;
    }
    /**
     * 
     * @param status Method to set the user status.
     */
    public void setStatus(UserStatus status) {
        this.status = status;
    }
    /**
     * 
     * @return Method wich returns the bird date of the user.
     */
    public Date getBirthDate() {
        return birthDate;
    }
    /**
     * 
     * @param birthDate Method to set the users birth date.
     */
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
    /**
     * 
     * @return Method wich returns the users full name.
     */
    public String getFullName() {
        return fullName;
    }
    /**
     * 
     * @param fullName Method to set the users full name.
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    /**
     * 
     * @return Method wich returns the user privilege.
     */
    public UserPrivilege getPrivilege() {
        return privilege;
    }
    /**
     * 
     * @param privilege Method to set the user privilege.
     */
    public void setPrivilege(UserPrivilege privilege) {
        this.privilege = privilege;
    }
    
    
    
    
    /**
     * Method that returns an integer representation of user instance.
     * @return hash
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.idUser);
        hash = 67 * hash + Objects.hashCode(this.login);
        hash = 67 * hash + Objects.hashCode(this.email);
        hash = 67 * hash + Objects.hashCode(this.password);
        hash = 67 * hash + Objects.hashCode(this.telephone);
        hash = 67 * hash + Objects.hashCode(this.lastPasswordChange);
        hash = 67 * hash + Objects.hashCode(this.status);
        hash = 67 * hash + Objects.hashCode(this.birthDate);
        hash = 67 * hash + Objects.hashCode(this.fullName);
        return hash;
    }

    
    /**
     * 
     * @param object Receives an object.
     * @return Returns true or false depending if that object is a User.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.idUser == null && other.idUser != null) || (this.idUser != null && !this.idUser.equals(other.idUser))) {
            return false;
        }
        return true;
    }

    
    /**
     * This method returns a String with the diferent attributes of this class.
     * @return The string representing the User.
     */
    @Override
    public String toString() {
        return "User{" + "userId=" + idUser + ", login=" + login + ", email=" + email + ", password=" + password + ", telephone=" + telephone + ", lastPasswordChange=" + lastPasswordChange + ", status=" + status + ", birthDate=" + birthDate + ", fullName=" + fullName + '}';
    }
    
}
