/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import java.util.Map;
import javax.transaction.Transactional;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import entity.Depart;

import java.util.ArrayList;
import org.hibernate.Transaction;

/**
 *
 * @author Thoai
 */
@Transactional
@Controller
@RequestMapping(value = "/depart/")
public class DepartController {

    @Autowired
    SessionFactory factory;

    @RequestMapping("index")
    public String index(ModelMap model) {
        Session session = factory.openSession();
        String hql = "FROM Depart";
        Query query = session.createQuery(hql);
        List<Depart> list = query.list();
        model.addAttribute("departs", list);
        return "depart/index";
    }

    @RequestMapping(value = "insert", method = RequestMethod.GET)
    public String insert(ModelMap model) {
        model.addAttribute("depart", new Depart());
        return "depart/insert";
    }
@RequestMapping(params="btnInsert")
	public String insert(ModelMap model, @ModelAttribute("depart") Depart depart) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(depart);
			t.commit();
			model.addAttribute("message", "Thêm mới thành công !");
		} 
		catch (Exception e) {
			t.rollback();
			model.addAttribute("message", "Thêm mới thất bại !");
		}
		finally {
			session.close();
		}
		model.addAttribute("depart", getDeparts());
		return "depart/insert";
	}
    @RequestMapping(value = "/{action}/{departId}", method = {RequestMethod.GET, RequestMethod.POST})
    public String actionCRUD(@ModelAttribute("depart") Depart depart, @PathVariable Map<String, String> pathvars) {
        Session session = factory.openSession();
        session.getTransaction().begin();
        String action = pathvars.get("action");
        String departId = pathvars.get("departId");
        try {
            if (action.equalsIgnoreCase("delete")) {
                session.delete(session.get(Depart.class, departId));
            } else if (action.equalsIgnoreCase("update")) {
                System.out.println("Update : " + depart.getDepId());
                session.update(depart);
            } else if (action.equalsIgnoreCase("insert")) {
                session.save(depart);
            }
            session.getTransaction().commit();
            System.out.println("Action Thành Công!");
        } catch (Exception e) {
            System.out.println("Action Bị Lỗi!");
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return "redirect:/depart/index.htm"; 
    }

    @RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
    public String editPage(ModelMap model, @PathVariable(value = "id") String id) {
        Session session = factory.openSession();
        session.getTransaction().begin();
        try {
            Depart depart = (Depart) session.get(Depart.class, id);
            model.addAttribute("depart", depart);
            session.getTransaction().commit();
            System.out.println("Action Thành Công!");
        } catch (Exception e) {
            System.out.println("Action Bị Lỗi!");
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return "depart/edit";
    }

    @ModelAttribute("departs")
    public List<Depart> getDeparts() {
        Session session = factory.openSession();
        String hql = "FROM Depart";
        Query query = session.createQuery(hql);
        List<Depart> list = query.list();
        return list;
    }
}
