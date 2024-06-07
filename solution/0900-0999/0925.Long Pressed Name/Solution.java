class Solution {
    public boolean isLongPressedName(String name, String typed) {
        int m = name.length(), n = typed.length();
        int i = 0, j = 0;
        while (i < m && j < n) {
            if (name.charAt(i) != typed.charAt(j)) {
                return false;
            }
            int x = i + 1;
            while (x < m && name.charAt(x) == name.charAt(i)) {
                ++x;
            }
            int y = j + 1;
            while (y < n && typed.charAt(y) == typed.charAt(j)) {
                ++y;
            }
            if (x - i > y - j) {
                return false;
            }
            i = x;
            j = y;
        }
        return i == m && j == n;
    }
}