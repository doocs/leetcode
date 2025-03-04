class Solution {
    public int maxDifference(String s) {
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) {
            ++cnt[c - 'a'];
        }
        int a = 0, b = 1 << 30;
        for (int v : cnt) {
            if (v % 2 == 1) {
                a = Math.max(a, v);
            } else if (v > 0) {
                b = Math.min(b, v);
            }
        }
        return a - b;
    }
}
