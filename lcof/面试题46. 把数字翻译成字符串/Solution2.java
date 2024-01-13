class Solution {
    public int translateNum(int num) {
        char[] s = String.valueOf(num).toCharArray();
        int n = s.length;
        int a = 1, b = 1;
        for (int i = 1; i < n; ++i) {
            int c = b;
            if (s[i - 1] == '1' || (s[i - 1] == '2' && s[i] < '6')) {
                c += a;
            }
            a = b;
            b = c;
        }
        return b;
    }
}