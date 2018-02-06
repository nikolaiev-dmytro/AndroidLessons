package customview.lesson6;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Man on 02.02.2018.
 */

public class MyView extends View {
    Paint paint1 = new Paint();
    Rect[] rect = new Rect[4];
    PointF[] points=new PointF[4];
    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        paint1.setStyle(Paint.Style.FILL);
        paint1.setColor(Color.BLUE);
        canvas.drawRect(rect[1],paint1);
        paint1.setColor(Color.RED);
        canvas.drawRect(rect[2],paint1);
        paint1.setColor(Color.BLACK);

        for (int i = 0; i < points.length; i++) {
            canvas.drawText(String.valueOf(i+1),points[i].x,points[i].y,paint1);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getX() < getWidth() / 2 && event.getY() < getHeight() / 2) {
            Toast.makeText(getContext(), "1", Toast.LENGTH_SHORT).show();
        } else if (event.getX() > getWidth() / 2 && event.getY() < getHeight() / 2) {
            Toast.makeText(getContext(), "2", Toast.LENGTH_SHORT).show();
        } else if (event.getX() < getWidth() / 2 && event.getY() > getHeight() / 2) {
            Toast.makeText(getContext(), "3", Toast.LENGTH_SHORT).show();
        } else if (event.getX() > getWidth() / 2 && event.getY() > getHeight() / 2) {
            Toast.makeText(getContext(), "4", Toast.LENGTH_SHORT).show();
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        paint1.setTextSize(100);
        rect[0] = new Rect(left, top, getWidth() / 2, getHeight() / 2);
        rect[1] = new Rect(getWidth() / 2, top, right, getHeight() / 2);
        rect[2] = new Rect(left, getHeight() / 2, getWidth() / 2, bottom);
        rect[3] = new Rect(getWidth() / 2, getHeight() / 2, right, bottom);
        points[0]=new PointF(getWidth() / 4 - paint1.measureText("1") / 2, getHeight() / 4 + paint1.measureText("1") / 2);
        points[1]=new PointF(3 * getWidth() / 4 - paint1.measureText("2") / 2, getHeight() / 4 + paint1.measureText("2") / 2);
        points[2]=new PointF(getWidth() / 4 - paint1.measureText("3") / 2, 3 * getHeight() / 4 + paint1.measureText("3") / 2);
        points[3]=new PointF(3 * getWidth() / 4 - paint1.measureText("4") / 2, 3 * getHeight() / 4 + paint1.measureText("4") / 2);
    }
}
