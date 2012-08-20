package de.thomaskuster;

import android.content.Context;
import android.nfc.Tag;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;
import de.thomaskuster.listener.CollapseListener;
import de.thomaskuster.listener.ExpandListener;

import java.util.ArrayList;

/**
 * User: Thomas Kuster
 * Date: 20.08.12
 * Time: 12:51
 * Copyright: Thomas Kuster 2012
 */
public class ExpandableLayout extends RelativeLayout {

    private AnimationSet mAnimationSet;
    private boolean isClosed = true;
    private ExpandListener mExpandListener;
    private CollapseListener mCollapseListener;
    private ArrayList<View> mChildViews;
    private static final String TAG = "EXPANDABLE_LAYOUT";
    private float mMeasuredHeight = 0;

    public ExpandableLayout(Context context) {
        super(context);
    }

    public ExpandableLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ExpandableLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void init() {
        mChildViews = new ArrayList<View>();
        mExpandListener = new ExpandListener(this);
        mCollapseListener = new CollapseListener(this);
    }

    public void toggle() {
        TranslateAnimation anim = null;
        if (mAnimationSet == null) {
            mAnimationSet = new AnimationSet(false);
        }
        mAnimationSet.reset();

        if (!isClosed) {
            Log.i(TAG, "CLOSING");
            isClosed = true;
            anim = new TranslateAnimation(
                    0.0f,
                    0.0f,
                    mMeasuredHeight,
                    0.0f);
            anim.setAnimationListener(mExpandListener);
        } else {
            Log.i(TAG, "OPENING");
            isClosed = false;
            anim = new TranslateAnimation(
                    0.0f,
                    0.0f,
                    0.0f,
                    mMeasuredHeight);
            anim.setAnimationListener(mCollapseListener);
        }
        anim.setDuration(500);
        mAnimationSet.addAnimation(anim);
        setAnimation(anim);
        startAnimation(anim);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if(mMeasuredHeight == 0){
            mMeasuredHeight += getChildAt(1).getMeasuredHeight();
        }
    }
}
