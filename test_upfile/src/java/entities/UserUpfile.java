/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author damien.gygi
 */
@Entity
@Table(name = "user_upfile")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserUpfile.findAll", query = "SELECT u FROM UserUpfile u"),
    @NamedQuery(name = "UserUpfile.findByIduser", query = "SELECT u FROM UserUpfile u WHERE u.iduser = :iduser"),
    @NamedQuery(name = "UserUpfile.findByEmail", query = "SELECT u FROM UserUpfile u WHERE u.email = :email"),
    @NamedQuery(name = "UserUpfile.findByUsername", query = "SELECT u FROM UserUpfile u WHERE u.username = :username"),
    @NamedQuery(name = "UserUpfile.findByPassword", query = "SELECT u FROM UserUpfile u WHERE u.password = :password")})
public class UserUpfile implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iduser")
    private Integer iduser;
    @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 100)
    @Column(name = "email")
    private String email;
    @Size(min = 4, max = 100, message="Le username doit contenir minimum 4 charactères")
    @Column(name = "username")
    private String username;
    @Size(min = 6, max = 200, message="Le mot de passe doit contenir minimum 6 charactères")
    @Column(name = "password")
    private String password;
    @ManyToMany(mappedBy = "userUpfileCollection")
    private Collection<Role> roleCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iduser")
    private Collection<File> fileCollection;

    public UserUpfile() {
    }

    public UserUpfile(Integer iduser) {
        this.iduser = iduser;
    }

    public Integer getIduser() {
        return iduser;
    }

    public void setIduser(Integer iduser) {
        this.iduser = iduser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
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

    @XmlTransient
    public Collection<Role> getRoleCollection() {
        return roleCollection;
    }

    public void setRoleCollection(Collection<Role> roleCollection) {
        this.roleCollection = roleCollection;
    }

    @XmlTransient
    public Collection<File> getFileCollection() {
        return fileCollection;
    }

    public void setFileCollection(Collection<File> fileCollection) {
        this.fileCollection = fileCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iduser != null ? iduser.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserUpfile)) {
            return false;
        }
        UserUpfile other = (UserUpfile) object;
        if ((this.iduser == null && other.iduser != null) || (this.iduser != null && !this.iduser.equals(other.iduser))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.UserUpfile[ iduser=" + iduser + " ]";
    }
    
}
