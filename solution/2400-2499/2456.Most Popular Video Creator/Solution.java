class Solution {
    public List<List<String>> mostPopularCreator(String[] creators, String[] ids, int[] views) {
        Map<String, Integer> cnt = new HashMap<>();
        Map<String, Integer> d = new HashMap<>();
        Map<String, String> x = new HashMap<>();
        int n = ids.length;
        for (int k = 0; k < n; ++k) {
            var c = creators[k];
            var i = ids[k];
            int v = views[k];
            cnt.put(c, cnt.getOrDefault(c, 0) + v);
            if (!d.containsKey(c) || d.get(c) < v || (d.get(c) == v && x.get(c).compareTo(i) > 0)) {
                d.put(c, v);
                x.put(c, i);
            }
        }
        List<List<String>> ans = new ArrayList<>();
        int pre = -1;
        for (var e : cnt.entrySet()) {
            String a = e.getKey();
            int b = e.getValue();
            if (b > pre) {
                ans.clear();
                ans.add(Arrays.asList(a, x.get(a)));
                pre = b;
            } else if (b == pre) {
                ans.add(Arrays.asList(a, x.get(a)));
            }
        }
        return ans;
    }
}