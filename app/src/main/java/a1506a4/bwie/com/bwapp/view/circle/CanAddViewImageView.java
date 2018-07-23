package a1506a4.bwie.com.bwapp.view.circle;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

import a1506a4.bwie.com.bwapp.R;

/**
 * Created by wxn on 2017/11/7.
 */

@SuppressLint("AppCompatCustomView")
public class CanAddViewImageView extends ImageView {

    public boolean delIsShow = false;

    private int marketRadius = 40;
    private Paint paint;



    public CanAddViewImageView(Context context) {
        this(context,null);
    }

    public CanAddViewImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CanAddViewImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint mPaint = paint;

        this.setAlpha(1f);
        if(delIsShow){
//            int cx = getWidth()-marketRadius;
//            int cy = marketRadius;
//            canvas.drawCircle(cx,cy,marketRadius,mPaint);

            Rect rect = new Rect(0,0,getWidth(),getHeight());
            Bitmap bitmap = BitmapFactory.decodeResource(
                    getContext().getResources(), R.drawable.ic_action_name);

            this.setAlpha(0.3f);
            canvas.drawBitmap(bitmap,rect,rect,paint);
        }




    }

    public void addDelMarket(boolean isShow){

        delIsShow = isShow;

        invalidate();

    }
}
