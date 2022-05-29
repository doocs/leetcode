class Solution {
    public boolean digitCount(String num) {
        int[] cnt = new int[10];
        for (char c : num.toCharArray()) {
            ++cnt[c - '0'];
        }
        for (int i = 0; i < num.length(); ++i) {
            int v = num.charAt(i) - '0';
            if (cnt[i] != v) {
                return false;
            }
        }
        return true;
    }
}