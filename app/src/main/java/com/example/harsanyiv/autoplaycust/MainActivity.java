package com.example.harsanyiv.autoplaycust;

import android.app.Activity;
import android.app.Dialog;
import android.content.ClipData;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Display;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ramotion.fluidslider.FluidSlider;

import kotlin.Unit;

public class MainActivity extends Activity {

    private ImageButton spin;
    private ImageView ii;


    final Context context = this;
    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GombView g = findViewById(R.id.gv);


    }

    private void allitgat(Activity activity){

        Window window = dialog.getWindow();
        WindowManager wm = getWindowManager();
        Display d = wm.getDefaultDisplay();
        WindowManager.LayoutParams wlp = window.getAttributes();

        if(activity.getResources().getConfiguration().orientation==Configuration.ORIENTATION_PORTRAIT) {
            //wlp.gravity = Gravity.VERTICAL_GRAVITY_MASK;
            window.setGravity(Gravity.END);

        }
        else {
            wlp.gravity = Gravity.DISPLAY_CLIP_VERTICAL;
        }

        wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;

        window.setAttributes(wlp);
    }

    public void listener(final Animation aa,final Animation aaa,final Animation b) {

      spin = findViewById(R.id.spin_gomb);



        spin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(dialog.isShowing())
                ii.setRotation(-65);
                ii.startAnimation(aa);
                v.startAnimation(b);
               /* Toast.makeText(getApplicationContext(),
                        "EZ EGY SIMA SPIN VOLT", Toast.LENGTH_SHORT).show();*/

            }

        });

        spin.setOnLongClickListener(new HosszKatt(aaa));

    }


    private void longclick(final Animation aaa){
        ii.startAnimation(aaa);

        aaa.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
               aaa.setFillAfter(true);
                dialog.show();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });




    }

    private final class HosszKatt implements View.OnLongClickListener{
        Animation aaa;
       HosszKatt(final Animation aaa){
            this.aaa=aaa;
        }
        @Override
        public boolean onLongClick(View v) {
            //ClipData data = ClipData.newPlainText(" "," ");
            //View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);

           // v.startDrag(data,shadowBuilder,v,0);
            //v.setVisibility(View.INVISIBLE);
            longclick(aaa);
            return true;
        }
    }




    private class HZ implements View.OnDragListener{
        Drawable blps = getResources().getDrawable(R.drawable.blps);
        Drawable gmbkp = getResources().getDrawable(R.drawable.image823);
        @Override
        public boolean onDrag(View v, DragEvent event) {
            int action = event.getAction();
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    // do nothing
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    v.setBackgroundDrawable(blps);
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    v.setBackgroundDrawable(gmbkp);
                    break;
                case DragEvent.ACTION_DROP:
                    // Dropped, reassign View to ViewGroup


                   /* View view = (View) event.getLocalState();
                    ViewGroup owner = (ViewGroup) view.getParent();
                    owner.removeView(view);
                    LinearLayout container = (LinearLayout) v;
                    container.addView(view);
                    view.setVisibility(View.VISIBLE);*/
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    v.setBackgroundDrawable(gmbkp);
                default:
                    break;
            }
            return true;
        }
    }



}
