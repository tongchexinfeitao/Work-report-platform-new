package a1506a4.bwie.com.bwapp.view.circle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 作者: 赵虔
 * 时间: 2017/10/22
 * 类作用:
 */

public class MyCircleView extends View {


    public MyCircleView(Context context) {
        super(context);
    }

    public MyCircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int radius = (getWidth() / 2);

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //画外层的大圆圈
        paint.setColor(Color.parseColor("#B2CDE6"));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(20);
        canvas.drawCircle(radius, radius, radius - 10, paint);

        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(Color.parseColor("#005BAC"));
        canvas.drawCircle(radius, radius, radius - 30, paint);

    }
}
