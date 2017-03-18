/**
 * Created by Administrator on 2017/1/8 0008.
 */

package com.android.tool;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.PopupWindow;

import com.android.R;

public class AskSavePopMenu implements OnClickListener,OnTouchListener {

    private PopupWindow popupWindow;
    private Button btnCancel, btnSave, btnNoSave;
    private View mMenuView;
    private Activity mContext;
    private OnClickListener clickListener;

    public AskSavePopMenu(Activity context, OnClickListener clickListener) {
        LayoutInflater inflater = LayoutInflater.from(context);
        this.clickListener=clickListener;
        mContext=context;
        mMenuView = inflater.inflate(R.layout.pop_ask_save_menu, null);
        btnSave = (Button) mMenuView.findViewById(R.id.pop_save);
        btnNoSave = (Button) mMenuView.findViewById(R.id.pop_no_save);
        btnCancel = (Button) mMenuView.findViewById(R.id.pop_cancel);
        btnCancel.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        btnNoSave.setOnClickListener(this);
        popupWindow=new PopupWindow(mMenuView, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT,true);
        popupWindow.setAnimationStyle(R.style.popwin_anim_style);
        ColorDrawable dw = new ColorDrawable(context.getResources().getColor(R.color.ccc));
        popupWindow.setBackgroundDrawable(dw);
        mMenuView.setOnTouchListener(this);
    }

    /**
     * ��ʾ�˵�
     */
    public void show(){
        View rootView=((ViewGroup)mContext.findViewById(android.R.id.content)).getChildAt(0);
        popupWindow.showAtLocation(rootView, Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    @Override
    public void onClick(View view) {
        popupWindow.dismiss();
        switch (view.getId()) {
            case R.id.pop_cancel:
                break;
            default:
                clickListener.onClick(view);
                break;
        }
    }

    @Override
    public boolean onTouch(View arg0, MotionEvent event) {
        int height = mMenuView.findViewById(R.id.pop_layout).getTop();
        int y=(int) event.getY();
        if(event.getAction()==MotionEvent.ACTION_UP){
            if(y<height){
                popupWindow. dismiss();
            }
        }
        return true;
    }

}
