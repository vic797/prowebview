/*
Copyright 2017 Victor Campos

Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/
package vcm.github.webkit.proview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import java.util.Timer;
import java.util.TimerTask;

/**
 * A better zoom and navigation controls for {@link ProWebView}
 */

public class ProWebViewControls extends FrameLayout {

    private ImageButton zoomIn, zoomOut, goTop, goBottom;
    private ProWebView proWebView;
    private ControlMode mode;
    private int timeout = 3000;

    private Timer timer;

    /**
     * Control mode
     */
    public enum ControlMode {
        MODE_ZOOM,
        MODE_NAVIGATION,
        MODE_DUAL
    }

    public ProWebViewControls(@NonNull Context context) {
        this(context, null);
    }

    public ProWebViewControls(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProWebViewControls(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = inflate(context, R.layout.virtual_buttons, this);
        zoomIn = (ImageButton) view.findViewById(R.id.btn_zoom_in);
        zoomOut = (ImageButton) view.findViewById(R.id.btn_zoom_out);
        goBottom = (ImageButton) view.findViewById(R.id.btn_bottom);
        goTop = (ImageButton) view.findViewById(R.id.btn_top);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ProWebViewControls);
        try {
            int mode = array.getInt(R.styleable.ProWebViewControls_controlMode, 2);
            switch (mode) {
                case 0:
                    setMode(ControlMode.MODE_ZOOM);
                    break;
                case 1:
                    setMode(ControlMode.MODE_NAVIGATION);
                    break;
                case 2:
                    setMode(ControlMode.MODE_DUAL);
                    break;
            }
            timeout = array.getInteger(R.styleable.ProWebViewControls_hideTimeout, 3000);
        } finally {
            array.recycle();
        }
        resetThread();
        setListeners();
    }

    /**
     * Initialize all the listeners
     */
    private void setListeners() {
        zoomIn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (proWebView!=null)
                    proWebView.zoomIn();
                resetThread();
            }
        });
        zoomOut.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (proWebView!=null)
                    proWebView.zoomOut();
                resetThread();
            }
        });
        goTop.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (proWebView!=null)
                    proWebView.pageUp(false);
                resetThread();
            }
        });
        goBottom.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (proWebView!=null)
                    proWebView.pageDown(false);
                resetThread();
            }
        });
        goTop.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (proWebView!=null)
                    proWebView.pageUp(true);
                resetThread();
                return true;
            }
        });
        goBottom.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (proWebView!=null)
                    proWebView.pageDown(true);
                resetThread();
                return true;
            }
        });
    }

    /**
     * Set the control mode
     */
    public void setMode(ControlMode mode) {
        this.mode = mode;
        switch (mode) {
            case MODE_NAVIGATION:
                zoomOut.setVisibility(GONE);
                zoomIn.setVisibility(GONE);
                goTop.setVisibility(VISIBLE);
                goBottom.setVisibility(VISIBLE);
                break;
            case MODE_ZOOM:
                zoomOut.setVisibility(VISIBLE);
                zoomIn.setVisibility(VISIBLE);
                goTop.setVisibility(GONE);
                goBottom.setVisibility(GONE);
                break;
            case MODE_DUAL:
                zoomOut.setVisibility(VISIBLE);
                zoomIn.setVisibility(VISIBLE);
                goTop.setVisibility(VISIBLE);
                goBottom.setVisibility(VISIBLE);
                break;
        }
    }

    /**
     * Get the control mode
     */
    public ControlMode getMode() {
        return mode;
    }

    /**
     * Connect the ProWebViewControl to a {@link ProWebView}
     */
    public void setProWebView(@NonNull ProWebView webView) {
        this.proWebView = webView;
        this.proWebView.addOnTouchListener(new OnTouchListener(){

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (!isVisible())
                    show();
                return true;
            }
        });
    }

    /**
     * Shows the control with an animation
     */
    public void show() {
        clearAnimation();
        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                stopThread();
                setVisibility(VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                resetThread();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        setAnimation(animation);
        animation.start();
    }

    /**
     * Hides the control with an animation
     */
    public void hide() {
        clearAnimation();
        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.fade_out);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                setVisibility(GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        setAnimation(animation);
        animation.start();
    }

    /**
     * Toggle the control with an animation
     */
    public void toggle() {
        if (isVisible())
            hide();
        else
            show();
    }

    /**
     * Get the hide timeout
     */
    public int getTimeout() {
        return timeout;
    }

    /**
     * Set the timeout
     */
    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    /**
     * Check if the controls are visible
     */
    public boolean isVisible() {
        return getVisibility() == VISIBLE;
    }

    /**
     * Stop the "hide" thread
     */
    private void stopThread() {
        if (timer!=null)
            timer.cancel();
    }

    /**
     * Reset the "hide" thread
     */
    private void resetThread() {
        stopThread();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                ProWebViewControls.this.post(new Runnable() {
                    @Override
                    public void run() {
                        if (isVisible())
                            hide();
                    }
                });
            }
        }, timeout);
    }
}
