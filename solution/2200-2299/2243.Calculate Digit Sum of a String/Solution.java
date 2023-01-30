class Solution {
    public String digitSum(String s, int k) {
        while (s.length() > k) {
            int n = s.length();
            StringBuilder t = new StringBuilder();
            for (int i = 0; i < n; i += k) {
                int x = 0;
                for (int j = i; j < Math.min(i + k, n); ++j) {
                    x += s.charAt(j) - '0';
                }
                t.append(x);
            }
            s = t.toString();
        }
        return s;
    }
}