class Solution {
    public List<Integer> intersection(int[][] nums) {
        int[] cnt = new int[1001];
        for (int[] num : nums) {
            for (int i : num) {
                cnt[i]++;
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 1; i <= 1000; i++) {
            if (cnt[i] == nums.length) {
                ans.add(i);
            }
        }
        return ans;
    }
}