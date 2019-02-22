class Solution {
public:
    int findKthLargest(vector<int>& nums, int k) {
        --k ;
        int ii = 0, jj = nums.size()-1 ;
        int i, j ;
        while (true)
        {
            i = ii, j = jj ;
            if (i + 63 < j)
            {
                const int mid = i + (j-i)/2 ;
                if (nums[j] >= nums[mid] && nums[j] <= nums[i]
                   || nums[j] <= nums[mid] && nums[j] >= nums[i])
                {
                    swap(nums[i], nums[j]) ;
                }
                else if (nums[mid] >= nums[i] && nums[mid] <= nums[j]
                   || nums[mid] <= nums[i] && nums[mid] >= nums[j])
                {
                    swap(nums[i], nums[mid]) ;
                }
            }
            while (i < j)
            {
                while (i < j && nums.at(i) >= nums.at(j))
                    --j ;
                swap(nums[i], nums[j]) ;
                while (i < j && nums.at(i) >= nums.at(j))
                    ++i ;
                swap(nums[i], nums[j]) ;
            }
            
            if (i > k)
                jj = i-1 ;
            else if (i < k)
                ii = i+1 ;
            else
                return nums[k] ;
        }
        
        return nums.at(k) ;
    }
};
