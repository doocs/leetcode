class Solution {
    public int wordsTyping(String[] sentence, int rows, int cols) {
        String s = String.join(" ", sentence) + " ";
        int m = s.length();
        int cur = 0;
        while (rows-- > 0) {
            cur += cols;
            if (s.charAt(cur % m) == ' ') {
                ++cur;
            } else {
                while (cur > 0 && s.charAt((cur - 1) % m) != ' ') {
                    --cur;
                }
            }
        }
        return cur / m;
    }
}