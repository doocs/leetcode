class Solution {
    public String majorityFrequencyGroup(String s) {
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) {
            ++cnt[c - 'a'];
        }
        Map<Integer, StringBuilder> f = new HashMap<>();
        for (int i = 0; i < cnt.length; ++i) {
            if (cnt[i] > 0) {
                f.computeIfAbsent(cnt[i], k -> new StringBuilder()).append((char) ('a' + i));
            }
        }
        int mx = 0;
        int mv = 0;
        String ans = "";
        for (var e : f.entrySet()) {
            int v = e.getKey();
            var cs = e.getValue();
            if (mx < cs.length() || (mx == cs.length() && mv < v)) {
                mx = cs.length();
                mv = v;
                ans = cs.toString();
            }
        }
        return ans;
    }
}
