class Solution {
    public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
        var cnt = new int[n];
        for (var e : edges) {
            ++cnt[e.get(1)];
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            if (cnt[i] == 0) {
                ans.add(i);
            }
        }
        return ans;
    }
}