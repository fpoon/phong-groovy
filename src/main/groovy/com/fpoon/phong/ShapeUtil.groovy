package com.fpoon.phong

import javafx.geometry.Point3D

import java.awt.Dimension
import java.awt.Point

class ShapeUtil {
    static Point3D[] getSphere(double radius, Dimension d) {
        def points = []
        double stepy = radius/d.height
        double stepx = radius/d.width
        -radius.step(radius, stepx) { x ->
            -radius.step(radius, stepy) { y->
                if (x*x + y*y > radius*radius)
                    return

                double z = 0;

                points += new Point3D(x, y, z)
            }
        }

        return points;
    }
}
