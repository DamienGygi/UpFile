/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import controllers.FileController;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
/**
 *
 * @author damien.gygi
 */
@Entity
@Table(name = "file")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "File.findAll", query = "SELECT f FROM File f"),
    @NamedQuery(name = "File.findByIdfile", query = "SELECT f FROM File f WHERE f.idfile = :idfile"),
    @NamedQuery(name = "File.findByName", query = "SELECT f FROM File f WHERE f.name = :name"),
    @NamedQuery(name = "File.findByCreatedat", query = "SELECT f FROM File f WHERE f.createdat = :createdat"),
    @NamedQuery(name = "File.findByUrl", query = "SELECT f FROM File f WHERE f.url = :url")})
public class File implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idfile")
    private Integer idfile;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "desciption")
    private String desciption;
    @Basic(optional = false)
    @NotNull
    @Column(name = "createdat")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdat;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "url")
    private String url;
    @JoinColumn(name = "idtype", referencedColumnName = "idtype")
    @ManyToOne(optional = false)
    private TypeFile idtype;
    @JoinColumn(name = "iduser", referencedColumnName = "iduser")
    @ManyToOne(optional = false)
    private UserUpfile iduser;

    public File() {
    }

    public File(Integer idfile) {
        this.idfile = idfile;
    }

    public File(Integer idfile, String name, String desciption, Date createdat, String url) throws IOException {
        this.idfile = idfile;
        this.name = name;
        this.desciption = desciption;
        this.createdat = createdat;
        this.url= url;
        
    }

    public Integer getIdfile() {
        return idfile;
    }

    public void setIdfile(Integer idfile) {
        this.idfile = idfile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesciption() {
        return desciption;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }

    public Date getCreatedat() {
        return createdat;
    }

    public void setCreatedat(Date createdat) {
        this.createdat = createdat;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public TypeFile getIdtype() {
        return idtype;
    }

    public void setIdtype(TypeFile idtype) {
        this.idtype = idtype;
    }

    public UserUpfile getIduser() {
        return iduser;
    }

    public void setIduser(UserUpfile iduser) {
        this.iduser = iduser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idfile != null ? idfile.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof File)) {
            return false;
        }
        File other = (File) object;
        if ((this.idfile == null && other.idfile != null) || (this.idfile != null && !this.idfile.equals(other.idfile))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.File[ idfile=" + idfile + " ]";
    }
    
}
