class Solution {
    public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
        Set<Integer> s = new HashSet<>();
        for (List<Integer> e : edges) {
            s.add(e.get(1));
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            if (!s.contains(i)) {
                ans.add(i);
            }
        }
        return ans;
    }
}