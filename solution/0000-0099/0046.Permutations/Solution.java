class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        permute(list, nums, 0);
        return list;
    }
    
    private void permute(List<List<Integer>> list, int[] nums, int start) {
        int end = nums.length - 1;
        if (start == end) {
            list.add(Arrays.stream(nums).boxed().collect(Collectors.toList()));
            return;
        }
        
        for (int i = start; i <= end; ++i) {
            swap(nums, i, start);
            permute(list, nums, start + 1);
            swap(nums, i, start);
        }
        
        
    }
    
    private static void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}