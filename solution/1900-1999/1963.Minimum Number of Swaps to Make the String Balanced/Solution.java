class Solution {
    public int minSwaps(String s) {
        int x = 0;
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (c == '[') {
                ++x;
            } else if (x > 0) {
                --x;
            }
        }
        return (x + 1) / 2;
    }
}