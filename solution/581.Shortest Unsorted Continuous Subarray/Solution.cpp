// i自左向右找到第一个逆序，
// j自右向左找到第一个逆序
// 找到nums[i, j]的上下界
// ij向外扩展找到满足上下界的最小[i, j]
class Solution {
public:
    int findUnsortedSubarray(vector<int>& nums) {
        if (1 == nums.size())
            return 0 ;
        
        int i = 0, j = nums.size()-1 ;
        while (i < j && nums[i] <= nums[i+1])
            ++i ;
        if (i >= j)
            return 0 ;
        
        while (nums[j-1] <= nums[j])
            --j ;
        
        //cout << i << ' ' << j << endl ;
        int m = nums[i] ;
        int M = m ;
        for (int k = i; k <= j; ++k)
        {
            if (m > nums[k])
                m = nums[k] ;
            if (M < nums[k])
                M = nums[k] ;
        }
        //cout << m << ' ' << M << endl ;
        
        while (i >= 0 && m < nums[i])
            --i ;
        while (j < nums.size() && M > nums[j])
            ++j ;
        
        //cout << i << ' ' << j << endl ;
        return j-i-1 ;
    }
};