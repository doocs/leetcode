class Solution {
    Map<Integer, List<Integer>> mp = new HashMap<>();
    int[] ans;
    int[] quiet;
    public int[] loudAndRich(int[][] richer, int[] quiet) {
        int n = quiet.length;
        ans = new int[n];
        Arrays.fill(ans, -1);
        this.quiet = quiet;
        for (int[] item : richer) {
            mp.computeIfAbsent(item[1], k -> new ArrayList<>()).add(item[0]);
        }
        for (int i = 0; i < n; i++) {
            dfs(i);
        }
        return ans;
    }

    public int dfs(int i) {
        if (ans[i] == -1) {
            ans[i] = i;
            for (int child : mp.getOrDefault(i, new ArrayList<>())) {
                int cand = dfs(child);
                if (quiet[cand] < quiet[ans[i]]) {
                    ans[i] = cand;
                }
            }
        }
        return ans[i];
    }
}