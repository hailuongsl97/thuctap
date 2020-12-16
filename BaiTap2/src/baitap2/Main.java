/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baitap2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author HL
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Exercise exercise2 = new Exercise();
//        exercise2.sortWord();

        Scanner sc = new Scanner(System.in);

        int option = -1;

        do {
            System.out.println(" 1: Hiển thị tổng số từ trong file, số lần xuất hiện của các từ. "
                    + "\n 2: Sắp xếp các từ dựa vào số lần xuất hiện. \n 3: Tìm kiếm các từ dựa vào các chữ cái đầu tiên."
                    + " \n 4: Nhập 0 để kết thúc chương trình"
            );

            option = sc.nextInt();
            switch (option) {

                case 1: {
                    HashMap<String, Integer> hMap = exercise2.countWord();
                    Set<String> keySet = hMap.keySet();
                    for (String key : keySet) {
                        System.out.println(key + " - " + hMap.get(key));
                    }

                    break;
                }
                case 2: {
                    ArrayList<HashMap.Entry<String, Integer>> listWords = exercise2.sortWord();

                    for (int i = 0; i < listWords.size(); i++) {
                        System.out.println(listWords.get(i).getKey() + " " + listWords.get(i).getValue());
                    }

                    break;
                }
                case 3: {
                    sc.nextLine();
                    String keyword = "";
                    do {
                        System.out.println(" 1: Nhập từ cần tìm kiếm hoặc \n 2: Nhập số 0 để kết thúc tìm kiếm");

                        keyword = sc.nextLine();
                        exercise2.searchKeyword(keyword);

                    } while (keyword.equals("0") != true);
                    break;
                }
                default:
                    break;
            }

        } while (option != 0);

        sc.close();
    }

}
