class Solution {
    public int minimumOperationsToMakeKPeriodic(String word, int k) {
        Map<String, Integer> cnt = new HashMap<>();
        int n = word.length();
        int mx = 0;
        for (int i = 0; i < n; i += k) {
            mx = Math.max(mx, cnt.merge(word.substring(i, i + k), 1, Integer::sum));
        }
        return n / k - mx;
    }
}