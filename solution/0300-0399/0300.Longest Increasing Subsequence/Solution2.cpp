class Solution {
// dp[i] 表示当前长度为i+1的最大升序左侧子序列的最小末尾值
// 可以知道dp[]一定是递增的，每多加一个元素就可以用折半查找更新dp
public:
    int lengthOfLIS(vector<int>& nums) {
        if (nums.size() == 0)
            return 0 ;
        int dp[nums.size()] = {nums[0], };
        int maxLen = 1 ; // 刚开始找到的最大左侧子串是第一个元素，长度为1
        for (int i = 1; i < nums.size(); ++i)
        {
            int l = 0, r = maxLen ;
            while (l < r)
            {
                const int mid = l + (r-l)/2 ;
                if (dp[mid] < nums[i])
                    l = mid+1 ;
                else
                    r = mid ;
            }
            
            // 长度为l+1的最大上升子序列的最小末尾值可以更新为该元素了
            dp[l] = nums[i] ;
            
            // 如果当前元素比dp中所有元素都大，最大长度就可以+1，并以该元素结尾
            if (l == maxLen)    
                ++maxLen ;
        }
        return maxLen ;
    }
};
