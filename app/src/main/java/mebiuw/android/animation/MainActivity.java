package mebiuw.android.animation;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.DialogInterface;
import android.graphics.drawable.AnimationDrawable;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener {
    private Button button_alpha,button_scale,button_translate,button_rotate,button_frame,button_proa,button_prob;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /**
         * 绑定相关的按键
         */
        this.button_alpha=(Button) this.findViewById(R.id.button_alpha);
        this.button_scale=(Button) this.findViewById(R.id.button_scale);
        this.button_translate=(Button) this.findViewById(R.id.button_translate);
        this.button_rotate=(Button) this.findViewById(R.id.button_rotate);
        this.button_frame=(Button) this.findViewById(R.id.button_frame);
        this.button_proa=(Button) this.findViewById(R.id.button_proa);
        this.button_prob=(Button) this.findViewById(R.id.button_prob);
        this.img=(ImageView) this.findViewById(R.id.testImg);
        /**
         * 绑定动画执行
         */
        this.button_alpha.setOnClickListener(this);
        this.button_rotate.setOnClickListener(this);
        this.button_translate.setOnClickListener(this);
        this.button_scale.setOnClickListener(this);
        this.button_frame.setOnClickListener(this);
        this.button_proa.setOnClickListener(this);
        this.button_prob.setOnClickListener(this);

    }

    /**
     * 加载并启动动画，执行动画的关键
     * @param view 动画执行的对象，在这里全部都是应用到img，注意看onClick部分
     * @param type  动画的种类
     */
    private void  doAnimation(View view,int type){
        Animation animation = AnimationUtils.loadAnimation(this, type);
        view.startAnimation(animation);
    }

    /**
     * 根据不同的按钮，选择执行不同的方法作为一个参数，交由doAnimation执行
     * @param v
     */
    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.button_alpha){
            this.doAnimation(img,R.anim.mebiuw_anima_alpha);
        }
        else if(v.getId()==R.id.button_scale){
            this.doAnimation(img,R.anim.mebiuw_anima_scale);
        }
        else if(v.getId()==R.id.button_rotate){
            this.doAnimation(img,R.anim.mebiuw_anima_rotate);
        }
        else if(v.getId()==R.id.button_translate){
            this.doAnimation(img,R.anim.mebiuw_anima_translate);
        }
        else if(v.getId()==R.id.button_frame){
            try {
                //首先设置背景为我们设置的XML
                this.img=(ImageView) this.findViewById(R.id.testImg);
                this.img.setBackgroundResource(R.drawable.mebiuw_drawable_frame);
                //获得AnimationDrawable对象，并且开始触发
                AnimationDrawable animationDrawable = (AnimationDrawable) this.img.getBackground();
                animationDrawable.start();
                Log.i("Exp", "Finish");
            }
            catch (Exception e){
                Log.i("Exp","Error",e);
            }
        }
        else if(v.getId()==R.id.button_proa){
            //首先创建三个动画
            ObjectAnimator moveIn = ObjectAnimator.ofFloat(this.img, "translationX", -500f, 0f);
            moveIn.setRepeatCount(2);
            moveIn.setRepeatMode(ObjectAnimator.REVERSE);
            ObjectAnimator rotate = ObjectAnimator.ofFloat(this.img, "rotation", 0f, 360f);
            ObjectAnimator fadeInOut = ObjectAnimator.ofFloat(this.img, "alpha", 1f, 0f, 1f);
            //设定一个集合，并且设定各个动画的执行顺序
            AnimatorSet animSet = new AnimatorSet();
            animSet.play(rotate).with(fadeInOut).after(moveIn);
            animSet.setDuration(5000);
            animSet.start();
        }
        else if(v.getId()==R.id.button_prob){
                //默认效果
                ObjectAnimator moveInDefault = ObjectAnimator.ofFloat(this.img, "translationX", -500f, 0f);
                ObjectAnimator moveInAccDec = ObjectAnimator.ofFloat(this.img, "translationX", -500f, 0f);
                moveInAccDec.setInterpolator(new AccelerateDecelerateInterpolator());
                ObjectAnimator moveInAcc = ObjectAnimator.ofFloat(this.img, "translationX", -500f, 0f);
                moveInAcc.setInterpolator(new AccelerateInterpolator());
                ObjectAnimator moveInAos = ObjectAnimator.ofFloat(this.img, "translationX", -500f, 0f);
                moveInAos.setInterpolator(new AnticipateOvershootInterpolator());
                ObjectAnimator moveInB = ObjectAnimator.ofFloat(this.img, "translationX", -500f, 0f);
                moveInB.setInterpolator(new BounceInterpolator());
                ObjectAnimator moveInC = ObjectAnimator.ofFloat(this.img, "translationX", -500f, 0f);
                moveInC.setInterpolator(new CycleInterpolator(1));
                ObjectAnimator moveInL = ObjectAnimator.ofFloat(this.img, "translationX", -500f, 0f);
                moveInL.setInterpolator(new LinearInterpolator());

                //设定一个集合，并且设定各个动画的执行顺序
                AnimatorSet animSet = new AnimatorSet();
                animSet.play(moveInDefault).after(moveInAccDec).after(moveInAcc).after(moveInAos).after(moveInB).after(moveInC).after(moveInL);
                animSet.setDuration(1000);
                animSet.start();


        }
    }
}
