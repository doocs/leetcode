class Solution {
    public List<Integer> longestCommonSubsequence(int[][] arrays) {
        int[] cnt = new int[101];
        for (var row : arrays) {
            for (int x : row) {
                ++cnt[x];
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < 101; ++i) {
            if (cnt[i] == arrays.length) {
                ans.add(i);
            }
        }
        return ans;
    }
}
