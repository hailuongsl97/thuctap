/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baitap2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author HL
 */
public class Exercise {

    public static final String URL = "C:\\Users\\HL\\Desktop\\images\\";
    // kích thước đầu vào bộ đệm
    public static final int SIZE_BUFFER_16KB = 16384;// 16*1024
    public static final int SIZE_BUFFER_32KB = 32768;// 32*1024
    private ArrayList<HashMap.Entry<String, Integer>> listWords;// danh sách các từ sau khi sắp xếp
    private String words;

// đọc file từ ổ đĩa
    private String readFile(String fileName) {

        // đường dẫn file
        String fullPath = URL + fileName;
        File file = new File(fullPath);
        if (file.exists()) {
            long fTime = System.currentTimeMillis();
            // FileReader  đọc file từ  ổ đĩa
            // sử dụng luồng đọc file BufferedReader  để đệm đầu vào từ FileReader nhằm tối ưu tốc độ đọc từng dòng.
            // đối số thứ 2 là giá trị bộ đệm, vì mặc định khi không truyền đối số bộ đệm sẽ nhận giá trị là 8 KiloByte
            // do đó có thể tăng giảm kích thước bộ đệm để cải thiện tốc độ đọc(kích thước bộ đệm phụ thuộc các yếu tố như luồng vào và phần cứng)
            BufferedReader br = null;
            try {//
                br = new BufferedReader(new FileReader(file),SIZE_BUFFER_16KB);
                String line;
                StringBuilder result = new StringBuilder();
                // đọc từng dòng đến khi kết thúc file
                while ((line = br.readLine()) != null) {
                    result.append(line + "\n");
                }
                // trả về kết quả  sau khi đọc từ file về dạng chuỗi
                System.out.println("thời gian đọc file:" + (System.currentTimeMillis() - fTime));
                br.close();
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

    public void searchKeyword(String keyword) {
        int count = 0;

        StringBuilder sb = new StringBuilder();

        // kiểm tra danh sách đã sắp xếp chưa
        if (listWords == null) {
            sortWord();
        }
      
        if (listWords!=null&&keyword.equals("") != true) {
            long fTime = System.currentTimeMillis();
            for (HashMap.Entry entry : listWords) {
                // nếu giá trị =5 thì kết thúc tìm kiếm
                if (count == 5) {
                    break;
                }
                if (entry != null) {
                    if (entry.getKey().toString().startsWith(keyword)) {// so sánh giá trị theo tiền tố
                        count++;
//                            System.out.println(entry.getKey().toString());
                        sb.append("Từ khóa : " + entry.getKey().toString() + " | Số lượng :" + entry.getValue().toString() + "\n");
                    }
                }

            }
            long lTime = System.currentTimeMillis();
            System.out.println("thời gian tìm kiếm: " + (lTime - fTime));
        }

        // nếu biến đếm vẫn bằng không thì k có kết quả trùng khớp
        if (count == 0) {
            System.out.println("không tìm thấy kết quả");
        } else {
            System.out.println(sb.toString());
        }

    }

    // đếm số từ
 
    public HashMap<String, Integer> countWord() {
        
         // lấy chuỗi từ file BaiTap.txt
         if(words==null)
         words = readFile("BaiTap.txt");
        // biến để đếm tổng số từ 
        int count = 0;
        // đối số regex(Regular Expression) là \W hoặc  [^\w]
        // có tác dụng so sánh trùng khớp tất cả các kí tự đặc biệt ngoại trừ chữ cái và số
        // chữ cái và số được định nghĩa trong bảng mã ASII có giá trị thập phân từ 48->57 và 65->90, 97->122
        // sử dụng replaceAll để thay thế kí tự đặc biệt thành space
        long fTime = System.currentTimeMillis();
        if (words != null) {
            // [^\w]
            words = words.replaceAll("\\W", " ");
//            System.out.println(str);
            HashMap<String, Integer> map = new HashMap<>();

            for (String word : words.split(" ")) {
                // nếu từ trên là rỗng thì bỏ qua
                if (word.isEmpty()) {
                    continue;
                } // nếu từ trên đã có trong hasmap thì tăng giá trị lên 1
                else if (map.containsKey(word)) {
                    count++;
                    map.put(word, map.get(word) + 1);
                } // nếu chưa có thì cho vào hashmap và cho giá trị ban đầu là 1
                else {
                    count++;
                    map.put(word, 1);
                }

            }
            System.out.println("Tổng số từ đếm trong file là: " + count);
//                System.out.println("thời gian đếm: " +  (System.currentTimeMillis()-fTime) );
            return map;
        } else {
           
            return null;
        }

    }

    public void writeFile(String nameFile) {

    }
    // sắp xếp các từ theo thứ tự từ lớn đến nhỏ.

    public ArrayList sortWord() {

       
        // nếu danh sách đã được sắp xếp
           if(listWords!=null)
          return listWords;
      
       
//        ArrayList<HashMap.Entry<String, Integer>> listWords= null;
        long fTime = System.currentTimeMillis();
         HashMap<String, Integer> map = countWord();
        if (map != null) {
             // Entry là đối tượng lưu giữ cặp generic KEY, VALUE
            listWords = new ArrayList<HashMap.Entry<String, Integer>>(map.entrySet());
            // sử dụng phương thức   Collections.sort 
            //kết hợp đối tượng nạc danh Comparator để ghi đè phương thức compare sau đó so sánh giá trị đếm dc
            // ở  Collections.sort  sử dụng thuật toán Merge sort
            Collections.sort(listWords, new Comparator<Map.Entry<String, Integer>>() {
                @Override
                public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                    return o2.getValue().compareTo(o1.getValue());// so sánh 2 giá trị của đối tượng theo thứ tự tăng dần 
                    // -1 nhỏ hơn, 0 bằng, 1 lớn đảo ngược vị trí để thay đổi thứ tự sắp xếp
                    //  return (x < y) ? -1 : ((x == y) ? 0 : 1);
                }
            });
        }

//        System.out.println("Tổng số t : " + listWord.size());
        System.out.println("Thời gian sắp xếp : " + (System.currentTimeMillis() - fTime));
//        this.listWords = listWords;
//        for (int i = 0; i < listWords.size(); i++) {
//            System.out.println(listWords.get(i).getKey() + " " + listWords.get(i).getValue());
//        }
        return listWords;
    }

}
