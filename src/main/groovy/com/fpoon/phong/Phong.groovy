package com.fpoon.phong

import javafx.geometry.Point3D

import javax.swing.*
import java.awt.*

class Phong {
    double m
    double ip
    double kd
    double ks

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

    def render(JPanel panel) {
        def gfx = panel.graphics
        def radius = (panel.width < panel.height ? panel.width : panel.height) * 0.5
        def shape = ShapeUtil.getSphere(radius)
        gfx.setColor(Color.BLACK)
        gfx.fillRect(0, 0, panel.width, panel.height)
        shape.each {
            gfx.setColor(new Color(it.z/radius as float, 1f, 1f))
//            println "Color: ${gfx.getColor()}"
            gfx.fillOval(it.x as int, it.y as int, 2, 2)
        }
    }

}
