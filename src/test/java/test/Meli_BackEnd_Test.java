package test;


import Services.BackEndClass;
import org.testng.annotations.Test;



public class Meli_BackEnd_Test {



    @Test
    public void doSearch_test() {
        BackEndClass back = new BackEndClass();
        back.doSearch("Ps4");
        back.searchFirstProductById();

    }

    @Test(dependsOnMethods = "doSearch_test", priority = 1)
        public void validar_titulo(){
        BackEndClass back = new BackEndClass();
        back.validateTitle();
    }

    @Test(dependsOnMethods = "doSearch_test",priority = 2)
    public void validar_precio(){
        BackEndClass back = new BackEndClass();
        back.validatePrice();
    }

    @Test(dependsOnMethods = "doSearch_test",priority = 3)
    public void validar_AceptaMp(){
        BackEndClass back = new BackEndClass();
        back.validateAcceptMp();
    }

    @Test(dependsOnMethods = "doSearch_test",priority = 4)
    public void validar_melicoin(){
        BackEndClass back = new BackEndClass();
        back.validateMelicoin();
    }

    @Test(dependsOnMethods = "doSearch_test",priority = 5)
    public void validar_envio_gratis(){
        BackEndClass back = new BackEndClass();
        back.validateFreeShipping();
    }

}


