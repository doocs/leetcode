class Solution {
    public boolean digitCount(String num) {
        int[] cnt = new int[10];
        int n = num.length();
        for (int i = 0; i < n; ++i) {
            ++cnt[num.charAt(i) - '0'];
        }
        for (int i = 0; i < n; ++i) {
            if (num.charAt(i) - '0' != cnt[i]) {
                return false;
            }
        }
        return true;
    }
}