import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Map;

public class StringReversalLambdaHandler implements RequestHandler<Map<String, String>, String> {
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public String handleRequest(Map<String, String> event, Context context) {
        LambdaLogger logger = context.getLogger();
        String response = new String("200 OK");
        // log execution details
        logger.log("ENVIRONMENT VARIABLES: " + gson.toJson(System.getenv()));
        logger.log("CONTEXT: " + gson.toJson(context));
        // process event
        logger.log("EVENT: " + gson.toJson(event));
        logger.log("EVENT TYPE: " + event.getClass().toString());

        String reversedStr1 = reverseString(event.get("message1"));
        String reversedStr2 = reverseString(event.get("message2"));

        response = String.format("{message1: %s, message2: %s}", reversedStr1, reversedStr2);

        return response;
    }

    /**
     * Reverses a string s and returns the result.
     *
     * @param s string to be reversed
     * @return reversed string
     */
    private static String reverseString(String s) {
        return new StringBuilder(s).reverse().toString();
    }
}
