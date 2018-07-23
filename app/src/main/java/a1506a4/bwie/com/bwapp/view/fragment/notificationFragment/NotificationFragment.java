package a1506a4.bwie.com.bwapp.view.fragment.notificationFragment;

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
import a1506a4.bwie.com.bwapp.model.utils.RxBus;
import a1506a4.bwie.com.bwapp.presenter.IPresenter;
import a1506a4.bwie.com.bwapp.view.fragment.BaseFragment;
import rx.Observer;

/**
 * Created by Shadow on 2017/10/13.
 */

public class NotificationFragment extends BaseFragment {


    private static final String TAG = "TAG";
    private String[] title = {"发送通知", "新收通知", "我的通知"};
    private View view;
    private TabLayout tabLayout;
    private ViewPager viewpager;
    private ArrayList<Fragment> list;
    private SendNotifyFragment sendNotifyFragment;
    private ReceiveNotifyFragment receiveNotifyFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.notification_fragment, container, false);
        initView(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewpager.setAdapter(new MyAdapter(getChildFragmentManager()));
        viewpager.setOffscreenPageLimit(3);
        tabLayout.setupWithViewPager(viewpager);


        RxBus.getInstance().toObservable(String.class)
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {

                        if ("receive".equals(s)) {
                            viewpager.setCurrentItem(1);
                        }
                    }
                });
    }

    @Override
    protected void onRefresh() {
        super.onRefresh();

    }

    @Override
    protected IPresenter getPresebter() {
        return null;
    }

    @Override
    public void onResume() {
        super.onResume();

    }


    private void initView(View view) {
        tabLayout = (TabLayout) view.findViewById(R.id.notificationTablayout);
        viewpager = (ViewPager) view.findViewById(R.id.notificationViewPager);
        sendNotifyFragment = new SendNotifyFragment();
        receiveNotifyFragment = new ReceiveNotifyFragment();
        MyNotificationFragment myNotificationFragment = new MyNotificationFragment();
        //保存Fragment的集合
        list = new ArrayList<>();
        list.add(sendNotifyFragment);
        list.add(receiveNotifyFragment);
        list.add(myNotificationFragment);

        viewpager.setOffscreenPageLimit(10);
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
