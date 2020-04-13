import java.util.ArrayList;

public class Solution {

    public String[] findWords(String[] words) {
        if (words == null) {
            return null;
        }
        ArrayList<String> list = new ArrayList<>();
        String[] keyboards = {"qwertyuiop", "asdfghjkl", "zxcvbnm"};
        for (int i = 0; i < words.length; i++) {
            String word = words[i].toLowerCase();
            for (int j = 0; j < keyboards.length; j++) {
                // 先用word首字符确定属于哪一行
                if (keyboards[j].indexOf(word.charAt(0)) > -1) {
                    // 判断word字符串所有字符是否都属于同一行
                    boolean match = match(keyboards[j], word, list);
                    if (match) {
                        list.add(words[i]);
                    }
                    break;
                }
            }
        }
        return list.toArray(new String[list.size()]);
    }

    private boolean match(String keyboard, String word, ArrayList<String> list) {
        for (int i = 1; i < word.length(); i++) {
            if (keyboard.indexOf(word.charAt(i)) < 0) {
                return false;
            }
        }
        return true;
    }
}