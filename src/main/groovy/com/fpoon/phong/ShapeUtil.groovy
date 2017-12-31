package com.fpoon.phong

import javafx.geometry.Point3D

class ShapeUtil {
    static Point3D[] getSphere(double radius) {
        println "Computing shape model..."
        println "Radius: ${radius}"
        def points = []
        double stepy = 1.0
        double stepx = 1.0
        0.step(2*radius+stepx, stepx) { x ->
            0.step(2*radius+stepy, stepy) { y->

                if ((x-radius)**2 + (y-radius)**2 > radius*radius)
                    return

                double z = -(radius**2 - new Point3D(radius, radius, 0).distance(new Point3D(x,y,0))**2)**0.5

                points += new Point3D(x, y, z)
            }
//            println "Generated column at x=${x}"
        }

        println "Computing shape model... DONE"
        println "Generated ${points.size()} points."

        return points;
    }
}
