package com.example.vladimir.sityinfov113;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Vladimir on 16.08.2017.
 */

public class OpenGLProjectRenderer implements GLSurfaceView.Renderer {

    //вершинный буфер
    //final FloatBuffer verticesReady;

    int colored_vertices_program_handle;
    int MVPmatrix_location;
    int mPositionHandle;
    int mColorHandle;
    final int mBytesPerFloat = 4;
    final int mStrideBytes = 7 * mBytesPerFloat;
    final int mPositionOffset = 0;
    final int mPositionDataSize = 3;
    final int mColorOffset = 3;
    final int mColorDataSize = 4;


    public Camera camera = new Camera();
    public FPSCounter fpsCounter = new FPSCounter();
    public ReadFile readFile = new ReadFile();
    FloatBuffer test_vertices;
    FloatBuffer verticesReady;
    String strLine;
    float [] mass_vertices = new float[0];

    public OpenGLProjectRenderer() {

        float[] test={

//Исправлено
                4,50,0,
                1.0f,0.549f,0.0f,1f,
                -5,50,0,
                1.0f,0.549f,0.0f,1f,
                -5,-50,0,
                1.0f,0.549f,0.0f,1f,

                -5,-50,0,
                1.0f,0.549f,0.0f,1f,
                4,-50,0,
                1.0f,0.549f,0.0f,1f,
                4,50,0,
                1.0f,0.549f,0.0f,1f,

//Исправленно
                -50,4,0,
                1.0f,0.549f,0.0f,1f,
                -50,-4,0,
                1.0f,0.549f,0.0f,1f,
                50,-4,0,
                1.0f,0.549f,0.0f,1f,

                50,-4,0,
                1.0f,0.549f,0.0f,1f,
                50,4,0,
                1.0f,0.549f,0.0f,1f,
                -50,4,0,
                1.0f,0.549f,0.0f,1f,

//Исправленно
                50,-7,10,
                0.2f,0.2f,0.2f,1f,
                20,-7,10,
                0.2f,0.2f,0.2f,1f,
                20,-20,10,
                0.2f,0.2f,0.2f,1f,

                20,-20,10,
                0.2f,0.2f,0.2f,1f,
                50,-20,10,
                0.2f,0.2f,0.2f,1f,
                50,-7,10,
                0.2f,0.2f,0.2f,1f,

//Исправленно
                50,-7,0,
                0.2f,0.2f,0.2f,1f,
                20,-7,0,
                0.2f,0.2f,0.2f,1f,
                20,-20,0,
                0.2f,0.2f,0.2f,1f,

                20,-20,0,
                0.2f,0.2f,0.2f,1f,
                50,-20,0,
                0.2f,0.2f,0.2f,1f,
                50,-7,0,
                0.2f,0.2f,0.2f,1f,


//Исправленно
                50,-7,0,
                0.5f,0.5f,0.5f,1f,
                20,-7,0,
                0.5f,0.5f,0.5f,1f,
                20,-7,10,
                0.5f,0.5f,0.5f,1f,

                20,-7,10,
                0.5f,0.5f,0.5f,1f,
                50,-7,10,
                0.5f,0.5f,0.5f,1f,
                50,-7,0,
                0.5f,0.5f,0.5f,1f,

//Исправленно
                50,-20,0,
                0.5f,0.5f,0.5f,1f,
                50,-20,10,
                0.5f,0.5f,0.5f,1f,
                20,-20,10,
                0.5f,0.5f,0.5f,1f,

                20,-20,10,
                0.5f,0.5f,0.5f,1f,
                20,-20,0,
                0.5f,0.5f,0.5f,1f,
                50,-20,0,
                0.5f,0.5f,0.5f,1f,

//Исправленно
                20,-7,0,
                0.5f,0.5f,0.5f,1f,
                20,-20,0,
                0.5f,0.5f,0.5f,1f,
                20,-20,10,
                0.5f,0.5f,0.5f,1f,

                20,-20,10,
                0.5f,0.5f,0.5f,1f,
                20,-7,10,
                0.5f,0.5f,0.5f,1f,
                20,-7,0,
                0.5f,0.5f,0.5f,1f,

//Исправленно
                20,-20,10,
                0.5f,0.5f,0.5f,1f,
                20,-20,0,
                0.5f,0.5f,0.5f,1f,
                20,-7,10,
                0.5f,0.5f,0.5f,1f,

                50,-20,10,
                0.5f,0.5f,0.5f,1f,
                50,-20,0,
                0.5f,0.5f,0.5f,1f,
                50,-7,10,
                0.5f,0.5f,0.5f,1f,

//Исправленно
                50,-20,10,
                0.5f,0.5f,0.5f,1f,
                50,-20,0,
                0.5f,0.5f,0.5f,1f,
                50,-7,0,
                0.5f,0.5f,0.5f,1f,

                50,-7,0,
                0.5f,0.5f,0.5f,1f,
                50,-7,10,
                0.5f,0.5f,0.5f,1f,
                50,-20,10,
                0.5f,0.5f,0.5f,1f,


//Исправленно
                50,20,0,
                0.2f,0.2f,0.2f,1f,
                20,20,0,
                0.2f,0.2f,0.2f,1f,
                20,7,0,
                0.2f,0.2f,0.2f,1f,

                20,7,0,
                0.2f,0.2f,0.2f,1f,
                50,7,0,
                0.2f,0.2f,0.2f,1f,
                50,20,0,
                0.2f,0.2f,0.2f,1f,

//Исправленно
                20,20,0,
                0.5f,0.5f,0.5f,1f,
                20,20,10,
                0.5f,0.5f,0.5f,1f,
                50,20,10,
                0.5f,0.5f,0.5f,1f,

                20,20,0,
                0.5f,0.5f,0.5f,1f,
                20,20,10,
                0.5f,0.5f,0.5f,1f,
                50,20,0,
                0.5f,0.5f,0.5f,1f,

//Исправленно
                //
                50,7,0,
                0.5f,0.5f,0.5f,1f,
                50,20,0,
                0.5f,0.5f,0.5f,1f,
                50,20,10,
                0.5f,0.5f,0.5f,1f,

                50,20,10,
                0.5f,0.5f,0.5f,1f,
                50,7,10,
                0.5f,0.5f,0.5f,1f,
                50,7,0,
                0.5f,0.5f,0.5f,1f,

//Исправленно
                //
                20,7,0,
                0.5f,0.5f,0.5f,1f,
                20,7,10,
                0.5f,0.5f,0.5f,1f,
                20,20,10,
                0.5f,0.5f,0.5f,1f,

                20,20,10,
                0.5f,0.5f,0.5f,1f,
                20,20,0,
                0.5f,0.5f,0.5f,1f,
                20,7,0,
                0.5f,0.5f,0.5f,1f,

//Исправленно
                //
                50,7,0,
                0.5f,0.5f,0.5f,1f,
                50,7,10,
                0.5f,0.5f,0.5f,1f,
                20,7,10,
                0.5f,0.5f,0.5f,1f,

                20,7,10,
                0.5f,0.5f,0.5f,1f,
                20,7,0,
                0.5f,0.5f,0.5f,1f,
                50,7,0,
                0.5f,0.5f,0.5f,1f,

//Исправленно
                //
                50,20,10,
                0.2f,0.2f,0.2f,1f,
                20,20,10,
                0.2f,0.2f,0.2f,1f,
                50,7,10,
                0.2f,0.2f,0.2f,1f,

                20,20,10,
                0.2f,0.2f,0.2f,1f,
                20,7,10,
                0.2f,0.2f,0.2f,1f,
                50,7,10,
                0.2f,0.2f,0.2f,1f,

//Исправленно
                //
                50,20,0,
                0.2f,0.2f,0.2f,1f,
                50,28,0,
                0.2f,0.2f,0.2f,1f,
                35,28,0,
                0.2f,0.2f,0.2f,1f,

                35,28,0,
                0.2f,0.2f,0.2f,1f,
                35,20,0,
                0.2f,0.2f,0.2f,1f,
                50,20,0,
                0.2f,0.2f,0.2f,1f,

//Исправленно//
                //
                50,20,0,
                0.5f,0.5f,0.5f,1f,
                50,28,0,
                0.5f,0.5f,0.5f,1f,
                50,28,30,
                0.5f,0.5f,0.5f,1f,

                50,28,30,
                0.5f,0.5f,0.5f,1f,
                50,20,30,
                0.5f,0.5f,0.5f,1f,
                50,20,0,
                0.5f,0.5f,0.5f,1f,

//Исправленно
                //
                50,28,0,
                0.5f,0.5f,0.5f,1f,
                35,28,0,
                0.5f,0.5f,0.5f,1f,
                35,28,30,
                0.5f,0.5f,0.5f,1f,

                50,28,0,
                0.5f,0.5f,0.5f,1f,
                35,28,30,
                0.5f,0.5f,0.5f,1f,
                50,28,30,
                0.5f,0.5f,0.5f,1f,

//Исправленно
                //
                35,28,30,
                0.5f,0.5f,0.5f,1f,
                35,28,0,
                0.5f,0.5f,0.5f,1f,
                35,20,0,
                0.5f,0.5f,0.5f,1f,

                35,20,0,
                0.5f,0.5f,0.5f,1f,
                35,20,30,
                0.5f,0.5f,0.5f,1f,
                35,28,30,
                0.5f,0.5f,0.5f,1f,

//Исправленно
                //
                35,20,0,
                0.5f,0.5f,0.5f,1f,
                50,20,0,
                0.5f,0.5f,0.5f,1f,
                50,20,30,
                0.5f,0.5f,0.5f,1f,

                50,20,30,
                0.5f,0.5f,0.5f,1f,
                35,20,30,
                0.5f,0.5f,0.5f,1f,
                35,20,0,
                0.5f,0.5f,0.5f,1f,

//Исправленно
                //
                35,20,30,
                0.2f,0.2f,0.2f,1f,
                50,20,30,
                0.2f,0.2f,0.2f,1f,
                50,28,30,
                0.2f,0.2f,0.2f,1f,

                50,28,30,
                0.2f,0.2f,0.2f,1f,
                35,28,30,
                0.2f,0.2f,0.2f,1f,
                35,20,30,
                0.2f,0.2f,0.2f,1f,

//Исправленно
                //
                -50,28,0,
                0.2f,0.2f,0.2f,1f,
                -50,7,0,
                0.2f,0.2f,0.2f,1f,
                -30,7,0,
                0.2f,0.2f,0.2f,1f,

                -30,7,0,
                0.2f,0.2f,0.2f,1f,
                -30,28,0,
                0.2f,0.2f,0.2f,1f,
                -50,28,0,
                0.2f,0.2f,0.2f,1f,

//Исправленно
                //
                -30,7,0,
                0.5f,0.5f,0.5f,1f,
                -30,28,0,
                0.5f,0.5f,0.5f,1f,
                -30,28,50,
                0.5f,0.5f,0.5f,1f,

                -30,28,50,
                0.5f,0.5f,0.5f,1f,
                -30,7,50,
                0.5f,0.5f,0.5f,1f,
                -30,7,0,
                0.5f,0.5f,0.5f,1f,

//Исправленно
                //
                -30,28,0,
                0.5f,0.5f,0.5f,1f,
                -50,28,0,
                0.5f,0.5f,0.5f,1f,
                -50,28,50,
                0.5f,0.5f,0.5f,1f,

                -50,28,50,
                0.5f,0.5f,0.5f,1f,
                -30,28,50,
                0.5f,0.5f,0.5f,1f,
                -30,28,0,
                0.5f,0.5f,0.5f,1f,

//Исправленно
                //
                -30,7,0,
                0.5f,0.5f,0.5f,1f,
                -30,7,50,
                0.5f,0.5f,0.5f,1f,
                -50,7,50,
                0.5f,0.5f,0.5f,1f,

                -50,7,50,
                0.5f,0.5f,0.5f,1f,
                -50,7,0,
                0.5f,0.5f,0.5f,1f,
                -30,7,0,
                0.5f,0.5f,0.5f,1f,

//Исправленно
                //
                -50,7,0,
                0.5f,0.5f,0.5f,1f,
                -50,7,50,
                0.5f,0.5f,0.5f,1f,
                -50,28,50,
                0.5f,0.5f,0.5f,1f,

                -50,28,50,
                0.5f,0.5f,0.5f,1f,
                -50,28,0,
                0.5f,0.5f,0.5f,1f,
                -50,7,0,
                0.5f,0.5f,0.5f,1f,

//Исправленно
                //
                -50,7,50,
                0.2f,0.2f,0.2f,1f,
                -30,28,50,
                0.2f,0.2f,0.2f,1f,
                -50,28,50,
                0.2f,0.2f,0.2f,1f,

                -30,28,50,
                0.2f,0.2f,0.2f,1f,
                -50,7,50,
                0.2f,0.2f,0.2f,1f,
                -30,7,50,
                0.2f,0.2f,0.2f,1f,

//Исправленно
                //
                -50,50,0,
                0.2f,0.2f,0.2f,1f,
                -50,35,0,
                0.2f,0.2f,0.2f,1f,
                -10,35,0,
                0.2f,0.2f,0.2f,1f,

                -10,35,0,
                0.2f,0.2f,0.2f,1f,
                -10,50,0,
                0.2f,0.2f,0.2f,1f,
                -50,50,0,
                0.2f,0.2f,0.2f,1f,

//Исправленно
                //
                -50,50,0,
                0.5f,0.5f,0.5f,1f,
                -50,50,75,
                0.5f,0.5f,0.5f,1f,
                -10,50,75,
                0.5f,0.5f,0.5f,1f,

                -10,50,75,
                0.5f,0.5f,0.5f,1f,
                -10,50,0,
                0.5f,0.5f,0.5f,1f,
                -50,50,0,
                0.5f,0.5f,0.5f,1f,

//Исправленно
                //
                -10,50,0,
                0.5f,0.5f,0.5f,1f,
                -10,50,75,
                0.5f,0.5f,0.5f,1f,
                -10,35,75,
                0.5f,0.5f,0.5f,1f,

                -10,35,75,
                0.5f,0.5f,0.5f,1f,
                -10,35,0,
                0.5f,0.5f,0.5f,1f,
                -10,50,0,
                0.5f,0.5f,0.5f,1f,



//Исправленно
                //
                -50,35,0,
                0.5f,0.5f,0.5f,1f,
                -50,35,75,
                0.5f,0.5f,0.5f,1f,
                -50,50,75,
                0.5f,0.5f,0.5f,1f,

                -50,50,75,
                0.5f,0.5f,0.5f,1f,
                -50,50,0,
                0.5f,0.5f,0.5f,1f,
                -50,35,0,
                0.5f,0.5f,0.5f,1f,



//Исправленно
                //
                -10,35,0,
                0.5f,0.5f,0.5f,1f,
                -10,35,75,
                0.5f,0.5f,0.5f,1f,
                -50,35,75,
                0.5f,0.5f,0.5f,1f,

                -50,35,75,
                0.5f,0.5f,0.5f,1f,
                -50,35,0,
                0.5f,0.5f,0.5f,1f,
                -10,35,0,
                0.5f,0.5f,0.5f,1f,

//Исправленно
                //
                -10,35,75,
                0.2f,0.2f,0.2f,1f,
                -10,50,75,
                0.2f,0.2f,0.2f,1f,
                -50,50,75,
                0.2f,0.2f,0.2f,1f,

                -50,50,75,
                0.2f,0.2f,0.2f,1f,
                -50,35,75,
                0.2f,0.2f,0.2f,1f,
                -10,35,75,
                0.2f,0.2f,0.2f,1f,

//Исправленно
                //
                -25,35,0,
                0.2f,0.2f,0.2f,1f,
                -25,13,0,
                0.2f,0.2f,0.2f,1f,
                -10,13,0,
                0.2f,0.2f,0.2f,1f,

                -10,13,0,
                0.2f,0.2f,0.2f,1f,
                -10,35,0,
                0.2f,0.2f,0.2f,1f,
                -25,35,0,
                0.2f,0.2f,0.2f,1f,

//Исправленно
                //
                -10,35,0,
                0.5f,0.5f,0.5f,1f,
                -25,35,0,
                0.5f,0.5f,0.5f,1f,
                -25,35,100,
                0.5f,0.5f,0.5f,1f,

                -25,35,100,
                0.5f,0.5f,0.5f,1f,
                -10,35,100,
                0.5f,0.5f,0.5f,1f,
                -10,35,0,
                0.5f,0.5f,0.5f,1f,

//Исправленно
                //
                -10,13,0,
                0.5f,0.5f,0.5f,1f,
                -10,13,100,
                0.5f,0.5f,0.5f,1f,
                -25,13,100,
                0.5f,0.5f,0.5f,1f,

                -25,13,100,
                0.5f,0.5f,0.5f,1f,
                -25,13,0,
                0.5f,0.5f,0.5f,1f,
                -10,13,0,
                0.5f,0.5f,0.5f,1f,

//Исправленно
                //
                -10,13,100,
                0.5f,0.5f,0.5f,1f,
                -10,13,0,
                0.5f,0.5f,0.5f,1f,
                -10,35,0,
                0.5f,0.5f,0.5f,1f,

                -10,35,0,
                0.5f,0.5f,0.5f,1f,
                -10,35,100,
                0.5f,0.5f,0.5f,1f,
                -10,13,100,
                0.5f,0.5f,0.5f,1f,

//Исправленно
                //

                -25,13,100,
                0.5f,0.5f,0.5f,1f,
                -25,35,100,
                0.5f,0.5f,0.5f,1f,
                -25,35,0,
                0.5f,0.5f,0.5f,1f,

                -25,35,0,
                0.5f,0.5f,0.5f,1f,
                -25,13,0,
                0.5f,0.5f,0.5f,1f,
                -25,13,100,
                0.5f,0.5f,0.5f,1f,

//Исправленно
                //
                -25,35,100,
                0.2f,0.2f,0.2f,1f,
                -25,13,100,
                0.2f,0.2f,0.2f,1f,
                -10,13,100,
                0.2f,0.2f,0.2f,1f,

                -10,13,100,
                0.2f,0.2f,0.2f,1f,
                -10,35,100,
                0.2f,0.2f,0.2f,1f,
                -25,35,100,
                0.2f,0.2f,0.2f,1f,

//Исправленно
                //
                -20,-10,0,
                0.2f,0.2f,0.2f,1f,
                -40,-10,0,
                0.2f,0.2f,0.2f,1f,
                -20,-20,0,
                0.2f,0.2f,0.2f,1f,

                -20,-20,0,
                0.2f,0.2f,0.2f,1f,
                -40,-10,0,
                0.2f,0.2f,0.2f,1f,
                -40,-20,0,
                0.2f,0.2f,0.2f,1f,

//Исправленно
                //
                -20,-10,0,
                0.5f,0.5f,0.5f,1f,
                -40,-10,0,
                0.5f,0.5f,0.5f,1f,
                -40,-10,10,
                0.5f,0.5f,0.5f,1f,

                -40,-10,10,
                0.5f,0.5f,0.5f,1f,
                -20,-10,10,
                0.5f,0.5f,0.5f,1f,
                -20,-10,0,
                0.5f,0.5f,0.5f,1f,

//Исправленно(возможно)
                //
                -20,-20,0,
                0.5f,0.5f,0.5f,1f,
                -40,-20,0,
                0.5f,0.5f,0.5f,1f,
                -40,-20,10,
                0.5f,0.5f,0.5f,1f,

                -40,-20,10,
                0.5f,0.5f,0.5f,1f,
                -20,-20,10,
                0.5f,0.5f,0.5f,1f,
                -20,-20,0,
                0.5f,0.5f,0.5f,1f,

//Исправленно
                //
                -20,-20,0,
                0.5f,0.5f,0.5f,1f,
                -20,-10,0,
                0.5f,0.5f,0.5f,1f,
                -20,-10,10,
                0.5f,0.5f,0.5f,1f,

                -20,-10,10,
                0.5f,0.5f,0.5f,1f,
                -20,-20,10,
                0.5f,0.5f,0.5f,1f,
                -20,-20,0,
                0.5f,0.5f,0.5f,1f,

//Исправленно
                //
                -40,-10,10,
                0.5f,0.5f,0.5f,1f,
                -40,-10,0,
                0.5f,0.5f,0.5f,1f,
                -40,-20,0,
                0.5f,0.5f,0.5f,1f,

                -40,-20,0,
                0.5f,0.5f,0.5f,1f,
                -40,-20,10,
                0.5f,0.5f,0.5f,1f,
                -40,-10,10,
                0.5f,0.5f,0.5f,1f,

//Исправленно
                //
                -40,-10,10,
                0.2f,0.2f,0.2f,1f,
                -40,-20,10,
                0.2f,0.2f,0.2f,1f,
                -20,-20,10,
                0.2f,0.2f,0.2f,1f,

                -20,-20,10,
                0.2f,0.2f,0.2f,1f,
                -20,-10,10,
                0.2f,0.2f,0.2f,1f,
                -40,-10,10,
                0.2f,0.2f,0.2f,1f,

//Исправленно
                //
                -10,-20,0,
                0.2f,0.2f,0.2f,1f,
                -50,-20,0,
                0.2f,0.2f,0.2f,1f,
                -50,-50,0,
                0.2f,0.2f,0.2f,1f,

                -50,-50,0,
                0.2f,0.2f,0.2f,1f,
                -10,-50,0,
                0.2f,0.2f,0.2f,1f,
                -10,-20,0,
                0.2f,0.2f,0.2f,1f,


//Исправленно
                //
                -50,-20,0,
                0.5f,0.5f,0.5f,1f,
                -50,-20,20,
                0.5f,0.5f,0.5f,1f,
                -10,-20,20,
                0.5f,0.5f,0.5f,1f,

                -10,-20,20,
                0.5f,0.5f,0.5f,1f,
                -10,-20,0,
                0.5f,0.5f,0.5f,1f,
                -50,-20,0,
                0.5f,0.5f,0.5f,1f,

//Исправленно
                //
                -10,-20,0,
                0.5f,0.5f,0.5f,1f,
                -10,-20,20,
                0.5f,0.5f,0.5f,1f,
                -10,-50,20,
                0.5f,0.5f,0.5f,1f,

                -10,-50,20,
                0.5f,0.5f,0.5f,1f,
                -10,-50,0,
                0.5f,0.5f,0.5f,1f,
                -10,-20,0,
                0.5f,0.5f,0.5f,1f,

//Исправленно
                //
                -50,-50,0,
                0.5f,0.5f,0.5f,1f,
                -50,-50,20,
                0.5f,0.5f,0.5f,1f,
                -50,-20,20,
                0.5f,0.5f,0.5f,1f,

                -50,-20,20,
                0.5f,0.5f,0.5f,1f,
                -50,-20,0,
                0.5f,0.5f,0.5f,1f,
                -50,-50,0,
                0.5f,0.5f,0.5f,1f,




//Исправленно
                //
                -10,-50,0,
                0.5f,0.5f,0.5f,1f,
                -10,-50,20,
                0.5f,0.5f,0.5f,1f,
                -50,-50,20,
                0.5f,0.5f,0.5f,1f,

                -50,-50,20,
                0.5f,0.5f,0.5f,1f,
                -50,-50,0,
                0.5f,0.5f,0.5f,1f,
                -10,-50,0,
                0.5f,0.5f,0.5f,1f,

//Исправленно
                //
                -50,-20,20,
                0.2f,0.2f,0.2f,1f,
                -50,-50,20,
                0.2f,0.2f,0.2f,1f,
                -10,-50,20,
                0.2f,0.2f,0.2f,1f,

                -10,-50,20,
                0.2f,0.2f,0.2f,1f,
                -10,-20,20,
                0.2f,0.2f,0.2f,1f,
                -50,-20,20,
                0.2f,0.2f,0.2f,1f,

//Исправленно
                //
                12,15,0,
                0.2f,0.2f,0.2f,1f,
                12,20,0,
                0.2f,0.2f,0.2f,1f,
                7,20,0,
                0.2f,0.2f,0.2f,1f,

                7,20,0,
                0.2f,0.2f,0.2f,1f,
                7,15,0,
                0.2f,0.2f,0.2f,1f,
                12,15,0,
                0.2f,0.2f,0.2f,1f,

//Исправленно
                //
                7,-15,0,
                0.2f,0.2f,0.2f,1f,
                7,-20,0,
                0.2f,0.2f,0.2f,1f,
                12,-20,0,
                0.2f,0.2f,0.2f,1f,

                12,-20,0,
                0.2f,0.2f,0.2f,1f,
                12,-15,0,
                0.2f,0.2f,0.2f,1f,
                7,-15,0,
                0.2f,0.2f,0.2f,1f,

//Исправленно
                //
                7,-15,0,
                0.5f,0.5f,0.5f,1f,
                7,-15,15,
                0.5f,0.5f,0.5f,1f,
                12,-15,15,
                0.5f,0.5f,0.5f,1f,

                12,-15,15,
                0.5f,0.5f,0.5f,1f,
                12,-15,0,
                0.5f,0.5f,0.5f,1f,
                7,-15,0,
                0.5f,0.5f,0.5f,1f,

//Исправленно
                //
                12,-20,0,
                0.5f,0.5f,0.5f,1f,
                12,-20,15,
                0.5f,0.5f,0.5f,1f,
                7,-20,15,
                0.5f,0.5f,0.5f,1f,

                7,-20,15,
                0.5f,0.5f,0.5f,1f,
                7,-20,0,
                0.5f,0.5f,0.5f,1f,
                12,-20,0,
                0.5f,0.5f,0.5f,1f,

//Исправленно
                //
                7,-15,0,
                0.5f,0.5f,0.5f,1f,
                7,-20,0,
                0.5f,0.5f,0.5f,1f,
                7,-20,15,
                0.5f,0.5f,0.5f,1f,

                7,-20,15,
                0.5f,0.5f,0.5f,1f,
                7,-15,15,
                0.5f,0.5f,0.5f,1f,
                7,-15,0,
                0.5f,0.5f,0.5f,1f,

//Исправленно
                //
                12,-15,0,
                0.5f,0.5f,0.5f,1f,
                12,-15,15,
                0.5f,0.5f,0.5f,1f,
                12,-20,15,
                0.5f,0.5f,0.5f,1f,


                12,-20,15,
                0.5f,0.5f,0.5f,1f,
                12,-20,0,
                0.5f,0.5f,0.5f,1f,
                12,-15,0,
                0.5f,0.5f,0.5f,1f,

//Исправленно
                //
                7,-15,15,
                0.2f,0.2f,0.2f,1f,
                7,-20,15,
                0.2f,0.2f,0.2f,1f,
                12,-20,15,
                0.2f,0.2f,0.2f,1f,

                12,-20,15,
                0.2f,0.2f,0.2f,1f,
                12,-15,15,
                0.2f,0.2f,0.2f,1f,
                7,-15,15,
                0.2f,0.2f,0.2f,1f,

//Исправленно
                //
                12,20,15,
                0.5f,0.5f,0.5f,1f,
                12,15,15,
                0.5f,0.5f,0.5f,1f,
                12,15,0,
                0.5f,0.5f,0.5f,1f,

                12,15,0,
                0.5f,0.5f,0.5f,1f,
                12,20,0,
                0.5f,0.5f,0.5f,1f,
                12,20,15,
                0.5f,0.5f,0.5f,1f,

//Исправленно
                //
                7,15,0,
                0.5f,0.5f,0.5f,1f,
                7,15,15,
                0.5f,0.5f,0.5f,1f,
                7,20,15,
                0.5f,0.5f,0.5f,1f,

                7,20,15,
                0.5f,0.5f,0.5f,1f,
                7,20,0,
                0.5f,0.5f,0.5f,1f,
                7,15,0,
                0.5f,0.5f,0.5f,1f,

//Исправленно
                //
                7,15,15,
                0.5f,0.5f,0.5f,1f,
                7,15,0,
                0.5f,0.5f,0.5f,1f,
                12,15,0,
                0.5f,0.5f,0.5f,1f,

                12,15,0,
                0.5f,0.5f,0.5f,1f,
                12,15,15,
                0.5f,0.5f,0.5f,1f,
                7,15,15,
                0.5f,0.5f,0.5f,1f,

//Исправленно
                //
                12,20,0,
                0.5f,0.5f,0.5f,1f,
                7,20,0,
                0.5f,0.5f,0.5f,1f,
                7,20,15,
                0.5f,0.5f,0.5f,1f,

                7,20,15,
                0.5f,0.5f,0.5f,1f,
                12,20,15,
                0.5f,0.5f,0.5f,1f,
                12,20,0,
                0.5f,0.5f,0.5f,1f,


//Исправленно
                //
                7,15,15,
                0.2f,0.2f,0.2f,1f,
                12,15,15,
                0.2f,0.2f,0.2f,1f,
                12,20,15,
                0.2f,0.2f,0.2f,1f,

                7,15,15,
                0.2f,0.2f,0.2f,1f,
                12,20,15,
                0.2f,0.2f,0.2f,1f,
                7,20,15,
                0.2f,0.2f,0.2f,1f,

//Исправленно
                //
                7,15,15,
                0.2f,0.2f,0.2f,1f,
                7,-15,15,
                0.2f,0.2f,0.2f,1f,
                12,-15,15,
                0.2f,0.2f,0.2f,1f,

                12,-15,15,
                0.2f,0.2f,0.2f,1f,
                12,15,15,
                0.2f,0.2f,0.2f,1f,
                7,15,15,
                0.2f,0.2f,0.2f,1f,

//Исправленно
                //
                7,15,13,
                0.2f,0.2f,0.2f,1f,
                7,-15,13,
                0.2f,0.2f,0.2f,1f,
                12,-15,13,
                0.2f,0.2f,0.2f,1f,

                12,-15,13,
                0.2f,0.2f,0.2f,1f,
                12,15,13,
                0.2f,0.2f,0.2f,1f,
                7,15,13,
                0.2f,0.2f,0.2f,1f,

                //
                7,15,15,
                0.5f,0.5f,0.5f,1f,
                7,15,13,
                0.5f,0.5f,0.5f,1f,
                7,-15,13,
                0.5f,0.5f,0.5f,1f,

                7,15,15,
                0.5f,0.5f,0.5f,1f,
                7,-15,13,
                0.5f,0.5f,0.5f,1f,
                7,-15,15,
                0.5f,0.5f,0.5f,1f,


                //
                12,15,15,
                0.5f,0.5f,0.5f,1f,
                12,-15,15,
                0.5f,0.5f,0.5f,1f,
                12,-15,13,
                0.5f,0.5f,0.5f,1f,

                12,-15,13,
                0.5f,0.5f,0.5f,1f,
                12,15,13,
                0.5f,0.5f,0.5f,1f,
                12,15,15,
                0.5f,0.5f,0.5f,1f,
        };

        float [] vertix = readFile.read_file();
        test_vertices = ByteBuffer.allocateDirect(vertix.length*4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        test_vertices.put(vertix).position(0);

//        verticesReady = ByteBuffer.allocateDirect(vertices.length * mBytesPerFloat).order(ByteOrder.nativeOrder()).asFloatBuffer();
//        verticesReady.put(vertices).position(0);
    }

    @Override
    public void onSurfaceCreated(GL10 glUnused, EGLConfig config) {
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        GLES20.glEnable(GLES20.GL_DEPTH_TEST);
        //Вершинный шейдер
        final String vertexShader =
                "uniform mat4 u_MVPMatrix;      \n"
                        + "attribute vec4 a_Position;     \n"
                        + "attribute vec4 a_Color;        \n"
                        + "varying vec4 v_Color;          \n"
                        + "void main()                    \n"
                        + "{                              \n"
                        + "   v_Color = a_Color;          \n"
                        + "   gl_Position = u_MVPMatrix   \n"
                        + "               * a_Position;   \n"
                        + "}                              \n";

        //Фрагментный шейдер
        final String fragmentShader =
                "precision mediump float;       \n"
                        + "varying vec4 v_Color;          \n"
                        + "void main()                    \n"
                        + "{                              \n"
                        + "   gl_FragColor = v_Color;     \n"
                        + "}                              \n";

        //считыватель шейдера вершинного
        int vertexShaderHandle = GLES20.glCreateShader(GLES20.GL_VERTEX_SHADER);
        //создание вершинного шейдера
        if (vertexShaderHandle != 0)
        {
            GLES20.glShaderSource(vertexShaderHandle, vertexShader);
            GLES20.glCompileShader(vertexShaderHandle);
            final int[] compileStatus = new int[1];
            GLES20.glGetShaderiv(vertexShaderHandle, GLES20.GL_COMPILE_STATUS, compileStatus, 0);

            if (compileStatus[0] == 0)
            {
                GLES20.glDeleteShader(vertexShaderHandle);
                vertexShaderHandle = 0;
            }
        }

        //Проверка на ошибку вершинного шейдера
        if (vertexShaderHandle == 0)
        {
            throw new RuntimeException("Error creating vertex shader.");
        }

        //считывание фрагментного шейдера
        int fragmentShaderHandle = GLES20.glCreateShader(GLES20.GL_FRAGMENT_SHADER);

        //создание фрагментного шейдера
        if (fragmentShaderHandle != 0)
        {
            GLES20.glShaderSource(fragmentShaderHandle, fragmentShader);
            GLES20.glCompileShader(fragmentShaderHandle);
            final int[] compileStatus = new int[1];
            GLES20.glGetShaderiv(fragmentShaderHandle, GLES20.GL_COMPILE_STATUS, compileStatus, 0);
            if (compileStatus[0] == 0)
            {
                GLES20.glDeleteShader(fragmentShaderHandle);
                fragmentShaderHandle = 0;
            }
        }

        //Проверка на фрагментного шейдера
        if (fragmentShaderHandle == 0)
        {
            throw new RuntimeException("Error creating fragment shader.");
        }

        //Создание программы из шейдеров
        colored_vertices_program_handle = GLES20.glCreateProgram();
        if (colored_vertices_program_handle != 0)
        {
            GLES20.glAttachShader(colored_vertices_program_handle, vertexShaderHandle);
            GLES20.glAttachShader(colored_vertices_program_handle, fragmentShaderHandle);
            GLES20.glBindAttribLocation(colored_vertices_program_handle, 0, "a_Position");
            GLES20.glBindAttribLocation(colored_vertices_program_handle, 1, "a_Color");
            GLES20.glLinkProgram(colored_vertices_program_handle);
            final int[] linkStatus = new int[1];
            GLES20.glGetProgramiv(colored_vertices_program_handle, GLES20.GL_LINK_STATUS, linkStatus, 0);
            if (linkStatus[0] == 0)
            {
                GLES20.glDeleteProgram(colored_vertices_program_handle);
                colored_vertices_program_handle = 0;
            }
        }
        //Проверка на ошибку создания программы
        if (colored_vertices_program_handle == 0)
        {
            throw new RuntimeException("Error creating program.");
        }
        //создание матрицы, позиции, и цвета
        MVPmatrix_location = GLES20.glGetUniformLocation(colored_vertices_program_handle, "u_MVPMatrix");
        mPositionHandle = GLES20.glGetAttribLocation(colored_vertices_program_handle, "a_Position");
        mColorHandle = GLES20.glGetAttribLocation(colored_vertices_program_handle, "a_Color");

        GLES20.glEnable(GLES20.GL_CULL_FACE);
        GLES20.glCullFace(GLES20.GL_BACK); //отключение прорисовки  "за" домом
        GLES20.glFrontFace(GLES20.GL_CW);//по часовой
    }

    @Override
    // задаем ViewPort
    public void onSurfaceChanged(GL10 glUnused, int width, int height) {
        //Первый вариант
        GLES20.glViewport(0,0,width,height);
        camera.setViewport(width,height);
    }

    boolean one_time=false;

    // рисуем
    @Override
    public void onDrawFrame(GL10 glUnused) {
        GLES20.glClear(GLES20.GL_DEPTH_BUFFER_BIT | GLES20.GL_COLOR_BUFFER_BIT);
        GLES20.glUseProgram(colored_vertices_program_handle);

        GLES20.glUniformMatrix4fv(MVPmatrix_location, 1, false,camera.getViewProjectionMatrix().values, 0);
        //drawColoredPoints(verticesReady, GLES20.GL_TRIANGLES, 0,30);
        drawColoredPoints(test_vertices, GLES20.GL_TRIANGLES, 0,399);
        //fpsCounter.logFrame();
    }

    //функция рисовки 3-ка
    private void drawColoredPoints(final FloatBuffer aTriangleBuffer, int render_type, int offset, int count)
    {
        // информация о позиции
        aTriangleBuffer.position(mPositionOffset);
        GLES20.glVertexAttribPointer(mPositionHandle, mPositionDataSize, GLES20.GL_FLOAT, false,
                mStrideBytes, aTriangleBuffer);
        GLES20.glEnableVertexAttribArray(mPositionHandle);
        // информация о цвете
        aTriangleBuffer.position(mColorOffset);
        GLES20.glVertexAttribPointer(mColorHandle, mColorDataSize, GLES20.GL_FLOAT, false,
                mStrideBytes, aTriangleBuffer);
        GLES20.glEnableVertexAttribArray(mColorHandle);
        //Вырисовываем 3-к
        GLES20.glDrawArrays(render_type, offset, count);
    }
}