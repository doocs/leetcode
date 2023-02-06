class Solution {
    public int distinctAverages(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        Set<Integer> s = new HashSet<>();
        for (int i = 0; i<n> > 1; ++i) {
            s.add(nums[i] + nums[n - i - 1]);
        }
        return s.size();
    }
}