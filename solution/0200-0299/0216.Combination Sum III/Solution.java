class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> ans = new ArrayList<>();
        robot(1, k, n, ans, new ArrayList<>());
        return ans;
    }

    private void robot(int start, int k, int left, List<List<Integer>> ans, List<Integer> tmp) {
        if (k < 0 || left < 0) return;

        if (k == 0 && left == 0) {
            ans.add(new ArrayList<>(tmp));
            return;
        }

        for (int i = start; i <= 9; i++) {
            if (left >= i && k > 0) {
                tmp.add(i);
                robot(i + 1, k - 1, left - i, ans, tmp);
                tmp.remove(tmp.size() - 1);
            } else {
                return;
            }
        }
    }
}