package Services;

import io.restassured.RestAssured;
import org.testng.annotations.Test;
import org.json.JSONArray;
import org.json.JSONObject;

public class Demo {



    @Test
    public void test(){
    doSearch();

    }



    public String doSearch() {
        String busqueda = "PS5";
        RestAssured.baseURI = "https://api.mercadolibre.com/sites/MLA";
        String body = RestAssured.given().when().get("/search?q=" + busqueda).then().extract().body().asString();


        String totalPartialResults = body.substring(body.indexOf("total"), body.indexOf(",\"primary_results"));
        int totalResults = Integer.parseInt(totalPartialResults.substring(7, totalPartialResults.length()));
        String totalResultsMsg = "El total de resultados para la busqueda es: " + totalResults;

        String limitPartialResults = body.substring(body.indexOf("limit"), body.indexOf("},\"results\":["));
        int limit = Integer.parseInt(limitPartialResults.substring(7, limitPartialResults.length()));
        String limitMsg = "El limite de paginado es: " + limit;
/*
        System.out.println(totalResultsMsg);
        System.out.println(limitMsg);

        if(totalResults>limit) {
            System.out.println("La cantidad de productos encontrados ("+totalResults+") supera el limite de paginado ("+limit+")");
        }else{
            System.out.println("La cantidad de productos encontrados ("+totalResults+") no supera el limite de paginado ("+limit+")");
        }*/
        //System.out.println(body);




        String getProductByIdAux = body.substring(body.indexOf("{\"id\":\""+"MLA1117228493"), body.length());
        String getProductById = getProductByIdAux.substring(0, body.indexOf("order_backend"));
        System.out.println(getProductById);



        return body;
    }



}
