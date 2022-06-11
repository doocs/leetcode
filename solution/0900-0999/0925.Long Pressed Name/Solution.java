class Solution {
    public boolean isLongPressedName(String name, String typed) {
        int m = name.length(), n = typed.length();
        int i = 0, j = 0;
        for (; i < m && j < n; ++i, ++j) {
            if (name.charAt(i) != typed.charAt(j)) {
                return false;
            }
            int cnt1 = 0, cnt2 = 0;
            char c = name.charAt(i);
            while (i + 1 < m && name.charAt(i + 1) == c) {
                ++i;
                ++cnt1;
            }
            while (j + 1 < n && typed.charAt(j + 1) == c) {
                ++j;
                ++cnt2;
            }
            if (cnt1 > cnt2) {
                return false;
            }
        }
        return i == m && j == n;
    }
}