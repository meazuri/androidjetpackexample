package com.san.androidjetpack;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import javax.inject.Singleton;

@Singleton
public class UserRepository {

    private Webservice webservice;

    // Simple in-memory cache. Details omitted for brevity.
    private UserCache userCache;

    public LiveData<User> getUser(int userId) {
        LiveData<User> cached = userCache.get(userId);
        if (cached != null) {
            return cached;
        }

        final MutableLiveData<User> data = new MutableLiveData<>();
        userCache.put(userId, data);

        // This implementation is still suboptimal but better than before.
        // A complete implementation also handles error cases.
        webservice.getUser(userId).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                data.setValue(response.body());
            }
        });
        return data;
    }
}
