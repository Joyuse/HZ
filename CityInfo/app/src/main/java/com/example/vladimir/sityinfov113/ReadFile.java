package com.example.vladimir.sityinfov113;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * Created by Vladimir on 01.09.2017.
 */

public class ReadFile {
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public float[] read_file() {
        float [] mass_vertices = new float[800000];
        int count_points;
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
            byte byte_count;
            ByteBuffer bb = ByteBuffer.wrap(buffer);
            //считываем
            fin.read(buffer, 0, fin.available());
            Log.w("W","Кол-во точек = " + bb.getInt());
            count_points = bb.getInt(); // кол-во точек
            //переводим это колво в байты
            byte_count = (byte) count_points;
            //считываем по кол-во точек заносим кол-во точки в буфер
            fin.read(buffer, 4, byte_count * 12);
            //снова вылавливаем массив
            ByteBuffer bb2 = ByteBuffer.wrap(buffer);
            //Определяем размер массива
            mass_vertices = new float[count_points];
            for(int i = 0; i < count_points; i ++)
                mass_vertices[i] =  bb2.getFloat();
            Log.w("W","кол-во точек №2 = " + bb.getInt());
        }
        //Ловим ошибку при считывании
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        Log.e("E", "__________________________________");

        return mass_vertices;
    }
}

