package com.example.jianji.Activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

public class MyFragementPagerAdapter extends FragmentPagerAdapter {

    private final int PAGER_COUNT = 4;
    private MyFragmentNew myFragment1 = null;
    private MyFragmentFolder myFragment2 = null;
    private MyFragmentCollect myFragment3 = null;
    private MyFragmentMe myFragment4 = null;

    public MyFragementPagerAdapter(FragmentManager fm) {
        super(fm);
        myFragment1 = new MyFragmentNew();
        myFragment2 = new MyFragmentFolder();
        myFragment3 = new MyFragmentCollect();
        myFragment4 = new MyFragmentMe();
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = null;
        switch (i) {
            case UserMainActivity.PAGE_ONE:
                fragment = myFragment1;
                break;
            case UserMainActivity.PAGE_TWO:
                fragment = myFragment2;
                break;
            case UserMainActivity.PAGE_THREE:
                fragment = myFragment3;
                break;
            case UserMainActivity.PAGE_FOUR:
                fragment = myFragment4;
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return PAGER_COUNT;
    }

    @Override
    public Object instantiateItem(ViewGroup vg, int position) {
        return super.instantiateItem(vg, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        System.out.println("position Destory" + position);
        super.destroyItem(container, position, object);
    }
}
