package app.example.com.x3;

import android.graphics.PointF;
import android.view.animation.Interpolator;

import java.util.List;

/**
 * Created by mnkj on 2017/11/10.
 */

public class CustomInterpolator implements Interpolator {
    public List<PointF> getPoints() {
        return Points;
    }

    public void setPoints(List<PointF> points) {
        Points = points;
    }

    private List<PointF> Points;

    @Override
    public float getInterpolation(float v) {
        if (Points != null) {
            return (float) getY(v, Points.get(0).y, Points.get(1).y, Points.get(2).y, Points.get(3).y);
        }
        return 0;
    }

    private static double getY(float t, float y1, float y2, float y3, float y4) {
        double value = Math.pow(1 - t, 3) * y1 + 3 * Math.pow((1 - t), 2) * t * y2 + 3 * Math.pow(t, 2) * (1 - t) * y3 + Math.pow(t, 3) * y4;
        return value;
    }
}
