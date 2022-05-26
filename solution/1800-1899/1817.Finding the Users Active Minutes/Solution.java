class Solution {
    public int[] findingUsersActiveMinutes(int[][] logs, int k) {
        Map<Integer, Set<Integer>> d = new HashMap<>();
        for (int[] log : logs) {
            int u = log[0], t = log[1];
            d.computeIfAbsent(u, key -> new HashSet<>()).add(t);
        }
        int[] ans = new int[k];
        for (Set<Integer> ts : d.values()) {
            ++ans[ts.size() - 1];
        }
        return ans;
    }
}