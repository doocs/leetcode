class Solution {
    public boolean isPossibleToRearrange(String s, String t, int k) {
        Map<String, Integer> cnt = new HashMap<>(k);
        int n = s.length();
        int m = n / k;
        for (int i = 0; i < n; i += m) {
            cnt.merge(s.substring(i, i + m), 1, Integer::sum);
            cnt.merge(t.substring(i, i + m), -1, Integer::sum);
        }
        for (int v : cnt.values()) {
            if (v != 0) {
                return false;
            }
        }
        return true;
    }
}
