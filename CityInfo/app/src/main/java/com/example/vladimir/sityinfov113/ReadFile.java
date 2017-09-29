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
            fin.read(buffer, 0, fin.available());
            ByteBuffer bb = ByteBuffer.wrap(buffer);
            count_points = bb.getInt(); // кол-во точек
            bb.order(ByteOrder.LITTLE_ENDIAN);
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
            Log.w("W","кол-во счит байтов = " + p);
            bb.order(ByteOrder.BIG_ENDIAN);
            //забираем инт
            Log.w("W","кол-во цветных точек = " + bb.getInt());
            //забираем флоаты
            bb.order(ByteOrder.LITTLE_ENDIAN);
            Log.w("W","X = " + bb.getFloat());
            Log.w("W","Y = " + bb.getFloat());
            Log.w("W","Z = " + bb.getFloat());
            //берем цвет
            bb.order(ByteOrder.BIG_ENDIAN);
            Log.w("W","цвет = " + bb.getInt());
        }
        //Ловим ошибку при считывании
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        Log.e("E", "__________________________________");
        return mass_vertices;
    }
}