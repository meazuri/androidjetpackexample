package com.san.androidjetpack;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import javax.inject.Inject;

public class UserProfileViewModel  extends ViewModel {

    private LiveData<User> user;
    private UserRepository userRepo;

// Instructs Dagger 2 to provide the UserRepository parameter.
    @Inject
    public UserProfileViewModel(UserRepository userRepo){
        this.userRepo = userRepo;
    }


    public void init(int userId) {
        if(this.user != null){
            return;
        }
        user = userRepo.getUser(userId);
    }
    public LiveData<User> getUser() {
        return user;
    }
}
