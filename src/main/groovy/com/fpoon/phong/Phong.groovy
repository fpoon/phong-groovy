package com.fpoon.phong

import javafx.geometry.Point3D

class Phong {
    double m
    double ip
    double kd
    double ks

    Point3D[] points;

    double phong(Point3D v, Point3D l, Point3D n) {
        v = v.normalize()
        l = l.normalize()
        n = n.normalize()

        Point3D h = l.add(v).normalize()
        double ndl = crop(n.dotProduct(l))
        double ndh = crop(n.dotProduct(h))

        return crop(kd * ndl * ip + ks * Math.pow(ndh, m) * ip)
    }

    double crop(double n) {
        return n < 0.0 ? 0.0 : n > 1.0 ? 1.0 : n
    }
}
