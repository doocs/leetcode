class Solution {
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] != nums[nums[i] - 1]) {
                swap(nums, i, nums[i] - 1);
                i--;
            }
        }
        
        System.out.println(Arrays.toString(nums));
        
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] != i + 1) {
                ans.add(nums[i]);
            }
        }
        
        return ans;
    }
    
    private void swap(int[] nums, int i, int j) {
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
	}
}