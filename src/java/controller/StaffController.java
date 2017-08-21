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
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import entity.Depart;
import entity.Staff;
import entity.User;

/*/**
 *
 * @author Thoai
 */

@Transactional
@Controller
@RequestMapping(value = "/staff/")
public class StaffController {

    @Autowired
    SessionFactory factory;

    @RequestMapping("index")
    public String index(ModelMap model) {
        Session session = factory.openSession();
        String hql = "FROM Staff";
        Query query = session.createQuery(hql);
        List<Staff> list = query.list();
        model.addAttribute("staffs", list);
        return "staff/index";
    }

    @RequestMapping("report")
    public String report(ModelMap model) {
        Session session = factory.openSession();
        String hql = "SELECT r.staff.staId, " + " SUM(case when r.type=1 then 1 else 0 end), " + " SUM(case when r.type=0 then 1 else 0 end)"
                + " FROM Record r "
                + " GROUP BY r.staff.staId";
        Query query = session.createQuery(hql);
        List<Object[]> list = query.list();
        model.addAttribute("arrays", list);
        return "staff/report";
    }
    
    @RequestMapping(value = "insert", method = RequestMethod.GET)
    public String insert(ModelMap model) {
        model.addAttribute("staff", new Staff());
        return "staff/insert";
    }
//@RequestMapping(value = "insert", method = RequestMethod.POST)
//	public String insert(ModelMap model, @ModelAttribute("arrays") Staff staffs) {
//		Session session = factory.openSession();
//		Transaction t = session.beginTransaction();
//		try {
//			session.save(staffs);
//			t.commit();
//			model.addAttribute("message", "Thêm mới thành công !");
//		} 
//		catch (Exception e) {
//			t.rollback();
//			model.addAttribute("message", "Thêm mới thất bại !");
//		}
//		finally {
//			session.close();
//		}
//		model.addAttribute("staffs", new Staff());
//		return "staff/insert";
//	}
    
    @RequestMapping(value = "/{action}/{staffId}",method = {RequestMethod.GET, RequestMethod.POST })
    public String actionCRUD(@ModelAttribute("staff") Staff staff, @PathVariable Map<String, String> pathvars) {
        Session session = factory.openSession();
        session.getTransaction().begin();
        String action = pathvars.get("action");
        String staffId = pathvars.get("staffId");
        try {
            if (action.equalsIgnoreCase("delete")) {
                session.delete(session.get(Staff.class, staffId));
            } else if (action.equalsIgnoreCase("update")) {
                System.out.println("Update : " + staff.getStaId());
                session.update(staff);
            } else if (action.equalsIgnoreCase("insert")) {
                session.save(staff);
            }
            session.getTransaction().commit();
            System.out.println(" Thành Công!");
        } catch (Exception e) {
            System.out.println("Không thành công");
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return "redirect:/staff/index.htm"; 
    }

    @RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
    public String editPage(ModelMap model, @PathVariable(value = "id") String id) {
        Session session = factory.openSession();
        session.getTransaction().begin();
        try {
            Staff staff = (Staff) session.get(Staff.class, id);
            model.addAttribute("staff", staff);
            session.getTransaction().commit();
            System.out.println(" Thành Công!");
        } catch (Exception e) {
            System.out.println("Không thành công");
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return "staff/edit";
    }
    
    @ModelAttribute("departs")
    public List<Depart> getDeparts() {
        Session session = factory.openSession();
        String hql = "FROM Depart";
        Query query = session.createQuery(hql);
        List<Depart> list = query.list();
        return list;
    }

    private Object getStaff() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
