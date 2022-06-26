class Solution {
    public int countAsterisks(String s) {
        int ans = 0, t = 0;
        for (char c : s.toCharArray()) {
            if (c == '|') {
                t ^= 1;
            } else if (c == '*') {
                if (t == 0) {
                    ++ans;
                }
            }
        }
        return ans;
    }
}