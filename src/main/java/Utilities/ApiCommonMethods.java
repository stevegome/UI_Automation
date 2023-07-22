package Utilities;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

/**
 * Write common api methods in this class using REST Assured
 */

public class ApiCommonMethods {

    public static String JWT = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjljNGMzMjU2LWVkZjktNDhjYi1hMz    BlLTlhNDA2ZWJmMmI3YSIsImFwcElkIjoiOWQ5NjY1Y2YtN2U4My00ODdiLWI4MTYtZWU5NDg4M2JmZjVmIiwiaWF0IjoxNjg0NTE3NTg2fQ.hs1RnzazBo97DnHrXLiLgCpwXe2aGLIoqbaznpxSr2o";

    /**
     *
     * @return  This method will return the description of the first alarm
     */
    public static String getDescriptionOfFirstAlarm() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/json");
        headers.put("Authorization", "Bearer " + JWT);
        Response response =
                given().headers(headers).when().get("http://localhost:7075/v1/alarms").then().assertThat()
                        .statusCode(200).log().all().extract().response();
        //JsonPath response = new JsonPath(apiResponseGetAlarms);
        JsonPath jsonPath = response.jsonPath();
        String value = jsonPath.get("description[0]");
        return value;
    }


}
