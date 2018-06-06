package com.example.shen.tabtest.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.shen.tabtest.R;
import com.example.shen.tabtest.adapter.myFragmentPagerAdapter;
import com.example.shen.tabtest.fragment.EvaluationFragment;
import com.example.shen.tabtest.fragment.MerchantFragment;
import com.example.shen.tabtest.fragment.OrderFragment;

import java.util.ArrayList;


public class MainActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener{
    private ViewPager viewPager;
    private RadioGroup radioGroup;
    private RadioButton rbOrder,rbEvaluation,rbMerchant;
    private ArrayList<Fragment> alFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       //初始化界面组件
        initView();
        initViewPager();
    }

    private void initView(){
        viewPager=(ViewPager) findViewById(R.id.viewpager);
        radioGroup=(RadioGroup) findViewById(R.id.radiogroup);
        rbOrder=(RadioButton) findViewById(R.id.rb_order);
        rbEvaluation=(RadioButton) findViewById(R.id.rb_evaluation);
        rbMerchant=(RadioButton) findViewById(R.id.rb_merchant);
        radioGroup.setOnCheckedChangeListener(this);
    }

    private void initViewPager(){
        OrderFragment orderFragment=new OrderFragment();
        EvaluationFragment evaluationFragment=new EvaluationFragment();
        MerchantFragment merchantFragment=new MerchantFragment();
        alFragment=new ArrayList<>();
        alFragment.add(orderFragment);
        alFragment.add(evaluationFragment);
        alFragment.add(merchantFragment);

        viewPager.setAdapter(new myFragmentPagerAdapter(getSupportFragmentManager(), alFragment));

        viewPager.setCurrentItem(0);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        radioGroup.check(R.id.rb_order);
                        break;
                    case 1:
                        radioGroup.check(R.id.rb_evaluation);
                        break;
                    case 2:
                        radioGroup.check(R.id.rb_merchant);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.rb_order:
                viewPager.setCurrentItem(0,false);
                break;
            case R.id.rb_evaluation:
                viewPager.setCurrentItem(1,false);
                break;
            case R.id.rb_merchant:
                viewPager.setCurrentItem(2,false);
                break;
        }
    }
}
