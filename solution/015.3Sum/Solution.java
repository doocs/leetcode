class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        int n = nums.length;
        if (n < 3) {
            return list;
        }
        int p = 0;
        int q = 0;
        for (int i = 0; i < n - 2; ++i) {
            if (nums[i] > 0) {
                break;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            
            p = i + 1;
            q = n - 1;
            
            while (p < q) {
                int val = nums[p] + nums[q] + nums[i];
                if (val == 0) {
                    list.add(Arrays.asList(nums[i], nums[p], nums[q]));
                    ++p;
                    while (p < q && nums[p] == nums[p - 1]) {
                        ++p;
                    }
                    --q;
                    while (p < q && nums[q] == nums[q + 1]) {
                        --q;
                    }
                    
                } else {
                    q = val > 0 ? q - 1 : q;
                    p = val < 0 ? p + 1 : p;
                }
                
            }  
        }
        return list;
        
    }
    
}