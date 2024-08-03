class Solution {
    public List<Integer> peopleIndexes(List<List<String>> favoriteCompanies) {
        int n = favoriteCompanies.size();
        Map<String, Integer> d = new HashMap<>();
        int idx = 0;
        Set<Integer>[] nums = new Set[n];
        Arrays.setAll(nums, i -> new HashSet<>());
        for (int i = 0; i < n; ++i) {
            var ss = favoriteCompanies.get(i);
            for (var s : ss) {
                if (!d.containsKey(s)) {
                    d.put(s, idx++);
                }
                nums[i].add(d.get(s));
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            boolean ok = true;
            for (int j = 0; j < n && ok; ++j) {
                if (i != j && nums[j].containsAll(nums[i])) {
                    ok = false;
                }
            }
            if (ok) {
                ans.add(i);
            }
        }
        return ans;
    }
}
