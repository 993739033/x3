package app.example.com.x3;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mnkj on 2017/11/8.
 */

public class ax3_bx3_cx_d extends View {
    private List<PointF> points;
    private int radius = 40;//点的半径
    private Path path;//用于绘制函数
    private Path path1;//用于绘制点
    private Paint paint;
    private int[] colors = {Color.RED, Color.YELLOW, Color.BLUE, Color.CYAN};//点的颜色
    private int line1 = 260, line2 = 400;
    private int index;
    String fx, fy;
    private int windowHeight;//屏幕高度
    private int windowWidth;//屏幕宽度

    public List<PointF> getPoints() {
        List<PointF> pointFs = new ArrayList<>();
        float D_value_y = points.get(0).y - points.get(3).y;
        pointFs.add(new PointF(0, 0));
        pointFs.add(new PointF(0, 1-(points.get(1).y - points.get(3).y) / D_value_y));
        pointFs.add(new PointF(0, 1-(points.get(2).y - points.get(3).y) / D_value_y));
        pointFs.add(new PointF(0, 1));
        return pointFs;
    }

    public ax3_bx3_cx_d(Context context) {
        super(context);
        initView(context);
    }

    public ax3_bx3_cx_d(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ax3_bx3_cx_d(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }


    private void initView(Context context) {
        path = new Path();
        path1 = new Path();
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(4);
        points = new ArrayList<>();
        PointF point1 = new PointF(100, 100);
        PointF point2 = new PointF(400, 200);
        PointF point3 = new PointF(500, 500);
        PointF point4 = new PointF(700, 100);
        points.add(point1);
        points.add(point2);
        points.add(point3);
        points.add(point4);
        windowHeight = ((Activity) context).getWindowManager().getDefaultDisplay().getHeight();
        windowWidth = ((Activity) context).getWindowManager().getDefaultDisplay().getWidth();
    }

    private void initFx() {
        float D_value_x = points.get(3).x - points.get(0).x;
        float D_value_y = points.get(0).y - points.get(3).y;
        fx = "(1-t)^3*" + "0" + "+" + "3t^2(1-t)*" + points.get(1).x / D_value_x +
                "+" + ("3t^2(1-t)*") + points.get(2).x / D_value_x + "+" + "t^3*" + "1";
        fy = "(1-t)^3*" + "0" + "+" + "3t^2(1-t)*" + points.get(1).y / D_value_y +
                "+" + ("3t^2(1-t)*") + points.get(2).y / D_value_y + "+" + "t^3*" + "1";
        postInvalidate();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        initFx();
        paint.setTextSize(40f);
        canvas.drawText(fx, 20, line1, paint);
        canvas.drawText("B(x)=",40, line1-60, paint);
        canvas.drawText(fy, 20, line2, paint);
        canvas.drawText("B(y)=", 40, line2-60, paint);
        path.reset();
        path.moveTo(points.get(0).x, points.get(0).y + windowHeight / 3);
        path.cubicTo(points.get(1).x, points.get(1).y + windowHeight / 3, points.get(2).x, points.get(2).y + windowHeight / 3,
                points.get(3).x, points.get(3).y + windowHeight / 3);
        paint.setStyle(Paint.Style.STROKE);
        for (int i = 0; i < 4; i++) {
            paint.setColor(colors[i]);
            canvas.drawCircle(points.get(i).x, points.get(i).y + windowHeight / 3, radius, paint);
            paint.setColor(colors[i]);
            canvas.drawText(i + "", points.get(i).x - radius / 4, points.get(i).y + windowHeight / 3 + radius / 4, paint);
        }
        paint.setColor(Color.BLACK);
        canvas.drawPath(path, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                for (int i = 0; i < 4; i++) {
                    if (event.getX() > (points.get(i).x - radius * 2)
                            && event.getX() < points.get(i).x + radius * 2
                            && event.getY() > points.get(i).y + windowHeight / 3 - radius * 2
                            && event.getY() < points.get(i).y + windowHeight / 3 + radius * 2
                            ) {
                        index = i;
                        return true;
                    }
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (event.getX() < 100 || event.getX() > windowWidth - 100 || event.getY() < 100 || event.getY() > windowHeight - 100)
                    return false;
                points.get(index).x = (int) event.getX();
                points.get(index).y = (int) event.getY() - windowHeight / 3;
                postInvalidate();
                return true;
        }
        return false;
    }
}
