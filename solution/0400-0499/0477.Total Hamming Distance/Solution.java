class Solution {
    public int totalHammingDistance(int[] nums) {
        
        if (nums == null || nums.length < 2) {
			return 0;
		}
        
        int[] m = new int[31];// 存储对应位数，有多少个0
        for(int num : nums) {
        	for(int i = 0; i < 31; i++) {
        		if ((num & (1 << i)) == 0) {
					m[i]++;
				}
        	}
        }
        
        int result = 0;
        for(int i = 0; i < 31; i++) {
        	result += m[i] * (nums.length - m[i]);
        }
        
        return result;
    
    }
}