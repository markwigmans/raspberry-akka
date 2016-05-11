package com.ximedes.rb.portal.Controller;

import com.ximedes.rb.portal.model.Card;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by mawi on 23/12/2015.
 */
@Controller
public class CardController {


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("card") Card card) {


        return "redirect:index.html";
    }
}
