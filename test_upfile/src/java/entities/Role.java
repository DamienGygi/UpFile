/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author damien.gygi
 */
@Entity
@Table(name = "role")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Role.findAll", query = "SELECT r FROM Role r"),
    @NamedQuery(name = "Role.findByIdrole", query = "SELECT r FROM Role r WHERE r.idrole = :idrole"),
    @NamedQuery(name = "Role.findByRolename", query = "SELECT r FROM Role r WHERE r.rolename = :rolename")})
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idrole")
    private Integer idrole;
    @Size(max = 50)
    @Column(name = "rolename")
    private String rolename;
    @JoinTable(name = "user_role", joinColumns = {
        @JoinColumn(name = "rolename", referencedColumnName = "rolename")}, inverseJoinColumns = {
        @JoinColumn(name = "username", referencedColumnName = "username")})
    @ManyToMany
    private Collection<UserUpfile> userUpfileCollection;

    public Role() {
    }

    public Role(Integer idrole) {
        this.idrole = idrole;
    }

    public Integer getIdrole() {
        return idrole;
    }

    public void setIdrole(Integer idrole) {
        this.idrole = idrole;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    @XmlTransient
    public Collection<UserUpfile> getUserUpfileCollection() {
        return userUpfileCollection;
    }

    public void setUserUpfileCollection(Collection<UserUpfile> userUpfileCollection) {
        this.userUpfileCollection = userUpfileCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idrole != null ? idrole.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Role)) {
            return false;
        }
        Role other = (Role) object;
        if ((this.idrole == null && other.idrole != null) || (this.idrole != null && !this.idrole.equals(other.idrole))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Role[ idrole=" + idrole + " ]";
    }
    
}
