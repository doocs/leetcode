class Solution {
    public long countPairs(String[] words) {
        Map<String, Integer> cnt = new HashMap<>();
        long ans = 0;
        for (String s : words) {
            char[] t = s.toCharArray();
            int k = 'z' - t[0];
            for (int i = 1; i < t.length; i++) {
                t[i] = (char) ('a' + (t[i] - 'a' + k) % 26);
            }
            t[0] = 'z';
            cnt.merge(new String(t), 1, Integer::sum);
        }
        for (int v : cnt.values()) {
            ans += 1L * v * (v - 1) / 2;
        }
        return ans;
    }
}
