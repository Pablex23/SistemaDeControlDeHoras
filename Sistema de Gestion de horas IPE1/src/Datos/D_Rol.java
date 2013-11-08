/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Hibernate.NewHibernateUtil;
import Hibernate.TbRol;
import java.io.Console;
import org.hibernate.Session;
import java.util.List;
import org.hibernate.SQLQuery;

/**
 *
 * @author Usuario HP
 */
public class D_Rol {
    Session conexion = NewHibernateUtil.getSessionFactory().openSession();
  
    public D_Rol(){

}
    
    public void insertar ( Integer Cod_Rol, String Descripcion_Rol) {

    conexion.getTransaction().begin();
    TbRol usuarioObj= new TbRol(Cod_Rol, Descripcion_Rol);
    conexion.save(usuarioObj);
    conexion.getTransaction().commit();
    
}

    
    public TbRol getRol(int codigo){
    conexion.getTransaction().begin();
    TbRol Rol = (TbRol)conexion.load(TbRol.class, codigo);
        return Rol;
}

    public void eliminar (int codigo){

    conexion.getTransaction().begin();
    TbRol Rol = (TbRol)conexion.load(TbRol.class, codigo);
    conexion.delete(Rol);
    conexion.getTransaction().commit(); 
}

    public void Modificar (int codRol, String descripcionRol ){
    conexion.getTransaction().begin();
    TbRol Rol = (TbRol)conexion.load(TbRol.class, codRol);
    Rol.setCodRol(codRol);
    Rol.setDescripcionRol(descripcionRol);
    conexion.update(Rol);
    conexion.getTransaction().commit();
}

    public List getAllList(){
    conexion.getTransaction().begin();
    String consulta = "select * from Tb_Rol";
    SQLQuery consultabd= conexion.createSQLQuery(consulta).addEntity(TbRol.class);
    List listabd;
    listabd =  consultabd.list();
    return  listabd;

}

    public static void main(String[] args) {
    D_Rol cc = new D_Rol ();
    List listaRol = cc.getAllList();
        for (int i = 0; i < listaRol.size(); i++) {
           TbRol rol = (TbRol) listaRol.get(i);
          System.out.println(rol.getCodRol() + " --- "+ rol.getDescripcionRol());
        }
   }

}

