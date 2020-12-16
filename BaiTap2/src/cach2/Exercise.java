/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cach2;

import static baitap2.Exercise.URL;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author HL
 */
public class Exercise {

    private ArrayList<Word> list;

    public String readFile(String fileName) {
//        String result = "";
        // đường dẫn file
        String fullPath = URL + fileName;
        File file = new File(fullPath);
        if (file != null) {

            // FileReader  đọc file từ  ổ đĩa
            // sử dụng luồng đọc file BufferedReader  để đệm đầu vào từ FileReader nhằm tối ưu tốc độ đọc từng dòng.
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                StringBuilder result = new StringBuilder();
                // đọc từng dòng đến khi kết thúc file
                while ((line = br.readLine()) != null) {
                    result.append(line);
                }
                // trả về kết quả  sau khi đọc từ file về dạng chuỗi
                return result.toString();

            } catch (IOException e) {
                e.printStackTrace();
                // trả về null nếu xảy ra ngoại lệ trong quá trình đọc file
                return null;
            }
        } else {
            // trả về null nếu file rỗng
            return null;
        }

    }

    public boolean checkExist(String word) {
        int size = this.list.size();
        int mid = (size - 1) / 2;

        for (int i = 0; i <= mid; i++) {

            if (list.get(i).getValue().equals(word)) {
                int number = list.get(i).getNumber() + 1;
                list.get(i).setNumber(number);
                return true;
            } else if (list.get(size - 1 - i).getValue().equals(word)) {
                int number = list.get(i).getNumber() + 1;
                list.get(i).setNumber(number);

                return true;
            }
        }
        return false;
    }

    public ArrayList<Word> countWord() {

        this.list = new ArrayList<>();
//         Word w = new Word();
        String str = readFile("demo.txt");

        if (str != null) {
            str = str.replaceAll("\\W", " ");
            System.out.println(str);
            for (String word : str.split(" ")) {
                if (list.size() > 0 && checkExist(word)) {
//                continue;
                } else {
                    list.add(new Word(word, false, 1));
                }
            }

        } else {
            System.out.println("chuỗi rỗng");
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).toString());
        }
        return null;
    }
}
