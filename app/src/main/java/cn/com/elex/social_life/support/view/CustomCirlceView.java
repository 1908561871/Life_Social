package cn.com.elex.social_life.support.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import cn.com.elex.social_life.R;


/**
 * Created by zhangweibo on 2015/12/17.
 */
public class CustomCirlceView extends View{

    Paint mPaint;

    //圆的半径
    private int radius;

    private int ringSize;


    private int ringcolor;


    private int width;

    private int height;

    private int shadowSize=1;
    public CustomCirlceView(Context context) {
        super(context);
    }

    public CustomCirlceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray=context.obtainStyledAttributes(attrs, R.styleable.CustomCirlceView);
        radius=typedArray.getDimensionPixelOffset(R.styleable.CustomCirlceView_circleradius,10);
        ringSize=typedArray.getDimensionPixelOffset(R.styleable.CustomCirlceView_ringsize,0);
        ringcolor=typedArray.getColor(R.styleable.CustomCirlceView_ringcolor,Color.GREEN);
        typedArray.recycle();
        init();
    }

    public void init(){
        mPaint=new Paint();
        mPaint.setAntiAlias(true);
        if (ringSize!=0){
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setStrokeWidth(ringSize);
        }else{
            mPaint.setStyle(Paint.Style.FILL);
        }
        mPaint.setColor(ringcolor);
        mPaint.setShadowLayer(2,shadowSize,shadowSize,Color.GRAY);
        this.setLayerType(View.LAYER_TYPE_SOFTWARE, mPaint);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(width/2,height/2,radius,mPaint);
        super.onDraw(canvas);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        width=(radius+shadowSize)*2+ringSize+2;
        height=width;
        setMeasuredDimension(width,height);
    }
}
