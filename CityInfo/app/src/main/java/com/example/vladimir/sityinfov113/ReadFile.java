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
    public float[] read_file() {
        float [] mass_vertices = new float[0];
        int count_points;
        int count_mul;
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
            ByteBuffer bb = ByteBuffer.wrap(buffer);
            //считываем
            fin.read(buffer, 0, fin.available());
            count_points = bb.getInt(); // кол-во точек
            Log.w("W","Кол-во точек = " + count_points);
            //переводим это кол-во в байты
            count_mul = count_points * 12;
            Log.w("W","кол-во байт = " + count_mul);
            //считываем по кол-во точек заносим точки в буфер
            fin.read(buffer, 4, count_mul);
            bb.order(ByteOrder.LITTLE_ENDIAN);
            //снова вылавливаем массив
            mass_vertices = new float[count_mul];
            //Заносим в массив
            for(int i = 0; i < count_mul; i ++){
                mass_vertices[i] =  bb.getFloat();
            }
            Log.w("W"," X= " +  mass_vertices[0]);
            Log.w("W"," Y= " +  mass_vertices[1]);
            Log.w("W"," Z= " +  mass_vertices[2]);
            Log.w("W","Кол-во точек цветных точек = " + bb.getInt());
        }
        //Ловим ошибку при считывании
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        Log.e("E", "__________________________________");
        return mass_vertices;
    }
}

