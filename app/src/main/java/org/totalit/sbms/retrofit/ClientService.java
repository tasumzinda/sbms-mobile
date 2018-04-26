package org.totalit.sbms.retrofit;

import org.totalit.sbms.domain.Client;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ClientService {

        @POST("/clients/saveClient")
        Call<Client> createClient(@Body Client client);

        /*
        Client client = new Client(1, "my new client");
Call<Client > call = clientService.createClient(client);
call.enqueue(new Callback<Client >() {});
         */
        /**********Result
         {
    "id": 1,
    "text": "my task title"
       }
         ********/
}
