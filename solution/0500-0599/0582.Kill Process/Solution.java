class Solution {
    private Map<Integer, List<Integer>> g;
    private List<Integer> ans;

    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        g = new HashMap<>();
        for (int i = 0, n = pid.size(); i < n; ++i) {
            int c = pid.get(i), p = ppid.get(i);
            g.computeIfAbsent(p, k -> new ArrayList<>()).add(c);
        }
        ans = new ArrayList<>();
        dfs(kill);
        return ans;
    }

    private void dfs(int u) {
        ans.add(u);
        for (int v : g.getOrDefault(u, new ArrayList<>())) {
            dfs(v);
        }
    }
}