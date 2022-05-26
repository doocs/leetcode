class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
		for (int start = 0; start < nums.length; start++) {
        	int check = 0;
        	for (int i = start; i < nums.length; i++) {
        		check += nums[i];
                if (i > start) {
                	if (k != 0) {
            			if (check % k == 0) {
                    		return true;
                    	}
            		} else {
            			if (check == k) {
            				return true;
            			}
            		}
        		}
        	}
        }
        
        return false;
    }
}