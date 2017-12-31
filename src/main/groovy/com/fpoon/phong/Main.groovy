package com.fpoon.s3dcam

import com.fpoon.phong.Phong
import groovy.swing.SwingBuilder

import javax.swing.*
import java.awt.*

class Main {

    static void main(String[] args) {
        def swing = new SwingBuilder()
        def phong = new Phong()

        def reset = {
            def renderPanel = swing."renderPanel"
            phong = new Phong()
            phong.render(renderPanel)
        }

        def frame = swing.frame(
                title: "Phong reflection model",
                size: [400, 300],
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
                    button(text: "RESET", actionPerformed: reset)
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
                        background: Color.PINK,
                        id: "renderPanel"
                )
            }
        }

        frame.setVisible(true)

    }
}