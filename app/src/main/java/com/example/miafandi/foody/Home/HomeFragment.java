package com.example.miafandi.foody.Home;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.miafandi.foody.Home._sliders.FragmentSlider;
import com.example.miafandi.foody.Home._sliders.SliderIndicator;
import com.example.miafandi.foody.Home._sliders.SliderPagerAdapter;
import com.example.miafandi.foody.Home._sliders.SliderView;
import com.example.miafandi.foody.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    AppBarLayout Appbar;
    CollapsingToolbarLayout CoolToolbar;
    ImageView img;
    Toolbar toolbar;
    Boolean ExpandedActionBar = true;

    //grid
    ViewPager viewPager;
    RecyclerView mRecycleView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;
    ViewPager viewPagerIn;
    View rootView;
    //Tab
    private static final int ACTIVITY_NUM = 0;
    private Context mContext = getActivity(); //gimana nih

    //sliderImage
    private SliderPagerAdapter mAdapterSlider;
    private SliderIndicator mIndicator;

    private SliderView sliderView;
    private LinearLayout mLinearLayout;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        toolbar = (Toolbar) rootView.findViewById(R.id.MyToolbar);
        Appbar = (AppBarLayout) rootView.findViewById(R.id.MyAppbar);
        CoolToolbar = (CollapsingToolbarLayout) rootView.findViewById(R.id.MyCollapseToolbar);
        img = (ImageView) rootView.findViewById(R.id.imageHeader);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        Appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if(Math.abs(verticalOffset) > 100){
                    ExpandedActionBar = false;
                    CoolToolbar.setTitle("Foody");
                }else{
                    ExpandedActionBar = true;
                    CoolToolbar.setTitle(" ");
                }
            }
        });

        ViewPager mViewPager = (ViewPager) rootView.findViewById(R.id.viewPagerHome);
        ViewPagerAdapter mViewPagerAdapter = new ViewPagerAdapter(this.getActivity().getSupportFragmentManager());
        mViewPagerAdapter.addFragment(BerandaFragment.newInstance(),"Beranda");
        mViewPagerAdapter.addFragment(VotingFragment.newInstance(),"Voting");
        mViewPagerAdapter.addFragment(CateringFragment.newInstance(),"Catering");
        mViewPager.setAdapter(mViewPagerAdapter);

        mViewPager.setCurrentItem(1);

        TabLayout tabLayout = (TabLayout) rootView.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        //slider
        sliderView = (SliderView) rootView.findViewById(R.id.sliderView);
        mLinearLayout = (LinearLayout) rootView.findViewById(R.id.pagesContainer);
        setupSlider();

        return rootView;
    }

    private void setupSlider() {
        sliderView.setDurationScroll(800);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(FragmentSlider.newInstance("http://www.menucool.com/slider/prod/image-slider-1.jpg"));
        fragments.add(FragmentSlider.newInstance("http://www.menucool.com/slider/prod/image-slider-2.jpg"));
        fragments.add(FragmentSlider.newInstance("http://www.menucool.com/slider/prod/image-slider-3.jpg"));
        fragments.add(FragmentSlider.newInstance("http://www.menucool.com/slider/prod/image-slider-4.jpg"));
//        fragments.add(FragmentSlider.newInstance(R.drawable.borderbackground));

        mAdapterSlider = new SliderPagerAdapter(getFragmentManager(), fragments);
        sliderView.setAdapter(mAdapterSlider);
        mIndicator = new SliderIndicator(getActivity(), mLinearLayout, sliderView, R.drawable.indicator_circle);
        mIndicator.setPageCount(fragments.size());
        mIndicator.show();
    }
}
