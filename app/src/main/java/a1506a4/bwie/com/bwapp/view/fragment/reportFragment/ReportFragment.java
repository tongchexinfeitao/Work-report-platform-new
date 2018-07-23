package a1506a4.bwie.com.bwapp.view.fragment.reportFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import a1506a4.bwie.com.bwapp.R;


/**
 * 作者: 赵虔
 * 时间: 2017/10/21
 * 类作用:汇报Fragment总页面
 */

public class ReportFragment extends Fragment {
    private String[] title = {"工作汇报", "新收汇报", "我的汇报"};
    private View view;
    private TabLayout tabLayout;
    private ViewPager viewpager;
    private ArrayList<Fragment> list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.report_fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        viewpager.setAdapter(new MyAdapter(getChildFragmentManager()));
        viewpager.setOffscreenPageLimit(5);
        tabLayout.setupWithViewPager(viewpager);
    }

    private void initView() {
        tabLayout = (TabLayout) view.findViewById(R.id.ReportTabLayout);
        viewpager = (ViewPager) view.findViewById(R.id.ReportViewPager);
        //保存Fragment的集合
        list = new ArrayList<>();
        list.add(new JobReportFragment());
        list.add(new ReportReceiveFragment());
        list.add(new ReportMyFragment());
    }

    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position, object);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return title[position];
        }
    }


}
