package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.interactions.Actions;

public class MeliPage extends Base {



    @FindBy(xpath = "//button[@type='button' and @data-testid='action:understood-button']")
    WebElement btnAcceptCookies;

    @FindBy(name = "as_word")
    WebElement barraBusqueda;

    @FindBy(xpath = "//a[@href='https://www.mercadolibre.com.ar/categorias#nav-header']")
    WebElement btnCategorias;

    @FindBy(xpath = "//a[@href='https://www.mercadolibre.com.ar/c/electrodomesticos-y-aires-ac#menu=categories']")
    WebElement opCategoriaElectrodomestico;


    @FindBy(xpath = "//h3[contains(text(), 'AIRES AC.')]")
    WebElement optionAires;

    @FindBy(xpath = "//div/span[contains(text(), 'resultados')]")
    WebElement resultados;


    @FindBy(xpath = "//h2[@class='category-list__title']")
    WebElement secondTitle;



    public MeliPage(WebDriver driver) {
        super(driver);
    }


    public void selectCategories(String Categoria1, String Categoria2) {
    waitElement(btnCategorias,5000);
    btnCategorias.click();
    waitElement(opCategoriaElectrodomestico,5000);
    //opCategoriaElectrodomestico.click();
    findByText(Categoria1).click();
    waitElement(optionAires, 5000);
    //optionAires.click();
    findByText(Categoria2).click();
    waitElement(resultados,5000);
    System.out.println(getDriver().getTitle());
    System.out.println(resultados.getText());

    }

    public void selectOneCategorie(String categoria){
        waitElement(btnCategorias,5000);
        btnCategorias.click();
        waitElement(opCategoriaElectrodomestico,5000);
        //opCategoriaElectrodomestico.click();
        findByText(categoria).click();
        waitElement(optionAires, 5000);
        //optionAires.click();

        System.out.println(secondTitle.getText());

    }


    public boolean isConnected() {
        System.out.println(getDriver().getTitle());
        return getDriver().getTitle().contains("Mercado Libre Argentina");

    }


    public boolean validateTitle(String title) {

        return getDriver().getTitle().contains(title);
    }

    public boolean validateSecondTitle(String texto){
       return secondTitle.getText().contains(texto);
    }

    public void acceptCokkies(){
        btnAcceptCookies.click();
    }

    public WebElement findByText(String texto){
        WebElement e;
      return   e = getDriver().findElement(By.xpath("//*[text()='"+texto+"']"));
    }

}