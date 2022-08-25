class Solution {
    public List<Integer> peopleIndexes(List<List<String>> favoriteCompanies) {
        Map<String, Integer> d = new HashMap<>();
        int idx = 0;
        int n = favoriteCompanies.size();
        Set<Integer>[] t = new Set[n];
        for (int i = 0; i < n; ++i) {
            var v = favoriteCompanies.get(i);
            for (var c : v) {
                if (!d.containsKey(c)) {
                    d.put(c, idx++);
                }
            }
            Set<Integer> s = new HashSet<>();
            for (var c : v) {
                s.add(d.get(c));
            }
            t[i] = s;
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            boolean ok = true;
            for (int j = 0; j < n; ++j) {
                if (i != j) {
                    if (t[j].containsAll(t[i])) {
                        ok = false;
                        break;
                    }
                }
            }
            if (ok) {
                ans.add(i);
            }
        }
        return ans;
    }
}