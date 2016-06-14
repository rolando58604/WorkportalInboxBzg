package com.rolandoamarillo.workportalinboxbizagi.managers;

import com.rolandoamarillo.workportalinboxbizagi.models.Task;
import com.rolandoamarillo.workportalinboxbizagi.remote.ApiInterface;
import com.squareup.otto.Bus;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TasksManager {

    Bus bus;
    ApiInterface apiInterface;

    private List<Task> taskList;

    public TasksManager(Bus bus, ApiInterface apiInterface) {
        this.bus = bus;
        this.apiInterface = apiInterface;
    }

    public void getTasks() {
        if (taskList == null) {
            Call call = apiInterface.getAllTasks();
            call.enqueue(new TasksCallback());
        } else {
            TasksEvent event = new TasksEvent();
            event.setSuccess(true);
            event.setResponse(taskList);
            bus.post(event);
        }
    }

    private class DefaultCallBack<T> implements Callback<T> {
        @Override
        public void onResponse(Call<T> call, Response<T> response) {
        }

        @Override
        public void onFailure(Call<T> call, Throwable t) {
        }
    }

    private class Event<T> {
        private T response;
        private boolean isSuccess;

        public T getResponse() {
            return response;
        }

        public void setResponse(T response) {
            this.response = response;
        }

        public boolean isSuccess() {
            return isSuccess;
        }

        public void setSuccess(boolean success) {
            isSuccess = success;
        }
    }

    public class TasksCallback<List> extends DefaultCallBack<java.util.List<Task>> {
        @Override
        public void onResponse(Call<java.util.List<Task>> call, Response<java.util.List<Task>> response) {
            super.onResponse(call, response);
            taskList = response.body();
            TasksEvent newEvent = new TasksEvent();
            newEvent.setSuccess(true);
            newEvent.setResponse(response.body());
            bus.post(newEvent);
        }

        @Override
        public void onFailure(Call<java.util.List<Task>> call, Throwable t) {
            super.onFailure(call, t);
            TasksEvent newEvent = new TasksEvent();
            newEvent.setSuccess(false);
            bus.post(newEvent);
        }
    }

    public class TasksEvent extends Event<List<Task>> {
    }
}
