class Solution {
    public String getHappyString(int n, int k) {
        if (k > 3 * (1 << (n - 1))) {
            return "";
        }
        String cs = "abc";
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int remain = 1 << (n - i - 1);
            for (char c : cs.toCharArray()) {
                if (ans.length() > 0 && ans.charAt(ans.length() - 1) == c) {
                    continue;
                }
                if (k <= remain) {
                    ans.append(c);
                    break;
                }
                k -= remain;
            }
        }
        return ans.toString();
    }
}
