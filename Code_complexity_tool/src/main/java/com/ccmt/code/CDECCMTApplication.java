package com.ccmt.code;

import com.sliit.cde.driver.service.BootstrapService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class CDECCMTApplication {

   public static void main(String[] args) {
      SpringApplication.run(CDECCMTApplication.class, args);
      BootstrapService.openApp();
   }

}
