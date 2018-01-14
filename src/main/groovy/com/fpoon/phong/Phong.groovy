package com.fpoon.phong

import javafx.geometry.Point3D

import javax.swing.*
import java.awt.*

class Phong {

    double ip = 1.0
    double ia = 0.1
    double ka = 0.45
    double kd = 0.9
    double ks = 0.2
    double m = 50

    Point3D light = new Point3D(-1, -1, -2)

    Phong(ip, ia, ka, kd, ks, m) {
        this.ip = ip
        this.ia = ia
        this.ka = ka
        this.kd = kd
        this.ks = ks
        this.m = m
    }



    double phong(Point3D v, Point3D l, Point3D n) {
        v = v.normalize()
        l = l.normalize()
        n = n.normalize()

        Point3D h = getH(v,l)
        double ndl = crop(n.dotProduct(l))
        double ndh = crop(n.dotProduct(h))

        return crop(kd * ndl * ip + ks * Math.pow(ndh, m) * ip) * ip
    }

    Point3D getH(Point3D p, Point3D q) {
        Point3D r = q.multiply(p.distance(Point3D.ZERO) / q.distance(Point3D.ZERO))
        return r.add(
                q.subtract(r).multiply(0.5)
        )
    }

    double crop(double n) {
        return n < 0.0 ? 0.0 : n > 1.0 ? 1.0 : n
    }

    def render(JPanel panel) {
        def gfx = panel.graphics
        def radius = (panel.width < panel.height ? panel.width : panel.height) * 0.5
        def shape = ShapeUtil.getSphere(radius)
        def light = new Point3D(this.light.x*radius+radius, this.light.y*radius+radius, this.light.z*radius)
        gfx.setColor(Color.GRAY)
        gfx.fillRect(0, 0, panel.width, panel.height)
        Point3D camera = new Point3D(+radius, +radius, -3*radius)
        shape.each {

            def v = camera.subtract(it)
            def l = light.subtract(it)
            def n = new Point3D(it.x-radius, it.y-radius, it.z)

            def p = phong(v, l, n)

            if (p.isNaN()) p = 0

            p += ia*ka

            p = crop(p) as float

            gfx.setColor( new Color(p, p, p))
            gfx.fillOval(it.x as int, it.y as int, 2, 2)
        }
    }

}
