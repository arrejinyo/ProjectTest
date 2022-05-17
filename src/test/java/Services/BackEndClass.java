package Services;


import org.testng.Assert;

import java.util.ArrayList;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.path.json.JsonPath.from;

public class BackEndClass {

   static int  price;
   static String id, title, melicoin, responseProduct;
   static boolean acceptMp, freeShipping;

    public BackEndClass(){

    }


    public void doSearch(String busqueda) {
        //primera parte Obtener limite de paginado, cantidad de resultados devueltos,
        //comparar cantidad de devueltos contra limite de paginado.
        baseURI = "https://api.mercadolibre.com/sites/MLA";
        String response = given().when().get("/search?q=" + busqueda).then().extract().body().asString();

        this.id = from(response).getString("results[0].id");
        this.title= from(response).getString("results[0].title");
        this.price = from(response).getInt("results[0].price");
        this.acceptMp = from(response).getBoolean("results[0].accepts_mercadopago");
        this.melicoin = from(response).getString("results[0].melicoin");
        this.freeShipping = from(response).getBoolean("results[0].shipping.free_shipping");

        int totalResults = from(response).get("paging.total");
        int limitPaginado = from(response).get("paging.limit");
        ArrayList resultadosDevueltos = from(response).get("results");
        System.out.println("El limite de paginado es: " + limitPaginado);
        System.out.println("La cantidad de elementos devueltos es: " + resultadosDevueltos.size());
        System.out.println("La cantidad total de resultados para (" + busqueda + ") es: " + totalResults);

        Assert.assertTrue(resultadosDevueltos.size()<=limitPaginado,
                "La cantidad de productos devueltos (" +
                        +resultadosDevueltos.size() +   ") es superior al limite de paginado ("+limitPaginado+")");
    }


    public void searchFirstProductById() {
        System.out.println("El ID del producto a buscar es " + this.id);
        baseURI = "https://api.mercadolibre.com/items/";
        this.responseProduct = given().when().get(this.id).then().extract().body().asString();

    }


    public void validateTitle() {
        String title = from(responseProduct).get("title").toString();
        Assert.assertTrue(title.equalsIgnoreCase(this.title));
        System.out.println("Titulo: " + title);
    }

    public void validatePrice() {
        int price = from(this.responseProduct).getInt("price");
        Assert.assertTrue(price == this.price);
        System.out.println("Precio: " + price);
    }


    public void validateAcceptMp() {
        boolean aceptaMP = from(this.responseProduct).getBoolean("accepts_mercadopago");
        Assert.assertTrue(aceptaMP == this.acceptMp, "Los valores de Acept MP no coinciden");
        if (aceptaMP) {
            System.out.println("Acepta mercado pago");
        } else {
            System.out.println("NO acepta mercado pago");
        }
    }


    public void validateFreeShipping() {
        boolean freeSheeping = from(responseProduct).getBoolean("shipping.free_shipping");

        if (freeSheeping) {
            System.out.println("El envío es gratis");
        } else {
            System.out.println("El envío no es gratis");
        }
        Assert.assertTrue(freeSheeping == this.freeShipping, "Los valores de 'FreeShipping' no coinciden");
    }





    public void validateMelicoin() {
        try {
            String melicoin = from(responseProduct).get("melicoin").toString();

            System.out.println("Moneda: " + melicoin);
            Assert.assertTrue(melicoin.equalsIgnoreCase(this.melicoin));
        } catch (Exception e) {
            Assert.fail("No se encontró el parametro 'melicoin' en el detalle del producto ID: "+this.id);

        }
    }


}
