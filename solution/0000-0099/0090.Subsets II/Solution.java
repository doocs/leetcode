class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums==null||nums.length==0) return res;
        Arrays.sort(nums);
        backTrack(res, nums, 0, new ArrayList<>());
        return res;
    }
    private void backTrack(List<List<Integer>> res, int[] nums, int index, List<Integer> ls){
        res.add(new ArrayList<>(ls));
        if(index>=nums.length) return;
        for(int i=index;i<nums.length;i++){
            ls.add(nums[i]);
            backTrack(res, nums, i+1, ls);
            ls.remove(ls.size()-1);
            while(i<nums.length-1 && nums[i+1]==nums[i]) i++;
        }
    }
}