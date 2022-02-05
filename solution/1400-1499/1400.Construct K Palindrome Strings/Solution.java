class Solution {
    public boolean canConstruct(String s, int k) {
        if (s.length() < k) {
            return false;
        }
        int[] counter = new int[26];
        for (char c : s.toCharArray()) {
            ++counter[c - 'a'];
        }
        int cnt = 0;
        for (int v : counter) {
            if (v % 2 == 1) {
                ++cnt;
            }
        }
        return cnt <= k;
    }
}