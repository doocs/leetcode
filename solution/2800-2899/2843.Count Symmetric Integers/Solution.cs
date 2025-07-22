public class Solution {
    public int CountSymmetricIntegers(int low, int high) {
        int ans = 0;
        for (int x = low; x <= high; ++x) {
            ans += f(x);
        }
        return ans;
    }

    private int f(int x) {
        string s = x.ToString();
        int n = s.Length;
        if (n % 2 == 1) {
            return 0;
        }
        int a = 0, b = 0;
        for (int i = 0; i < n / 2; ++i) {
            a += s[i] - '0';
        }
        for (int i = n / 2; i < n; ++i) {
            b += s[i] - '0';
        }
        return a == b ? 1 : 0;
    }
}
