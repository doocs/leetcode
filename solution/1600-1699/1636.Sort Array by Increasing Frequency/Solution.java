class Solution {
    public int[] frequencySort(int[] nums) {
        int[] cnt = new int[201];
        for (int v : nums) {
            ++cnt[v + 100];
        }
        List<int[]> t = new ArrayList<>();
        for (int i = 0; i < cnt.length; ++i) {
            if (cnt[i] > 0) {
                t.add(new int[]{cnt[i], i});
            }
        }
        t.sort((a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
        int[] ans = new int[nums.length];
        int i = 0;
        for (int[] e : t) {
            for (int j = 0; j < e[0]; ++j) {
                ans[i++] = e[1] - 100;
            }
        }
        return ans;
    }
}