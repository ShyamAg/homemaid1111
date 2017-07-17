package retrofit;

import bean.Login;
import bean.Registration;
import bean.Splash;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiInterface {

    @Headers("Content-Type: application/json")
    @POST("login.php")
    Call<Login> loginValidation(@Body String body);

    @Headers("Content-Type: application/json")
    @POST("registration.php")
    Call<Registration> getRegistration(@Body String body);

    @Headers("Content-Type: application/json")
    @POST("userMobileDetails.php")
    Call<Splash> getDeviceDetails(@Body String body);


}
