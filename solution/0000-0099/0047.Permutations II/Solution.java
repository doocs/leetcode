class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        permute(list, nums, 0);
        return list;
    }
    
    private void permute(List<List<Integer>> list, int[] nums, int start) {
        int end = nums.length - 1;
        if (start == end) {
            List<Integer> tmp = new ArrayList<>();
            for (int val : nums) {
                tmp.add(val);
            }
            
            list.add(tmp);
            
        }
        
        for (int i = start; i <= end; ++i) {
            if (isSwap(nums, start, i)) {
                swap(nums, i, start);
                permute(list, nums, start + 1);
                swap(nums, i, start);
            }
            
        }
        
    }
    
    private boolean isSwap(int[] nums, int from, int to) {
        for (int i = from; i < to; ++i) {
            if (nums[i] == nums[to]) {
                // 相等，不进行交换
                return false;
            }
        }
        return true;
    }
    
    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}