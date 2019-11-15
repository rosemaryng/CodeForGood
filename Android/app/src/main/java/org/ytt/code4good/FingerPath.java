package org.ytt.code4good;

import android.graphics.Color;
import android.graphics.Path;

public class FingerPath {
    public int color;
//    public boolean emboss;
//    public boolean blur;
    public int strokeWidth;
    public Path path;

    public FingerPath(Path path) {
        this.color = Color.BLACK;
        this.strokeWidth = 20;
        this.path = path;
    }
}
