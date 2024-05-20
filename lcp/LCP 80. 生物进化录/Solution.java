class Solution {
    private List<Integer>[] g;

    public String evolutionaryRecord(int[] parents) {
        int n = parents.length;
        g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int i = 1; i < n; ++i) {
            g[parents[i]].add(i);
        }
        return dfs(0).substring(1).replaceAll("1+$", "");
    }

    private String dfs(int i) {
        List<String> t = new ArrayList<>();
        for (int j : g[i]) {
            t.add(dfs(j));
        }
        Collections.sort(t);
        return "0" + String.join("", t) + "1";
    }
}