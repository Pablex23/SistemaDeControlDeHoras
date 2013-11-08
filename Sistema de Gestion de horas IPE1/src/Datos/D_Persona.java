/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Hibernate.NewHibernateUtil;
import Hibernate.TbPersona;
import java.io.Console;
import org.hibernate.Session;
import java.util.List;
import org.hibernate.SQLQuery;

/**
 *
 * @author Usuario HP
 */
public class D_Persona {
    Session conexion = NewHibernateUtil.getSessionFactory().openSession();
  
    public D_Persona(){

}
    
    public void insertar ( Integer CI, String Nom_Cliente, String Ape_Persona, Integer Telefono_Persona, String Direccion_Persona ) {

    conexion.getTransaction().begin();
    TbPersona usuarioObj= new TbPersona(CI, Nom_Cliente, Ape_Persona, Telefono_Persona, Direccion_Persona);
    conexion.save(usuarioObj);
    conexion.getTransaction().commit();
    
}

    
    public TbPersona getPersona(int codigo){
    conexion.getTransaction().begin();
    TbPersona Persona = (TbPersona)conexion.load(TbPersona.class, codigo);
        return Persona;
}

    public void eliminar (int codigo){

    conexion.getTransaction().begin();
    TbPersona Persona = (TbPersona)conexion.load(TbPersona.class, codigo);
    conexion.delete(Persona);
    conexion.getTransaction().commit(); 
}

    public void Modificar (int ci, String nomPersona, String apePersona, String direccionPersona, int telefonoPersona ){
    conexion.getTransaction().begin();
    TbPersona Persona = (TbPersona)conexion.load(TbPersona.class, ci);
    Persona.setNomPer(nomPersona);
    Persona.setApePer(apePersona);
    Persona.setDireccion(direccionPersona);
    Persona.setTelefono(telefonoPersona);
    conexion.update(Persona);
    conexion.getTransaction().commit();
}

    public List getAllList(){
    conexion.getTransaction().begin();
    String consulta = "select * from Tb_Persona";
    SQLQuery consultabd= conexion.createSQLQuery(consulta).addEntity(TbPersona.class);
    List listabd;
    listabd =  consultabd.list();
    return  listabd;

}

    public static void main(String[] args) {
    D_Persona cc = new D_Persona();
    List listaPersona = cc.getAllList();
        for (int i = 0; i < listaPersona.size(); i++) {
           TbPersona persona = (TbPersona) listaPersona.get(i);
          System.out.println(persona.getNomPer() + " --- "+ persona.getApePer());
        }
   }

}

