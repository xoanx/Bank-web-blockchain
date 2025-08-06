package com.example.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/message")
public class HelloController {

    @Autowired
    private HelloService helloService;

    @GetMapping("/get")
    public String getMessage() {
        try{
            return helloService.getMessage ();
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    @PostMapping("/update")
    public String updateMessage(@RequestParam String msg) {
        try{
            helloService.updateMessage (msg);
            return "Updated:" + msg;
        } catch (Exception e) {
            return "Error updating: " + e.getMessage();
        }
    }
}
