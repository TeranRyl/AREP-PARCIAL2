package co.edu.escuelaing.app;
import java.util.ArrayList;

import static spark.Spark.*;
public class Collatz {

    public static void main(String[] args){
        staticFiles.location("public");
        port(getPort());
        get("hello", (req,res) -> "Hello Docker!");

        get("collatz", (req,res) -> {
           String value = req.queryParams("value");
           res.type("application/json");
           return valCollatz(value);
        });
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }

    private static String valCollatz(String value){
        String response;
        int val = Integer.parseInt(value);
        ArrayList<Integer> seq = new ArrayList<>();
        seq.add(val);
        while (val > 1){
            if(val % 2 == 0){
                val = val / 2;
            } else {
                val = (3*val) + 1;
            }
            seq.add(val);
        }
        response = seq.toString().replace("[", "").replace("]", "").replace(", ", "->");
        response = String.format("{\"operation\": \"collatzsequence\", \"input\": \"%s\", \"output\": \"%s\"}", value, response);
        return response;

    }

}