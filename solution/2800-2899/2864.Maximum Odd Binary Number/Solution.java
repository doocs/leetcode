class Solution {
    public String maximumOddBinaryNumber(String s) {
        int cnt = 0;
        for (char c : s.toCharArray()) {
            if (c == '1') {
                ++cnt;
            }
        }
        return "1".repeat(cnt - 1) + "0".repeat(s.length() - cnt) + "1";
    }
}