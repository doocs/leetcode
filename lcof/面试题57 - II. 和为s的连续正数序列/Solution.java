class Solution {
    public int[][] findContinuousSequence(int target) {
        int l = 1, r = 2;
        List<int[]> ans = new ArrayList<>();
        while (l < r) {
            int s = (l + r) * (r - l + 1) / 2;
            if (s == target) {
                int[] t = new int[r - l + 1];
                for (int i = l; i <= r; ++i) {
                    t[i - l] = i;
                }
                ans.add(t);
                ++l;
            } else if (s < target) {
                ++r;
            } else {
                ++l;
            }
        }
        return ans.toArray(new int[0][]);
    }
}