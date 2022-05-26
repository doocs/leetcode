class Solution {
    public String multiply(String num1, String num2) {
        int m = num1.length(), n = num2.length();
        int[] res = new int[m + n];
        for (int i = 0; i < n; ++i) {
            int b = num2.charAt(n - 1 - i) - '0';
            mul(num1, b, i, res);
        }
        StringBuilder ans = new StringBuilder();
        for (int v : res) {
            ans.append(v);
        }
        while (ans.length() > 1 && ans.charAt(ans.length() - 1) == '0') {
            ans.deleteCharAt(ans.length() - 1);
        }
        return ans.reverse().toString();
    }

    private void mul(String A, int b, int i, int[] res) {
        for (int j = A.length() - 1, t = 0; j >= 0 || t > 0; --j) {
            if (j >= 0) {
                int a = A.charAt(j) - '0';
                t += a * b;
            }
            res[i++] += t % 10;
            if (res[i - 1] >= 10) {
                res[i - 1] %= 10;
                ++res[i];
            }
            t /= 10;
        }
    }
}