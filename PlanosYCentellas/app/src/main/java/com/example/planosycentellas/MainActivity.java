package com.example.planosycentellas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.example.planosycentellas.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupDataBinding();
        setUpBottonNavigation();
    }

    private void setUpBottonNavigation(){
        NavigationUI.setupWithNavController(mBinding.bottomNavigation,
                Navigation.findNavController(this, R.id.nav_host_fragment));
    }

    private void setupDataBinding(){
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
    }
}
