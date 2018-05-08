package com.example.harsanyiv.autoplaycust;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;


import android.os.Parcelable;


import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xw.repo.BubbleSeekBar;


public class GombView extends RelativeLayout {

    public ImageButton spin,x;
    public ImageView ii;
    public Dialog dialog;
    private boolean leOk=false;
    BubbleSeekBar sz;
    TextView gombTv;
    private boolean dismissed=false;
    Animation aa,aaa,b;
    Activity a;

    public GombView(Context context){
        super(context);
        init(context);

    }


    public GombView(Context context, AttributeSet set) {
        super(context,set);


        init(context);
    }

    public GombView(Context context,
                       AttributeSet attrs,
                       int defStyle) {
        super(context, attrs, defStyle);


        init(context);
    }


    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.gomb_view, this);

          aa = AnimationUtils.loadAnimation(context,R.anim.fordulat);
          aaa = AnimationUtils.loadAnimation(context,R.anim.beford);
          b = AnimationUtils.loadAnimation(context,R.anim.nagy);


        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog);
        dialog.setTitle("Title...");



    }



    @Override
    protected void onFinishInflate(){
        super.onFinishInflate();
        //ide jönnnek a belső osztályok, mikor már felfúvódott az elrendezés

       // listener(aa,aaa,b);
        allitgat(a);

        ii = findViewById(R.id.i3);
        ii.setRotation(-65);
        spin = findViewById(R.id.spin_gomb);





        spin.setOnClickListener(v -> {

            ii.startAnimation(aa);
            v.startAnimation(b);
            leOkez();

            Toast.makeText(getContext(),"SPUN",Toast.LENGTH_SHORT).show();
        });

        spin.setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:

                    longclick();


                    break;
                case MotionEvent.ACTION_UP:
                    if(!dialog.isShowing())
                    v.performClick();

                    break;

                default:
                    break;
            }
            return true;
        });



    }





    private void allitgat(Activity activity){

        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();

        wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(wlp);
    }



    @Override
    protected void dispatchSaveInstanceState(SparseArray<Parcelable> container) {
        // Makes sure that the state of the child views in the side
        // spinner are not saved since we handle the state in the
        // onSaveInstanceState.
        super.dispatchFreezeSelfOnly(container);
    }

    @Override
    protected void dispatchRestoreInstanceState(SparseArray<Parcelable> container) {
        // Makes sure that the state of the child views in the side
        // spinner are not restored since we handle the state in the
        // onSaveInstanceState.
        super.dispatchThawSelfOnly(container);
    }

    private void leOkez(){
        if(dialog.isShowing()&&(Integer)spin.getTag()==R.drawable.spin) {
            spin.setImageResource(R.drawable.stop);
            dialog.dismiss();
        }
    }


    private void longclick(){
        ii.startAnimation(aaa);
        dialog.create();
        x = dialog.findViewById(R.id.x);
        x.setOnClickListener(v -> {
            dialog.dismiss();
        });
        dialog.setCanceledOnTouchOutside(false);
        aaa.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                dialog.show();

                aaa.setFillAfter(true);
                spin.setImageResource(R.drawable.spin);
                spin.setTag(R.drawable.spin);
                gombTv = findViewById(R.id.gmbtv);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        dialog.setOnDismissListener(dialog -> {
                  dismissed=true;


        });

        if(dialog.isShowing()){

            sz = dialog.findViewById(R.id.sz);
            gombTv.setText(sz.getProgress());

            sz.setOnProgressChangedListener(new BubbleSeekBar.OnProgressChangedListener() {
                @Override
                public void onProgressChanged(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat) {

                }

                @Override
                public void getProgressOnActionUp(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat) {

                }

                @Override
                public void getProgressOnFinally(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat) {

                }
            });

        }


    }





}
