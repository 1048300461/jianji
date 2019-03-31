package com.example.jianji.Utils;


import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.jianji.DB.Myuser;
import com.example.jianji.R;

import cn.bmob.v3.BmobUser;

/**
 * 封装了导航条的类均需继承该类
 */
public abstract class ParentWithNaviActivity extends BaseActivity {
    public ToolBarListener listener;
    public ImageButton title_left;
    public ImageButton title_right;
    public TextView title_center;

    /**导航栏标题:必填项
     * @return
     */
    protected abstract String title();

    /**导航栏左边：可以为string或图片资源id,非必须
     * @return
     */
    public Object left(){return null;}

    /**导航栏右边：可以为string或图片资源id,非必须
     * @return
     */
    public Object right(){return null;}

    /**设置导航栏监听,非必须
     * @return
     */
    public ToolBarListener setToolBarListener(){return null;}

    /**
     * 初始化导航条
     */
    public void initNaviView(){
        title_center = getView(R.id.title_center);
        title_right = getView(R.id.title_right);
        title_left = getView(R.id.title_left);
        setNaviListener(setToolBarListener());
        title_left.setOnClickListener(clickListener);
        title_right.setOnClickListener(clickListener);
        title_center.setText(title());
        refreshTop();
    }

    View.OnClickListener clickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.title_left:
                    if (listener == null) {
                        finish();
                    } else{
                        listener.clickLeft();
                    }
                    break;
                case R.id.title_right:
                    if (listener != null) {
                        listener.clickRight();
                    }
                    break;

                default:
                    break;
            }
        }
    };

    protected void refreshTop() {
        //不知用处
        //setLeftView(left()==null ? R.drawable.base_action_bar_back_bg_selector: left());
        setValue(R.id.title_right, right());
        this.title_center.setText(title());
    }

    private void setLeftView(Object obj){
        if(obj !=null && !obj.equals("")){
            title_left.setVisibility(View.VISIBLE);
            if(obj instanceof Integer){
                title_left.setImageResource(Integer.parseInt(obj.toString()));
            }else{
                //设置样式
                //title_left.setImageResource(R.drawable.base_action_bar_back_bg_selector);
            }
        }else{
            title_left.setVisibility(View.INVISIBLE);
        }
    }

    protected void setValue(int id,Object obj){
        if (obj != null && !obj.equals("")) {
            ((TextView) getView(id)).setText("");
            getView(id).setBackgroundDrawable(new BitmapDrawable());
            if (obj instanceof String) {
                ((TextView) getView(id)).setText(obj.toString());
            } else if (obj instanceof Integer) {
                getView(id).setBackgroundResource(Integer.parseInt(obj.toString()));
            }
        } else {
            ((TextView) getView(id)).setText("");
            getView(id).setBackgroundDrawable(new BitmapDrawable());
        }
    }

    protected void setNaviListener(ToolBarListener listener) {
        this.listener = listener;
    }

    /**不知干啥的*/
    @SuppressWarnings("unchecked")
    protected <T extends View> T getView(int id) {
        return (T) findViewById(id);
    }

    public boolean handleBackPressed(){
        return false;
    }

    /**获取Drawable资源
     * @param id
     * @return
     */
    public Drawable getDrawableResources(int id){
        return getResources().getDrawable(id);
    }

    public interface ToolBarListener {
        void clickLeft();

        void clickRight();
    }

    /**启动指定Activity
     * @param target
     * @param bundle
     */
    public void startActivity(Class<? extends Activity> target, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, target);
        if (bundle != null) {
            intent.putExtra(this.getPackageName(), bundle);
        }
        startActivity(intent);
    }

    public String getCurrentUid(){
        return BmobUser.getCurrentUser(Myuser.class).getObjectId();
        //return BmobUser.getCurrentUser(this, Myuser.class).getObjectId();
    }
}
