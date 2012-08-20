package de.thomaskuster.listener;

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
public class ExpandListener implements Animation.AnimationListener {

    private ExpandableLayout mContext;

    public ExpandListener(ExpandableLayout layout){
        setOnExpandAnimationListener(layout);
    }

    public void setOnExpandAnimationListener(ExpandableLayout context) {
        mContext = context;
    }

    public void removeExpandAnimationListener(){
        mContext = null;
    }

    @Override
    public void onAnimationStart(Animation animation) {
        int childCount = mContext.getChildCount();
        for(int i = 1; i < childCount; i++){
            View child = mContext.getChildAt(i);
            child.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        mContext.clearAnimation();
    }

    @Override
    public void onAnimationRepeat(Animation animation) {
    }
}
