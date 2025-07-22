class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        Map<String, Integer> cnt = new HashMap<>();
        for (var w : words) {
            cnt.merge(w, 1, Integer::sum);
        }
        List<Integer> ans = new ArrayList<>();
        int m = s.length(), n = words.length, k = words[0].length();
        for (int i = 0; i < k; ++i) {
            int l = i, r = i;
            Map<String, Integer> cnt1 = new HashMap<>();
            while (r + k <= m) {
                var t = s.substring(r, r + k);
                r += k;
                if (!cnt.containsKey(t)) {
                    cnt1.clear();
                    l = r;
                    continue;
                }
                cnt1.merge(t, 1, Integer::sum);
                while (cnt1.get(t) > cnt.get(t)) {
                    String w = s.substring(l, l + k);
                    if (cnt1.merge(w, -1, Integer::sum) == 0) {
                        cnt1.remove(w);
                    }
                    l += k;
                }
                if (r - l == n * k) {
                    ans.add(l);
                }
            }
        }
        return ans;
    }
}
