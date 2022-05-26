/*
 * @lc app=leetcode.cn id=53 lang=cpp
 *
 * [53] 最大子序和
 *
 * https://leetcode-cn.com/problems/maximum-subarray/description/
 *
 * algorithms
 * Easy (46.59%)
 * Likes:    2144
 * Dislikes: 0
 * Total Accepted:    270.6K
 * Total Submissions: 524.4K
 * Testcase Example:  '[-2,1,-3,4,-1,2,1,-5,4]'
 *
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * 
 * 示例:
 * 
 * 输入: [-2,1,-3,4,-1,2,1,-5,4],
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * 
 * 
 * 进阶:
 * 
 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 * 
 */

#include <vector>

using std::vector;

// 贪心
class Solution0 {
   public:
    int maxSubArray(vector<int>& nums) {
        if (nums.size() == 0) {
            return 0;
        }
        int result = nums[0];
        int temp = nums[0];
        for (int i = 1; i < nums.size(); i++) {
            if (temp >= 0) {
                temp += nums[i];
            } else {
                temp = nums[i];
            }
            result = std::max(result, temp);
        }
        return result;
    }
};

// @lc code=start
// 动态规划
class Solution {
   public:
    int maxSubArray(vector<int>& nums) {
        if (nums.size() == 0) {
            return 0;
        }
        vector<int> dp = vector<int>(nums.size());
        dp[0] = nums[0];
        int result = nums[0];
        for (int i = 1; i < nums.size(); i++) {
            dp[i] = std::max(dp[i - 1] + nums[i], nums[i]);
            result = std::max(dp[i], result);
        }
        return result;
    }
};
// @lc code=end
