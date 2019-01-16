package com.company;
import java.io.*;


import java.util.*;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws Exception {
                readmap();
    }
    static HashMap<String, String>readmap () throws Exception {
        Scanner file = new Scanner(new File("src/test.txt"), "UTF-8");
        HashMap<String, String> map = new HashMap<String, String>();

        String line =file.toString();

        while (file.hasNextLine()) {

            String[] parts = line.split(" ", 2);
            if (parts.length >= 2)
            {
                String key = parts[0];
                String value = parts[1];
                map.put(key, value);
                return map;
            } else {
                System.out.println("ignoring line: " + line);
            }
        }



        return map;
    }
}