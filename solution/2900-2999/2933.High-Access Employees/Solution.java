class Solution {
    public List<String> findHighAccessEmployees(List<List<String>> access_times) {
        Map<String, List<Integer>> d = new HashMap<>();
        for (var e : access_times) {
            String name = e.get(0), s = e.get(1);
            int t = Integer.valueOf(s.substring(0, 2)) * 60 + Integer.valueOf(s.substring(2));
            d.computeIfAbsent(name, k -> new ArrayList<>()).add(t);
        }
        List<String> ans = new ArrayList<>();
        for (var e : d.entrySet()) {
            String name = e.getKey();
            var ts = e.getValue();
            Collections.sort(ts);
            for (int i = 2; i < ts.size(); ++i) {
                if (ts.get(i) - ts.get(i - 2) < 60) {
                    ans.add(name);
                    break;
                }
            }
        }
        return ans;
    }
}