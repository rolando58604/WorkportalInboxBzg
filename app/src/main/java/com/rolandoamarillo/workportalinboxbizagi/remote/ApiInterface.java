package com.rolandoamarillo.workportalinboxbizagi.remote;

import com.rolandoamarillo.workportalinboxbizagi.models.Task;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("/vacations")
    Call<List<Task>> getAllTasks();

}
