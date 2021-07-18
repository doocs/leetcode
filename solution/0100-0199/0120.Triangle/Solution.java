class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < i + 1; ++j) {
                int mi = Integer.MAX_VALUE;
                if (j > 0) {
                    mi = Math.min(mi, triangle.get(i - 1).get(j - 1));
                }
                if (j < i) {
                    mi = Math.min(mi, triangle.get(i - 1).get(j));
                }
                triangle.get(i).set(j, triangle.get(i).get(j) + mi);
            }
        }
        int res = Integer.MAX_VALUE;
        for (int val : triangle.get(n - 1)) {
            res = Math.min(res, val);
        }
        return res;
    }
}