package com.amir.serviceman.fragments.customer;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amir.serviceman.Model.CustomerProjectsList;
import com.amir.serviceman.Model.ProjectForProviderModel;
import com.amir.serviceman.R;
import com.amir.serviceman.activities.CustomerProjectDetails;
import com.amir.serviceman.activities.PostJob1stScreen;
import com.amir.serviceman.adapter.CustomerProjectsAdapter;
import com.amir.serviceman.core.BaseFragment;
import com.amir.serviceman.databinding.FragmentMyJobBinding;
import com.amir.serviceman.interfaces.OnAdapterItemClick;
import com.amir.serviceman.util.Dialogs;
import com.amir.serviceman.util.EndlessRecyclerViewScrollListener;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MyJob extends BaseFragment implements View.OnClickListener, OnAdapterItemClick {


    private FragmentMyJobBinding binding;
    private EndlessRecyclerViewScrollListener scrollListener;
    private int page = 1, totalInThisPage = 0;
    private ArrayList<CustomerProjectsList.Datum> projectList = new ArrayList<>();
    private boolean isLoading;
    private CustomerProjectsAdapter adapter;
    private int status;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_my_job, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loader = (ConstraintLayout) binding.loader.getRoot();

        setTabs();
        setSelectedTab();
        implementListener();
        emptyAdapter();
        if (checkInternetConnection()) {
            status = 1;
            getProjects(status);
        } else {
            showToast(getString(R.string.no_internet));
        }
    }

    private void emptyAdapter(){
        binding.rv.setLayoutManager(layoutManager);
        adapter = new CustomerProjectsAdapter(projectList,mContext,this);
        binding.rv.setAdapter(adapter);
    }

    private void setTabs() {
        setCustomTabView("Active");
        setCustomTabView("Closed");
    }

    private void implementListener(){
        binding.btnPostNewJob.setOnClickListener(this);

        // Retain an instance so that you can call `resetState()` for fresh searches
        scrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page1, int totalItemsCount, RecyclerView view) {
                if (totalInThisPage == 15 && !isLoading) {
                    page = page + 1;
                    if (checkInternetConnection()) {
                        getProjects(status);
                    } else {
                        showToast(getString(R.string.no_internet));
                    }
                }
            }
        };

        // Adds the scroll listener to RecyclerView
        binding.rv.addOnScrollListener(scrollListener);

    }

    private void getProjects(int status){
        showLoader();
        isLoading = true;
        String apiTOken;
        if (sp.getBoolean(SIGNUP)) {
            apiTOken = getUserModelFromSharedPreference(sp).getApiToken();
        } else {
            apiTOken = getLoginUserModelFromSharedPreference(sp).getApiToken();
        }

        call = api.getUserProjects(apiTOken,status,page);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                isLoading = false;
                try {
                    Type type = new TypeToken<CustomerProjectsList>() {
                    }.getType();
                    if (response.code() == 200) {
                        CustomerProjectsList data = gson.fromJson(response.body().string(), type);
                        if (data.getType().equals("success")) {
                            if (page == 1) {
                                scrollListener.resetState();
                                projectList.clear();
                            }
                            projectList.addAll(data.getData().getData());
                            adapter.notifyDataSetChanged();
                            totalInThisPage = data.getData().getData().size();
                        } else {
                            Dialogs.alertDialog(data.getMessage(), mContext);
                        }
                    } else {
                        Dialogs.alertDialog(getString(R.string.SERVER_ERROR_MSG), mContext);
                    }

                }
                catch (Exception ex){

                }

                hideLoader();

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                isLoading = false;

                hideLoader();
            }
        });

    }

    /**
     * setup custom tabs
     *
     * @param text text
     */
    private void setCustomTabView(String text) {
        LinearLayout customView = (LinearLayout) LayoutInflater.from(getActivity())
                .inflate(R.layout.item_custom_tab_listings_chat, null);

        ((TextView) customView.findViewById(R.id.tvText)).setText(text);

        binding.tabLayout.addTab(binding.tabLayout.newTab().setCustomView(customView));
    }

    private void setSelectedTab(){
        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        setVIsibility(View.VISIBLE);
                        status = 1;
                        checkInternet(status);
                        break;
                    case 1:
                        setVIsibility(View.GONE);
                        status = 2;
                        checkInternet(status);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void checkInternet(int status){
        if (checkInternetConnection()){
            page = 1;
            projectList.clear();
            getProjects(status);
        }
        else {
            showToast(getString(R.string.no_internet));
        }
    }
    private void setVIsibility(int view){
        binding.btnPostNewJob.setVisibility(view);
    }

    @Override
    public void onClick(View v) {
        if (v == binding.btnPostNewJob){
          startActivity(new Intent(mContext, PostJob1stScreen.class));
        }
    }


    @Override
    public void onClick(int position, boolean data, int type) {
        if (type == 1){
            startActivity(new Intent(mContext, CustomerProjectDetails.class)
                    .putExtra(FROM,MY_JOB)
                    .putExtra("projectId",projectList.get(position).getPId()));
        }
    }
}
