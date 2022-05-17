package test;

import Pages.MeliPage;

import org.testng.Assert;
import org.testng.annotations.Test;


public class Meli_Test extends BaseTest {


    MeliPage objMeliPage;



    @Test()
    public void Ingreso(){
        objMeliPage = new MeliPage(this.driver);
        driver.get("http://www.mercadolibre.com.ar");
        objMeliPage.acceptCokkies();
        Assert.assertTrue(objMeliPage.isConnected(), "No se pudo ingresar en el sitio");
    }



    @Test(dependsOnMethods = "Ingreso", priority = 1)
    public void seleccionarAires(){
        objMeliPage = new MeliPage(this.driver);
        objMeliPage.selectCategories("Electrodomésticos","AIRES AC.");
        Assert.assertTrue(objMeliPage.validateTitle("Aires Acondicionados"), "No fue posible ingresar a la categoria 'Aires'");
    }

    @Test(dependsOnMethods = "Ingreso", priority = 2)
    public void seleccionarCelularesYSmartphones(){
        objMeliPage = new MeliPage(this.driver);
        objMeliPage.selectCategories("Tecnología","Celulares y Smartphones");
        Assert.assertTrue(objMeliPage.validateTitle("Celulares y Smartphones"), "No fue posible ingresar a la categoria 'Aires'");
    }

    //La categoria Perfumes importados no existe
    @Test(dependsOnMethods = "Ingreso", priority = 3)
    public void seleccionarBelleza(){
        objMeliPage = new MeliPage(this.driver);
        objMeliPage.selectOneCategorie("Belleza y Cuidado Personal");
        Assert.assertTrue(objMeliPage.validateSecondTitle("Belleza"), "No fue posible ingresar a la categoria 'Aires'");
    }

    //La categoria Perfumes importados no existe
    @Test(dependsOnMethods = "Ingreso", priority = 4)
    public void seleccionarHerramientas(){
        objMeliPage = new MeliPage(this.driver);
        objMeliPage.selectOneCategorie("Herramientas");
        Assert.assertTrue(objMeliPage.validateSecondTitle("Herramientas"), "No fue posible ingresar a la categoria 'Aires'");
    }

    @Test(dependsOnMethods = "Ingreso", priority = 5)
    public void seleccionarCuartoDelBebe(){
        objMeliPage = new MeliPage(this.driver);
        objMeliPage.selectCategories("Bebés","CUARTO DEL BEBÉ");
        Assert.assertTrue(objMeliPage.validateTitle("Cuarto del Bebé"), "No fue posible ingresar a la categoria 'Aires'");
    }

    @Test(dependsOnMethods = "Ingreso", priority = 6)
    public void elegirCategoriaYvalidar(){
        objMeliPage = new MeliPage(this.driver);
        objMeliPage.selectCategories("Tecnología","Videojuegos");
        objMeliPage.findByText("Capital Federal").click();
        objMeliPage.setAtributesFirstItem();
        objMeliPage.selectFirstItem();

        Assert.assertTrue(objMeliPage.validateAtributes(), "Los campos de la grilla y detalle no son iguales!");
    }



}
