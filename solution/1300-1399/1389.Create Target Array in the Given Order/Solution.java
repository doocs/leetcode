class Solution {
    public int[] createTargetArray(int[] nums, int[] index) {
        int n = nums.length;
        List<Integer> target = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            target.add(index[i], nums[i]);
        }
        // return target.stream().mapToInt(i -> i).toArray();
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            ans[i] = target.get(i);
        }
        return ans;
    }
}