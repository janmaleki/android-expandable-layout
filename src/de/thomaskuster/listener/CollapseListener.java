package de.thomaskuster.listener;

import android.R;
import android.nfc.Tag;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import de.thomaskuster.ExpandableLayout;

/**
 * User: Thomas Kuster
 * Date: 20.08.12
 * Time: 12:51
 * Copyright: Thomas Kuster 2012
 */
public class CollapseListener implements Animation.AnimationListener {

    private ExpandableLayout mContext;

    public CollapseListener(ExpandableLayout layout){
        setOnCollapseAnimationListener(layout);
    }

    public void setOnCollapseAnimationListener(ExpandableLayout context) {
        mContext = context;
    }

    public void removeCollapseAnimationListener(){
        mContext = null;
    }

    @Override
    public void onAnimationStart(Animation animation) {
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        mContext.clearAnimation();
        //Hide Elements inside layout when collapsed
        int childCount = mContext.getChildCount();
        for(int i = 1; i < childCount; i++){
            View child = mContext.getChildAt(i);
            child.setVisibility(View.GONE);
        }
    }

    @Override
    public void onAnimationRepeat(Animation animation) {
    }
}
