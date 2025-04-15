package cholog;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MemberController {

    @GetMapping("/hello")
    public String world(@RequestParam("name") String name, Model model) {
        System.out.println(name);
        model.addAttribute("name", name);
        System.out.println(model);
        return "hello";
    }

    @GetMapping("/json")
    @ResponseBody
    public Person json() {
        return new Person("brown", 20);
    }
}
