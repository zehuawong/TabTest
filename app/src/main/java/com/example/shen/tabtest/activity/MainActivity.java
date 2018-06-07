package com.example.shen.tabtest.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.shen.tabtest.R;
import com.example.shen.tabtest.adapter.MyFragmentPagerAdapter;
import com.example.shen.tabtest.fragment.EvaluationFragment;
import com.example.shen.tabtest.fragment.MerchantFragment;
import com.example.shen.tabtest.fragment.OrderFragment;

import java.util.ArrayList;


public class MainActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener{
    private static final String TAG="MainActivity-->";
    private ViewPager viewPager;
    private RadioGroup radioGroup;
    private RadioButton rbOrder,rbEvaluation,rbMerchant;
    private ArrayList<Fragment> alFragment;
    private View viewPagerIndicator; //指示器
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
        viewPagerIndicator=findViewById(R.id.viewpager_indicator);
        radioGroup.setOnCheckedChangeListener(this);

        /**
         * View的measure过程和Activity的生命周期方法不是同步进行的，
         * 能保证在Activity的onCreate,onStart,onResume方法执行时View已经测量完毕，所以不能获得正确宽高值。
         */
        viewPagerIndicator.post(new Runnable() {
            @Override
            public void run() {
                initvi();
            }
        });

    }
    //把指示器下划线View设置初始值
    private void initvi(){
        //int width=getResources().getDisplayMetrics().widthPixels/3; //这个方法是获取整个屏幕的宽度，不合理，应该获取父容器的宽
        ViewGroup parentView=(ViewGroup) viewPagerIndicator.getParent();

        int width=parentView.getMeasuredWidth()/3;

        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(width, ViewGroup.LayoutParams.MATCH_PARENT);
        viewPagerIndicator.setLayoutParams(params);

    }

    private void initViewPager(){
        OrderFragment orderFragment=new OrderFragment();
        EvaluationFragment evaluationFragment=new EvaluationFragment();
        MerchantFragment merchantFragment=new MerchantFragment();
        alFragment=new ArrayList<>();
        alFragment.add(orderFragment);
        alFragment.add(evaluationFragment);
        alFragment.add(merchantFragment);

        viewPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), alFragment));

        viewPager.setCurrentItem(0);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //"页面:" + position + "
                // offset偏移百分比" + positionOffset
                // pix像素" + positionOffsetPixels

                LinearLayout.LayoutParams params=(LinearLayout.LayoutParams)viewPagerIndicator.getLayoutParams();

                int width=params.width;
                Log.d(TAG, "onPageScrolled: width="+width);
                //设置下划线距离左边的位置长度
                int left = (int) ((positionOffset + position) * width);
                params.setMargins(left,0,0,0);

                viewPagerIndicator.setLayoutParams(params);

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
