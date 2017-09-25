package com.example.vladimir.sityinfov113;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by Vladimir on 01.09.2017.
 */

public class ReadFile {

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public float[] read_file() {
        float[] mass_vertices = new float[0];
        int j = 0;
        String fileName = "city.model";
        //String fileName = "city.txt";
        StringBuilder stringBuilder = null;
        Log.w("W", "СЧИТЫВАЕМ ФАЙЛ");

        File myFile = new File(Environment.getExternalStorageDirectory().toString() + "/Download/" + fileName);
//        try {
//            FileInputStream inputStream = new FileInputStream(myFile);
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//            stringBuilder = new StringBuilder();
//            String line;
//            //считываем построчно
//            try {
//                while ((line = bufferedReader.readLine()) != null) {
//                    stringBuilder.append(line);
//                    Log.w("W","ADD STRING IN FILE");
//                    j++;
//                    if(j == 10)
//                    {
//                        Log.w("W","FINISH I = " + j);
//                        break;
//                    }
//                }
//            }
//            catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//        catch (FileNotFoundException e) {
//            //e.printStackTrace();
//            Log.w("W", "ФАЙЛ НЕ НАЙДЕН");
//        }
//
//        //стринг билдер = файлу
//        Log.w("W", "stringbuilder = " + stringBuilder);
//        stringBuilder.toString();
//        Log.w("W", "stringbuilder = " + stringBuilder);
//        //заносим каждый элемент в массив после запятой EZ
//        String[] test_line_string = stringBuilder.toString().split(",");
//        mass_vertices = new float[test_line_string.length];
//        for(int i = 0; i < test_line_string.length; i++)
//            mass_vertices[i] = Float.parseFloat(test_line_string[i]);
//        //возвращаем готовый массив
//        return mass_vertices;
//    }
//
//        int i = 0;
//        //находим файл
//        try (FileInputStream fin = new FileInputStream(myFile)) {
//            //получаем размер
//            System.out.println("Размер файла: " + fin.available() + " байт");
//            //задаем размер для буфера
//            byte[] buffer = new byte[fin.available()];
//            //считываем данные в буфер
//            while ((i = fin.read()) != -1) {
//                fin.read(buffer, 0, fin.available());
//                System.out.print(fin);
//            }
//
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());
//        }
///*
//        for (int i = 0; i < buffer.length; i++) {
//            Log.w("E","VIVOD +" + buffer.length);
//            System.out.print((char) buffer[i]);
//        }
//*/
        byte[] buffer = new byte[0];
        try (FileInputStream fin = new FileInputStream(myFile)) {
            System.out.println("Размер файла: " + fin.available() + " байт");
            int i = -1;
            //задаем размер для буфера
            buffer = new byte[fin.available()];
            while ((i = fin.read()) != -1) {
                //замутить копирование в файл
                fin.read(buffer, 0, fin.available());
                //System.out.print((char) i);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        Log.e("E", "__________________________________");

//        String[] test_byte_line = buffer.toString().split(String.valueOf(buffer[0]));
//        Log.w("E", "VIVOD +" + test_byte_line);
        //String[] test_line_string = stringBuilder.toString().split(",");
//
//        for (int i = 0; i < buffer.length; i++) {
//            //Log.w("E", "VIVOD +" + buffer.length);
//            System.out.print((char) buffer[i]);
//        }
        return mass_vertices;
    }
}