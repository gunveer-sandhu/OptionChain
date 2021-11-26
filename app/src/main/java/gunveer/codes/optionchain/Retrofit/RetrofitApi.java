package gunveer.codes.optionchain.Retrofit;

import gunveer.codes.optionchain.RequestEntity;
import gunveer.codes.optionchain.ResponseEntity;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RetrofitApi {

//    @Headers({"User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:93.0) Gecko/20100101 Firefox/93.0",
//            "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8,application/json",
//            "Accept-Encoding: gzip, deflate, br",
//            "Accept-Language: en-US,en;q=0.5",
//            "DNT: 1",
//            "Host: www.nseindia.com",
//            "Referer: https://www.google.com/",
//            "Sec-Fetch-Dest: document",
//            "Sec-Fetch-Mode: navigate",
//            "Sec-Fetch-Site: cross-site",
//            "Upgrade-Insecure-Requests: 1",
//            "Cookie: ak_bmsc=ACEB5689B9CE0C0FAD1633D9C76CBBC6~000000000000000000000000000000~YAAQr4gsMYPMBlV8AQAA7gHqsQ09XFIPeqnka6L0SsY0OI1VR689yZ525OKHdqjdjhbfZIdPbHTGvoNk+qbJv43sXjxE3rmim7SiR1J9/4CgISZemD0skFpnXwV7/MKPk4mkoheZ08GVQTeChypjKRbUDyZmOgsK3w7KocyLYpU6KyVV6ttVS+em+NrSXyFhhpbdl6lRQ2UWQoPGd8xi3Fyea/eeghq7fm9pFn41Pqw1F3fxU5UMXS/onX5MnHe85Sp53S9ZzAnpjtqIq171vKdo74v7XWVSCCwvJQJwkm8J8PzmbSCcb6PMdCSGXwxqYpFcyeXPZ+Yf+ruW+d6PitljFpmXKB3v3aPHZ2e9mzPxDU3MfDUcKajscEmy7A=="})
    @GET("option-chain-indices")
    Call<ResponseEntity> getResponse(@Query(value = "symbol") String symbol);

}
