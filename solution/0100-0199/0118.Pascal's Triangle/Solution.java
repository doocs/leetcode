class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < numRows; ++i) {
            List<Integer> t = new ArrayList<>();
            for (int j = 0; j < i + 1; ++j) {
                int v = j == 0 || j == i ? 1 : ans.get(i - 1).get(j) + ans.get(i - 1).get(j - 1);
                t.add(v);
            }
            ans.add(t);
        }
        return ans;
    }
}