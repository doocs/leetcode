class Solution {
    private List<List<Integer>> ans;
    private int[][] graph;

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        ans = new ArrayList<>();
        this.graph = graph;
        List<Integer> path = new ArrayList<>();
        path.add(0);
        dfs(0, path);
        return ans;
    }

    private void dfs(int i, List<Integer> path) {
        if (i == graph.length - 1) {
            ans.add(new ArrayList<>(path));
            return;
        }
        for (int j : graph[i]) {
            path.add(j);
            dfs(j, path);
            path.remove(path.size() - 1);
        }
    }
}