class Solution {
    public int minOperations(String s) {
        int ans = 0;
        for (char c : s.toCharArray()) {
            if (c != 'a') {
                ans = Math.max(ans, 26 - (c - 'a'));
            }
        }
        return ans;
    }
}
