package com.fpoon.s3dcam

import groovy.swing.SwingBuilder
import javafx.geometry.Point3D

import javax.swing.BorderFactory
import javax.swing.BoxLayout
import javax.swing.WindowConstants
import java.awt.BorderLayout
import java.awt.Color
import java.awt.KeyEventDispatcher
import java.awt.KeyboardFocusManager
import java.awt.event.KeyEvent

class Main {

    static void main(String[] args) {
        def swing = new SwingBuilder()

        def frame = swing.frame(
                title: "Phong reflection model",
                size: [800, 600],
                defaultCloseOperation: WindowConstants.EXIT_ON_CLOSE
        ) {
            panel(layout: new BorderLayout()) {
                panel(
                        constraints: BorderLayout.EAST,
                        minimumSize: [100, -1],
                        border: BorderFactory.createRaisedBevelBorder()
                ) {
                    boxLayout(axis:BoxLayout.Y_AXIS)
                    label(text: "CONTROL PANEL")
                    panel(
                            minimumSize: [100, -1],
                            border: BorderFactory.createRaisedBevelBorder()
                    ) {
                        boxLayout(axis: BoxLayout.Y_AXIS)
                        label(text: "MOVE CAMERA")
                    }
                    panel(
                            minimumSize: [100, -1],
                            border: BorderFactory.createRaisedBevelBorder()
                    ) {
                        boxLayout(axis: BoxLayout.Y_AXIS)
                        label(text: "ROTATE CAMERA")
                    }
                    panel(
                            minimumSize: [100, -1],
                            border: BorderFactory.createRaisedBevelBorder()
                    ) {
                        boxLayout(axis: BoxLayout.Y_AXIS)
                        label(text: "ZOOM")
                    }

                }
                panel(constraints: BorderLayout.CENTER,
                        background: Color.BLACK,
                        id: "renderPanel"
                )
            }
        }

        frame.setVisible(true)
    }
}