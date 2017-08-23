package boot.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.Map;

/**
 * PageController
 * Created by XJH on 2017/8/21.
 */
@Controller
@RequestMapping("/page")
public class PageController {

    // 从 application.properties 中读取配置，如取不到默认值为Hello Xiao
    @Value("${application.hello:Hello Xiao}")
    private String hello = "Hello Xiao";

    /**
     * 默认页<br/>
     *
     * @RequestMapping("/") 和 @RequestMapping 是有区别的
     * 如果不写参数，则为全局默认页，加入输入404页面，也会自动访问到这个页面。
     * 如果加了参数“/”，则只认为是根页面。
     *
     * @return String
     */
    @RequestMapping()
    public String index(Map<String, Object> model) {
        model.put("time", new Date());
        model.put("message", this.hello);
        return "index";
    }

    /**
     * 响应到JSP页面page1
     *
     * @return ModelAndView
     */
    @RequestMapping("/page1")
    public ModelAndView page1() {
        ModelAndView mav = new ModelAndView("page/page1");
        mav.addObject("content", hello);
        return mav;
    }

    /**
     * 响应到JSP页面page1（可以直接使用Model封装内容，直接返回页面字符串）
     *
     * @return String
     */
    @RequestMapping("/page2")
    public String page2(Model model) {
        model.addAttribute("content", hello + "（第二种）");
        return "page/page1";
    }
}
