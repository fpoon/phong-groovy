package com.fpoon.s3dcam

import com.fpoon.phong.Phong
import groovy.swing.SwingBuilder
import javafx.geometry.Point3D

import javax.swing.*
import java.awt.*

class Main {

    static void main(String[] args) {
        def swing = new SwingBuilder()

        def lights = [
                topLeft: new Point3D(-1, -1, -2),
                topCenter: new Point3D(0, -1, -2),
                topRight: new Point3D(1, -1, -2),
                centerLeft: new Point3D(-1, 0, -2),
                centerCenter: new Point3D(0, 0, -2),
                centerRight: new Point3D(1, 0, -2),
                bottomLeft: new Point3D(-1, 1, -2),
                bottomCenter: new Point3D(0, 1, -2),
                bottomRight: new Point3D(1, 1, -2)
        ]

        def draw = {
            def renderPanel = swing."renderPanel"
            def phong = new Phong(
                    swing."ipSlider".value/100,
                    swing."iaSlider".value/100,
                    swing."kaSlider".value/100,
                    swing."kdSlider".value/100,
                    swing."ksSlider".value/100,
                    swing."mSlider".value
            )

            phong.light = lights[swing."lightGroup".getSelection().getActionCommand()]

            phong.render(renderPanel)
        }

        def frame = swing.frame(
                title: "Phong reflection model",
                size: [600, 300],
                defaultCloseOperation: WindowConstants.EXIT_ON_CLOSE,
                resizable: false
        ) {
            panel(layout: new BorderLayout()) {
                panel(
                        constraints: BorderLayout.EAST,
//                        minimumSize: [100, -1],
                        border: BorderFactory.createRaisedBevelBorder()
                ) {
                    boxLayout(axis:BoxLayout.X_AXIS)
                    panel(
//                            minimumSize: [100, -1],
                            border: BorderFactory.createRaisedBevelBorder()
                    ) {
                        label(text: "CONTROL PANEL")
                        button(text: "DRAW", actionPerformed: draw)
                        boxLayout(axis: BoxLayout.Y_AXIS)
                        slider(id: "mSlider", value: 50, minimum: 1, maximum: 500)
                        label(text: bind(source: swing."mSlider", sourceProperty: "value", converter:{"n: ${it}"}))
                        slider(id: "ipSlider", value: 100, minimum: 0, maximum: 100)
                        label(text: bind(source: swing."ipSlider", sourceProperty: "value", converter:{"ip: ${sprintf("%.2f", it/100)}"}))
                        slider(id: "iaSlider", value: 10, minimum: 0, maximum: 100)
                        label(text: bind(source: swing."iaSlider", sourceProperty: "value", converter:{"ia: ${sprintf("%.2f", it/100)}"}))
                        slider(id: "kaSlider", value: 45, minimum: 0, maximum: 100)
                        label(text: bind(source: swing."kaSlider", sourceProperty: "value", converter:{"ka: ${sprintf("%.2f", it/100)}"}))
                        slider(id: "kdSlider", value: 90, minimum: 0, maximum: 100)
                        label(text: bind(source: swing."kdSlider", sourceProperty: "value", converter:{"kd: ${sprintf("%.2f", it/100)}"}))
                        slider(id: "ksSlider", value: 20, minimum: 0, maximum: 100)
                        label(text: bind(source: swing."ksSlider", sourceProperty: "value", converter:{"ks: ${sprintf("%.2f", it/100)}"}))
                    }
                    panel(
//                            minimumSize: [100, -1],
                            border: BorderFactory.createRaisedBevelBorder()
                    ) {
                        boxLayout(axis: BoxLayout.Y_AXIS)
                        label(text: "LIGHT SOURCE")
                        lightGroup = buttonGroup(id: "lightGroup")
                        radioButton(text: "Top Left", buttonGroup: lightGroup, selected: true, actionCommand: "topLeft")
                        radioButton(text: "Top Center", buttonGroup: lightGroup,  actionCommand: "topCenter")
                        radioButton(text: "Top Right", buttonGroup: lightGroup, actionCommand: "topRight")
                        radioButton(text: "Center Left", buttonGroup: lightGroup, actionCommand: "centerLeft")
                        radioButton(text: "Center Center", buttonGroup: lightGroup, actionCommand: "centerCenter")
                        radioButton(text: "Center Right", buttonGroup: lightGroup, actionCommand: "centerRight")
                        radioButton(text: "Bottom Left", buttonGroup: lightGroup, actionCommand: "bottomLeft")
                        radioButton(text: "Bottom Center", buttonGroup: lightGroup, actionCommand: "bottomCenter")
                        radioButton(text: "Bottom Right", buttonGroup: lightGroup, actionCommand: "bottomRight")

                    }

                }
                panel(constraints: BorderLayout.CENTER,
                        background: Color.GRAY,
                        id: "renderPanel"
                )
            }
        }

        frame.setVisible(true)

    }
}