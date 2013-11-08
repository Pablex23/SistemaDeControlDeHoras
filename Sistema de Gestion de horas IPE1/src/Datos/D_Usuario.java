/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Hibernate.NewHibernateUtil;
import Hibernate.TbUsuario;
import java.io.Console;
import org.hibernate.Session;
import java.util.List;
import org.hibernate.SQLQuery;

/**
 *
 * @author Usuario HP
 */
public class D_Usuario {
    Session conexion = NewHibernateUtil.getSessionFactory().openSession();
  
    public D_Usuario(){

}
    
    public void insertar ( String Cargo, Integer Cod_Usuario, String Contrasenha, Integer Cod_Rol, Integer CI ) {

    conexion.getTransaction().begin();
    TbUsuario usuarioObj= new TbUsuario (Cod_Usuario, Cargo, Contrasenha, Cod_Rol, CI);
    conexion.save(usuarioObj);
    conexion.getTransaction().commit();
    
}

    
    public TbUsuario getUsuario(int codigo){
    conexion.getTransaction().begin();
    TbUsuario Usuario = (TbUsuario)conexion.load(TbUsuario.class, codigo);
        return Usuario;
}

    public void eliminar (int codigo){

    conexion.getTransaction().begin();
    TbUsuario Usuario = (TbUsuario)conexion.load(TbUsuario.class, codigo);
    conexion.delete(Usuario);
    conexion.getTransaction().commit(); 
}

    public void Modificar (String cargo, int codUsuario, String contrasenha, Integer codRol, Integer Ci ){
    conexion.getTransaction().begin();
    TbUsuario Usuario = (TbUsuario)conexion.load(TbUsuario.class, codUsuario);
    Usuario.setCargo(cargo);
    Usuario.setContrasenha(contrasenha);
    Usuario.setCodRol(codRol);
    Usuario.setCi(Ci);
    conexion.update(Usuario);
    conexion.getTransaction().commit();
}

    public List getAllList(){
    conexion.getTransaction().begin();
    String consulta = "select * from Tb_Usuario";
    SQLQuery consultabd= conexion.createSQLQuery(consulta).addEntity(TbUsuario.class);
    List listabd;
    listabd =  consultabd.list();
    return  listabd;

}

    public static void main(String[] args) {
    D_Usuario cc = new D_Usuario();
    List listaUsuario = cc.getAllList();
        for (int i = 0; i < listaUsuario.size(); i++) {
           TbUsuario usuario = (TbUsuario) listaUsuario.get(i);
          System.out.println(usuario.getCargo() + " --- "+ usuario.getCodRol());
        }
   }

}

