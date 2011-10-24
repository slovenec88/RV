package com.example.projectionviewportcamera;

import com.badlogic.gdx.backends.jogl.JoglApplication;

public class ProjectionViewportCameraDesktop {

    public static void main(String[] args) {
        new JoglApplication(new ProjectionViewportCamera(), "Viewport, Perspective, and Camera", 480, 320, false);               
    }

}
