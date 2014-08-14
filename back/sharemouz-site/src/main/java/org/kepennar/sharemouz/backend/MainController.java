package org.kepennar.sharemouz.backend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by kepennar on 14/08/14.
 */
@Controller
@RequestMapping("site")
public class MainController {

    @RequestMapping(method= GET)
    public String welcomePage() {
        return "index";
    }
}
