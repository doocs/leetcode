class Solution {
    public int[] createTargetArray(int[] nums, int[] index) {
        int n = nums.length;
        List<Integer> target = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            target.add(index[i], nums[i]);
        }
        int[] res = new int[n];
        for (int i = 0; i < n; ++i) {
            res[i] = target.get(i);
        }
        return res;
    }
}