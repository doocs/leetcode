class Solution {
    public int countSymmetricIntegers(int low, int high) {
        int ans = 0;
        for (int x = low; x <= high; ++x) {
            ans += f(x);
        }
        return ans;
    }

    private int f(int x) {
        String s = "" + x;
        int n = s.length();
        if (n % 2 == 1) {
            return 0;
        }
        int a = 0, b = 0;
        for (int i = 0; i < n / 2; ++i) {
            a += s.charAt(i) - '0';
        }
        for (int i = n / 2; i < n; ++i) {
            b += s.charAt(i) - '0';
        }
        return a == b ? 1 : 0;
    }
}