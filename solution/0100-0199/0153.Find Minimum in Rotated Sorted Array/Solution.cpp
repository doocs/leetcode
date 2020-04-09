class Solution {
public:
    int findMin(vector<int>& nums) {
        if (nums[0] <= nums.back())
            return nums[0] ;
        
        int l = 0, r = nums.size() - 1 ;
        
        while (l+1 < r)
        {
            const int mid = l + ((r-l) >> 1) ;
            if (nums[l] <= nums[mid])
                l = mid ;
            else
                r = mid ;
        }
        
        return nums.at(r) ;
    }
};
