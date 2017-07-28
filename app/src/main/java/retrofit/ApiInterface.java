package retrofit;

import bean.CategoryDataClass;
import bean.Login;
import bean.MaidDataClass;
import bean.Registration;
import bean.Splash;
import bean.Status;
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
    Call<Login> getRegistration(@Body String body);

    @Headers("Content-Type: application/json")
    @POST("userMobileDetails.php")
    Call<Splash> getDeviceDetails(@Body String body);

    @Headers("Content-Type: application/json")
    @POST("categoryDetails.php")
    Call<CategoryDataClass> getCategories();

    @Headers("Content-Type: application/json")
    @POST("maidList.php")
    Call<MaidDataClass> getMaidsList(@Body String body);

    @Headers("Content-Type: application/json")
    @POST("getFavourate.php")
    Call<Status> addFav(@Body String body);


}
