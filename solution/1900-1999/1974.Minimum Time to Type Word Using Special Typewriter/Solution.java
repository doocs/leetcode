class Solution {
    public int minTimeToType(String word) {
        int ans = 0;
        int prev = 0;
        for (char c : word.toCharArray()) {
            int curr = c - 'a';
            int t = Math.abs(prev - curr);
            t = Math.min(t, 26 - t);
            ans += t + 1;
            prev = curr;
        }
        return ans;
    }
}