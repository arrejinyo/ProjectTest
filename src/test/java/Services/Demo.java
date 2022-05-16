package Services;


import org.testng.annotations.Test;

import java.util.*;
import Services.BackEndClass.*;


public class Demo {



    @Test
    public void doSearch_test() {
        BackEndClass back = new BackEndClass();
        back.doSearch("Ps4");
        back.searchFirstProductById();

    }

    @Test(dependsOnMethods = "doSearch_test", priority = 3)
        public void validar_titulo(){
        BackEndClass back = new BackEndClass();
        back.validateTitle();
    }

    @Test(dependsOnMethods = "doSearch_test")
    public void validar_precio(){
        BackEndClass back = new BackEndClass();
        back.validatePrice();
    }

    @Test(dependsOnMethods = "doSearch_test")
    public void validar_AceptaMp(){
        BackEndClass back = new BackEndClass();
        back.validateAcceptMp();
    }

    @Test(dependsOnMethods = "doSearch_test")
    public void validar_melicoin(){
        BackEndClass back = new BackEndClass();
        back.validateMelicoin();
    }

    @Test(dependsOnMethods = "doSearch_test")
    public void validar_envio_gratis(){
        BackEndClass back = new BackEndClass();
        back.validateFreeShipping();
    }

}


