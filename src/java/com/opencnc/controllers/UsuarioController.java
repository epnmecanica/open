/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.opencnc.controllers;

import com.opencnc.beans.Usuario;
import com.opencnc.util.HibernateUtil;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author root
 */
@Controller
public class UsuarioController {
    @RequestMapping  ("/usuario/lista")
    public ModelAndView   lista  (){
        Session  s = HibernateUtil.getSessionFactory().openSession();
        
        Criteria  c =s.createCriteria(Usuario.class);
        List<Usuario> l = c.list();
        ModelAndView m = new ModelAndView("/usuario/lista");
        m.addObject("usuarios",l);
 
        return m;
    }
    @RequestMapping ("/usuario/crear")
    public ModelAndView crear (){
        Usuario u = new Usuario();
        
        ModelAndView m = new ModelAndView("/usuario/crear");
        m.addObject("usuario",u);
        return m;
    }
    @RequestMapping ("/usuario/guardar")
    public ModelAndView guardar (@ModelAttribute Usuario usuario){
        if (!"".equals(usuario.getApellido()) 
            && !"".equals(usuario.getNombre()) 
            && !"".equals(usuario.getEmail())
             ){
            
            usuario.setEstado("A");
            Calendar c = new GregorianCalendar();
            Date d1 = c.getTime();
            
            usuario.setCreadoFecha(d1);
            Session s = HibernateUtil.getSessionFactory().openSession();
            
            Transaction t = s.getTransaction();
            s.beginTransaction();
            s.saveOrUpdate(usuario);
            t.commit();
        }
        return lista();
    }
    
    @RequestMapping  ("/usuario/editar/{id}")
    public ModelAndView   crear  ( @PathVariable  Integer id ){
         
        Session s = HibernateUtil.getSessionFactory().openSession();
        
        Usuario u = (Usuario)s.get(Usuario.class, id);
        ModelAndView m = new ModelAndView ("/usuario/editar");
        m.addObject("usuario",u);
        
        
        return m;
    }
    
    @RequestMapping ("/usuario/borrar/{id}")
    public ModelAndView borrar(@PathVariable Integer id){
        Session s = HibernateUtil.getSessionFactory().openSession();
        
        Usuario u = (Usuario) s.get(Usuario.class, id);
        Transaction t = s.beginTransaction();
        s.delete(u);
        t.commit();
        return lista();
    }
    
    @RequestMapping("/usuario/login")
    public ModelAndView login (){
        Usuario u = new Usuario();
        
        ModelAndView m = new ModelAndView("/usuario/login");
        m.addObject("usuario",u);
        return m;
    }
    
    /*
    @RequestMapping("/usuario/login")
    public ModelAndView login (ModelAndView m){
        
        return m;
    }
    */
    @RequestMapping("/usuario/iniciarSesion")
    public ModelAndView iniciarSesion (@ModelAttribute Usuario usuario){
      ModelAndView m = new ModelAndView();
    
      Session s = HibernateUtil.getSessionFactory().openSession();
      
      Criteria c = s.createCriteria(Usuario.class);
      c.add(Restrictions.eq("email", usuario.getEmail()));
      
      List<Usuario> l = c.list();
     
      if (l.isEmpty()){
          m.addObject("error","Usuario no existe");
      }
      if ("".equals(usuario.getEmail())){
          m.addObject("error","Casilla vacia");
      }
        m.addObject("usuario",usuario);
        //return login(m);
        return login();
    }
       
}
