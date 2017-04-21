package controllers;
import controllers.util.JsfUtil;
import controllers.util.PaginationHelper;
import entities.File;
import entities.UserUpfile;
import facades.FileFacade;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.Part;
@Named("fileController")
@SessionScoped
public class FileController implements Serializable {

    private entities.File current;
    private DataModel items = null;
    @EJB
    private facades.FileFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    private Part file;
    private List<UserUpfile> actualUserList;
    @PersistenceContext
    private EntityManager em;
   
    
    public List<UserUpfile> findUserByName (String name) 
    { 
        return em.createNamedQuery("UserUpfile.findByUsername").setParameter("username", name).setMaxResults(1).getResultList(); 
    }

    public Part getFile() {
    return file;
    }
 
    public void setFile(Part file) {
    this.file = file;
    }
    public void upload() throws IOException
    {
        if (file != null){
            try {
                String selectedName= current.getName();
                String Extension[]=selectedName.split("\\.");
                if(Extension.length<2)
                {
                    String extensionToAdd[]=file.getSubmittedFileName().split("\\.");
                    current.setName(current.getName()+"."+extensionToAdd[Extension.length]);
                }          
                String file_name = UUID.randomUUID().toString() + "_" + file.getSubmittedFileName();
                String currentDir = new java.io.File( "." ).getCanonicalPath();
                String file_url = currentDir+"\\"+current.getName();
                current.setUrl(file_name);
                InputStream is = file.getInputStream();
                byte[] buffer = new byte[is.available()];
                is.read(buffer);
                java.io.File targetFile = new java.io.File(file_url);
                OutputStream outStream = new FileOutputStream(targetFile);
                outStream.write(buffer);
                System.out.println(currentDir+"|| WORKING");
                JsfUtil.addSuccessMessage("File upload success");
                
                
            } catch (IOException ex) {
            }
        }
    }
    public FileController() {
    }

    public File getSelected() {
        if (current == null) {
            current = new File();
            selectedItemIndex = -1;
        }
        return current;
    }

    private FileFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() throws FileNotFoundException, IOException {
        current = (File) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }
    
    
    public void download() throws FileNotFoundException, MalformedURLException, IOException
    {  
      String name =current.getName();
      String currentDir = new java.io.File( "." ).getCanonicalPath();
      URL website = new URL(new java.io.File(currentDir+"\\"+name).toURI().toURL().toString());
      try (InputStream in = website.openStream()) 
      {
        Path target = Paths.get(System.getProperty("user.home")+"\\Downloads\\"+name);
        Files.copy(in,target, StandardCopyOption.REPLACE_EXISTING);
      }
    }
    
    public void downloadFile()
    {
         try {
            JsfUtil.addSuccessMessage("File download successed");
            download();
        } catch (Exception e) {
             JsfUtil.addErrorMessage(e, "File download failed");
        }
    }
    public String prepareCreate() {
        current = new File();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            actualUserList=new ArrayList<>();
            actualUserList=findUserByName(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
            UserUpfile actualUser= actualUserList.get(0);
            current.setIduser(actualUser);
            System.out.println(file.toString());
            upload();
            current.setUrl("../"+current.getName());
            Date date = new Date();
            current.setCreatedat(date);
            getFacade().create(current);         
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (File) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }
   
    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("FileUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }
    public void deleteFile(String filename) throws IOException
    {
        String currentDir = new java.io.File( "." ).getCanonicalPath();
        java.io.File delFile = new java.io.File(currentDir+"\\"+filename);
        if(delFile.exists() && !delFile.isDirectory()) { 
            delFile.delete();
        }    
    }
    public String destroy() throws IOException {
        current = (File) getItems().getRowData();
        deleteFile(current.getName());
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("FileDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public File getFile(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = File.class)
    public static class FileControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            FileController controller = (FileController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "fileController");
            return controller.getFile(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof File) {
                File o = (File) object;
                return getStringKey(o.getIdfile());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + File.class.getName());
            }
        }

    }

}
