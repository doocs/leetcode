class Solution {
    public boolean canConvert(String str1, String str2) {
        if (str1.equals(str2)) {
            return true;
        }
        int m = 0;
        int[] cnt = new int[26];
        int n = str1.length();
        for (int i = 0; i < n; ++i) {
            if (++cnt[str2.charAt(i) - 'a'] == 1) {
                ++m;
            }
        }
        if (m == 26) {
            return false;
        }
        int[] d = new int[26];
        for (int i = 0; i < n; ++i) {
            int a = str1.charAt(i) - 'a';
            int b = str2.charAt(i) - 'a';
            if (d[a] == 0) {
                d[a] = b + 1;
            } else if (d[a] != b + 1) {
                return false;
            }
        }
        return true;
    }
}