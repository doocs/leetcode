class Solution {
    public List<List<Integer>> mergeSimilarItems(int[][] items1, int[][] items2) {
        int[] cnt = new int[1010];
        for (int[] e : items1) {
            cnt[e[0]] += e[1];
        }
        for (int[] e : items2) {
            cnt[e[0]] += e[1];
        }
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < cnt.length; ++i) {
            if (cnt[i] > 0) {
                ans.add(Arrays.asList(i, cnt[i]));
            }
        }
        return ans;
    }
}