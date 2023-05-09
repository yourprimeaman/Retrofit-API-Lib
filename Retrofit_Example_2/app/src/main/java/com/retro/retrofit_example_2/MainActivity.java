package com.retro.retrofit_example_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.tv_result);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);


        private void getComments() {

            Call<List<Comment>> call = jsonPlaceHolderApi.getcomments(3);

            call.enqueue(new Callback<List<Comment>>() {
                @Override
                public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                    if (!response.isSuccessful()) {
                        textView.setText("Code"+response.code());
                        return;
                    }

                    //Populate Data
                    List<Comment> comments = response.body();

                    for (Comment comment : comments) {
                        String content = "";
                        content += "ID: ~ " +comment.getPostId()+ "\n";
                        content += "User ID: ~ " +comment.getId()+ "\n";
                        content += "Title: ~ " +comment.getName()+ "\n";
                        content += "Email: ~ " +comment.getEmail()+ "\n";
                        content += "Text: ~ " +comment.getText()+ "\n\n";

                        textView.append(content);
                    }
                }

                @Override
                public void onFailure(Call<List<Comment>> call, Throwable t) {
                    textView.setText(t.getMessage());
                }
            });
        }

    }
}