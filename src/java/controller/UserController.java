/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import entity.User;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Thoai
 */
@Transactional
@Controller
@RequestMapping(value = "/user/")
public class UserController {

    @Autowired
    SessionFactory factory;

    @RequestMapping(value = "index")
    public String index(ModelMap model) {
        Session session = factory.openSession();
        String hql = "FROM User";
        Query query = session.createQuery(hql);
        List<User> list = query.list();
        model.addAttribute("users", list);
        return "user/index";
    }

    @RequestMapping(value = "insert", method = RequestMethod.GET)
    public String insert(ModelMap model) {
        model.addAttribute("user", new User());
        return "user/insert";
    }

   
    @RequestMapping(value = "/{action}/{username}",method = {RequestMethod.GET, RequestMethod.POST })
    public String actionCRUD(@Valid @ModelAttribute("user") User user, @PathVariable Map<String, String> pathvars,BindingResult result) {
        Session session = factory.openSession();
        session.getTransaction().begin();
        String action = pathvars.get("action");
        String username = pathvars.get("username");
        try {
            if (action.equalsIgnoreCase("delete")) {
                session.delete(session.get(User.class, username));
            } else if (action.equalsIgnoreCase("update")) {
                System.out.println("Update : " + user.getUsername());
                session.update(user);
            } else if (action.equalsIgnoreCase("insert")) {
                if(result.hasErrors()){
                    return "user/insert";
                }else {
                    session.save(user);
                }
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
        return "redirect:/user/index.htm"; 
    }

    @RequestMapping(value = "edit/{username}", method = RequestMethod.GET)
    public String editPage(ModelMap model, @PathVariable(value = "username") String username) {
        Session session = factory.openSession();
        session.getTransaction().begin();
        try {
            User user = (User) session.get(User.class, username);
            model.addAttribute("user", user);
            session.getTransaction().commit();
            System.out.println("Action Thành Công!");
        } catch (Exception e) {
            System.out.println("Action Bị Lỗi!");
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return "user/edit";
    }
    @RequestMapping("login")
	public String login() {
		return "user/login";
	}
	@RequestMapping(value="login", method=RequestMethod.POST)
	public String login(ModelMap model, 
			@RequestParam("username") String username,
			@RequestParam("password") String password,
			HttpSession httpSession) {
		Session session = factory.getCurrentSession();
		try {
			User user = (User) session.get(User.class, username);
			if(!user.getPassword().equals(password)){
				model.addAttribute("message", "Sai tên mật khẩu !");
			}
			else{
				httpSession.setAttribute("user", user);
				model.addAttribute("message", "Đăng nhập thành công !");
			}
		} 
		catch (Exception e) {
			model.addAttribute("message", "Sai tên đăng nhập !");
		}
		
		return "user/login";
	}

	@RequestMapping("logoff")
	public String logoff(HttpSession httpSession) {
		httpSession.removeAttribute("user");
		return "redirect:/user/login.htm";
	}
}
