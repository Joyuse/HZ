package com.example.vladimir.sityinfov113;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Created by Vladimir on 01.09.2017.
 */

public class ReadFile {
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public float[] read_file(){
        float [] mass_vertices = new float[0];
        float [] mass_color_vertices = new float[0];
        int count_points;
        int count_mul;
        int color;
        String fileName = "city.model";
        Log.w("W", "СЧИТЫВАЕМ ФАЙЛ");
        //указали директорию файла
        File myFile = new File(Environment.getExternalStorageDirectory().toString() + "/Download/" + fileName);
        //пробумем считывать файл
        try (FileInputStream fin = new FileInputStream(myFile)) {
            //получаем размер файла
            System.out.println("Размер файла: " + fin.available() + " байт");
            //задаем размер для буфера
            byte[] buffer = new byte[fin.available()];
            //считываем и заносим в буфер
            fin.read(buffer, 0, 4);
            ByteBuffer bb = ByteBuffer.wrap(buffer);
            bb.order(ByteOrder.LITTLE_ENDIAN);
            count_points = bb.getInt(); // кол-во точек
            Log.w("W","кол-во точек = " + count_points);
            //переводим это кол-во в байты
            count_mul = count_points * 12;
            Log.w("W","кол-во байт которые занимают точки = " + count_mul);
            //обьявляем массив размерностью точек
            mass_vertices = new float[count_points * 3]; //NEW было count_mul стало  count_points * 3
            //Заносим в массив

            int p =0;
            for(int i = 0; i < count_points * 3 ; i ++) {
                mass_vertices[i] = bb.getFloat();
                p+=4;
            }
            //Проверка
            Log.w("W","X = " + mass_vertices[0]);
            Log.w("W","Y = " + mass_vertices[1]);
            Log.w("W","Z = " + mass_vertices[2]);
            Log.w("W","кол-во счит байтов = " + p);

            //забираем инт
            Log.w("W","кол-во цветных точек = " + bb.getInt());
           //забираем флоаты
            Log.w("W","X = " + bb.getFloat());
            Log.w("W","Y = " + bb.getFloat());
            Log.w("W","Z = " + bb.getFloat());
            //берем цвет
            //Log.w("W","цвет = " + Integer.);
        }
        //Ловим ошибку при считывании
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        Log.e("E", "__________________________________");
        return mass_vertices;
    }
}

//цвет = -1261015297

/*
09-29 12:04:00.526 9624-9624/com.example.vladimir.sityinfov113 W/W: СЧИТЫВАЕМ ФАЙЛ
09-29 12:04:00.526 9624-9624/com.example.vladimir.sityinfov113 I/System.out: Размер файла: 136507176 байт
09-29 12:04:00.996 9624-9636/com.example.vladimir.sityinfov113 W/art: Suspending all threads took: 13.269ms
09-29 12:04:01.426 9624-9624/com.example.vladimir.sityinfov113 W/W: кол-во точек = 793966
09-29 12:04:01.426 9624-9624/com.example.vladimir.sityinfov113 W/W: кол-во байт которые занимают точки = 9527592
09-29 12:04:01.946 9624-9624/com.example.vladimir.sityinfov113 W/W: X = 18230.195
09-29 12:04:01.946 9624-9624/com.example.vladimir.sityinfov113 W/W: Y = 21232.865
09-29 12:04:01.946 9624-9624/com.example.vladimir.sityinfov113 W/W: Z = 0.0
09-29 12:04:01.946 9624-9624/com.example.vladimir.sityinfov113 W/W: кол-во счит байтов = 9527592
09-29 12:04:01.946 9624-9624/com.example.vladimir.sityinfov113 W/W: кол-во цветных точек = 70255
09-29 12:04:01.946 9624-9624/com.example.vladimir.sityinfov113 W/W: X = 38541.46
09-29 12:04:01.946 9624-9624/com.example.vladimir.sityinfov113 W/W: Y = 10218.448
09-29 12:04:01.946 9624-9624/com.example.vladimir.sityinfov113 W/W: Z = 0.0
09-29 12:04:01.946 9624-9624/com.example.vladimir.sityinfov113 W/W: цвет = -9513292
*/
/*
W/W: СЧИТЫВАЕМ ФАЙЛ
09-29 12:05:53.640 13259-13259/com.example.vladimir.sityinfov113 I/System.out: Размер файла: 141387112 байт
09-29 12:06:02.830 13259-13259/com.example.vladimir.sityinfov113 W/W: кол-во точек = 793974
09-29 12:06:02.830 13259-13259/com.example.vladimir.sityinfov113 W/W: кол-во байт которые занимают точки = 9527688
09-29 12:06:03.610 13259-13259/com.example.vladimir.sityinfov113 W/W: X = 18230.195
09-29 12:06:03.610 13259-13259/com.example.vladimir.sityinfov113 W/W: Y = 21232.865
09-29 12:06:03.610 13259-13259/com.example.vladimir.sityinfov113 W/W: Z = 0.0
09-29 12:06:03.610 13259-13259/com.example.vladimir.sityinfov113 W/W: кол-во счит байтов = 9527688
09-29 12:06:03.610 13259-13259/com.example.vladimir.sityinfov113 W/W: кол-во цветных точек = 70256
09-29 12:06:03.610 13259-13259/com.example.vladimir.sityinfov113 W/W: X = 38541.46
09-29 12:06:03.610 13259-13259/com.example.vladimir.sityinfov113 W/W: Y = 10218.448
09-29 12:06:03.610 13259-13259/com.example.vladimir.sityinfov113 W/W: Z = 0.0
09-29 12:06:03.610 13259-13259/com.example.vladimir.sityinfov113 W/W: цвет = -1261015297
 */