class Solution {
    public boolean containsDuplicate(int[] nums) {
        if(nums == null || nums.length == 0) return false;
        Set<Integer> set = new HashSet<>();
        
        for(int v : nums) {
            if(set.contains(v)) return true;
            set.add(v);
        }
        
        return false;
    }
}