package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

    @FindBy(xpath = "//*[@id='root-app']/div/div[1]/section/ol/li[1]/div/div/div[2]/div[2]/div[1]/div[1]/div/div/div/span[1]/span[2]/span[2]")
    WebElement firstItemPrice;

    @FindBy(xpath = "//*[@id='root-app']/div/div[1]/section/ol/li[1]/div/div/div[2]/div[1]/a/h2")
    WebElement firstItemTitle;


    @FindBy(xpath = "//*[@id='root-app']/div[2]/div[3]/div[1]/div[1]/div/div[1]/div[2]/div[1]/div[1]/div[2]/h1")
    WebElement itemTitle;

    @FindBy(xpath = "//*[@id='root-app']/div[2]/div[3]/div[1]/div[1]/div/div[1]/div[2]/div[2]/div[1]/span/span[3]")
    WebElement itemPrice;




    public MeliPage(WebDriver driver) {
        super(driver);
    }




    String firstTitle, firstPrice;


    public void selectFirstItem(){
        waitElement(firstItemTitle,4000);
        firstItemTitle.click();
    }


    public void selectCategories(String Categoria1, String Categoria2) {
    waitElement(btnCategorias,5000);
    btnCategorias.click();
    waitElement(opCategoriaElectrodomestico,5000);
    findByText(Categoria1).click();
    waitElement(optionAires, 5000);
    findByText(Categoria2).click();
    waitElement(resultados,5000);
    System.out.println(getDriver().getTitle());
    System.out.println(resultados.getText());

    }

    public void selectOneCategorie(String categoria){
        waitElement(btnCategorias,5000);
        btnCategorias.click();
        waitElement(opCategoriaElectrodomestico,5000);
        findByText(categoria).click();
        waitElement(optionAires, 5000);

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

    public void setAtributesFirstItem(){
        waitElement(firstItemTitle,8000);
        this.firstPrice= firstItemPrice.getText();
        this.firstTitle=firstItemTitle.getText();
    }



    public boolean validateAtributes(){
        waitElement(itemTitle,8000);
        System.out.println("Expected Price: "+itemPrice.getText()+".    Actual result price: "+ firstPrice);
        System.out.println("Expected      : "+itemTitle.getText());
        System.out.println("Actual result : "+ firstTitle);
        return itemPrice.getText().equals(firstPrice) && itemTitle.getText().equals(firstTitle);
    }

}