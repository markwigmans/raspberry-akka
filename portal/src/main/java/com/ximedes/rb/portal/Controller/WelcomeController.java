package com.ximedes.rb.portal.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;


/**
 * Created by mawi on 23/12/2015.
 */
@Controller
public class WelcomeController {

    @RequestMapping("/")
    public ModelAndView welcome(final ModelAndView model) {
        model.addObject("time", new Date());
        model.addObject("message", "example text");
        model.setViewName("welcome");
        return model;
    }
}
