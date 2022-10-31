class Solution {
    public String getSmallestString(int n, int k) {
        char[] ans = new char[n];
        Arrays.fill(ans, 'a');
        int i = n - 1;
        int d = k - n;
        while (d > 25) {
            ans[i--] = 'z';
            d -= 25;
        }
        ans[i] = (char) ('a' + d);
        return String.valueOf(ans);
    }
}