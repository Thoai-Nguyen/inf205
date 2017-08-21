/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.Date;
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
import entity.Record;
import entity.Staff;

/**
 *
 * @author Thoai
 */
@Transactional
@Controller
@RequestMapping("/record/")
public class RecordController {

    @Autowired
    SessionFactory factory;

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(ModelMap model) {
        Session session = factory.openSession();
        String hql = "FROM Record";
        Query query = session.createQuery(hql);
        List<Record> list = query.list();
        model.addAttribute("records", list);
        return "record/index";
    }

    @RequestMapping("reportNV")
    public String reportNV(ModelMap model) {
        Session session = factory.openSession();
        String hql = "SELECT r.staff.staId, " + " SUM(case when r.type=1 then 1 else 0 end), " + " SUM(case when r.type=0 then 1 else 0 end)"
                + " FROM Record r "
                + " GROUP BY r.staff.staId";
        Query query = session.createQuery(hql);
        List<Object[]> list = query.list();
        model.addAttribute("arrays", list);
        return "record/reportNV";
    }

    @RequestMapping("reportPB")
    public String reportPB(ModelMap model) {
        Session session = factory.openSession();
        String hql = "SELECT r.staff.depart.depId, " + " SUM(case when r.type=1 then 1 else 0 end), " + " SUM(case when r.type=0 then 1 else 0 end)"
                + " FROM Record r "
                + " GROUP BY r.staff.depart.depId";
        Query query = session.createQuery(hql);
        List<Object[]> list = query.list();
        model.addAttribute("arrays", list);
        return "record/reportPB";
    }

    @RequestMapping(value = "insert", method = RequestMethod.GET)
    public String insert(ModelMap model) {
        model.addAttribute("record", new Record());
        return "record/insert";
    }

    @RequestMapping(value = "/{action}/{recordId}", method = {RequestMethod.GET, RequestMethod.POST})
    public String actionCRUD(@ModelAttribute("record") Record record, @PathVariable Map<String, String> pathvars) {
        Session session = factory.openSession();
        session.getTransaction().begin();
        String action = pathvars.get("action");
         
         String recordId = pathvars.get("recordId");
        try {
            if (action.equalsIgnoreCase("delete")) {
                int id = Integer.parseInt(recordId);
                session.delete(session.get(Record.class, id));
            } else if (action.equalsIgnoreCase("update")) {
                System.out.println("Update : " + record.getRecId());
                session.update(record);
            } else if (action.equalsIgnoreCase("insert")) {
                record.setDate(new Date());
                session.save(record);
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
        return "redirect:/record/index.htm"; 
    }

    @RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
    public String editPage(ModelMap model, @PathVariable(value = "id") int id) {
        Session session = factory.openSession();
        System.out.println("ID : " + id);
        session.getTransaction().begin();
        try {
            Record record = (Record) session.get(Record.class, id);
            model.addAttribute("record", record);
            session.getTransaction().commit();
            System.out.println("Action Thành Công!");
        } catch (Exception e) {
            System.out.println("Action Bị Lỗi!");
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return "record/edit";
    }
    
    @ModelAttribute("staffs")
    public List<Staff> getStaffs() {
        Session session = factory.openSession();
        String hql = "FROM Staff";
        Query query = session.createQuery(hql);
        List<Staff> list = query.list();
        return list;
    }

}
