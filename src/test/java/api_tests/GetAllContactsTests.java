package api_tests;

import dto.Contact;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.BaseApi;
import utils.ContactFactory;

import java.io.IOException;

public class GetAllContactsTests extends AddNewContact implements BaseApi {
    @Test
    public void GetContactsPositiveTests (){
        Contact contact = ContactFactory.positiveContact();
        contact.setName("Sergey");
        RequestBody requestBody = RequestBody.create(GSON.toJson(contact), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL + GET)
                .addHeader(AUTH, token.getToken())
                .get()
                .build();
        try(Response response = OK_HTTP_CLIENT.newCall(request).execute()){
            System.out.println(response.code());
        }catch (IOException e){
            e.printStackTrace();
        }
        System.out.println(contact.getName());
        Assert.assertEquals(contact.getName(), "Sergey");


    }
}
