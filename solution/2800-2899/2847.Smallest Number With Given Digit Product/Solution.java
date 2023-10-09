class Solution {
    public String smallestNumber(long n) {
        int[] cnt = new int[10];
        for (int i = 9; i > 1; --i) {
            while (n % i == 0) {
                ++cnt[i];
                n /= i;
            }
        }
        if (n > 1) {
            return "-1";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 2; i < 10; ++i) {
            while (cnt[i] > 0) {
                sb.append(i);
                --cnt[i];
            }
        }
        String ans = sb.toString();
        return ans.isEmpty() ? "1" : ans;
    }
}