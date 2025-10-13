impl Solution {
    pub fn max_increasing_subarrays(nums: Vec<i32>) -> i32 {
        let n = nums.len();
        let (mut ans, mut pre, mut cur) = (0, 0, 0);

        for i in 0..n {
            cur += 1;
            if i == n - 1 || nums[i] >= nums[i + 1] {
                ans = ans.max(cur / 2).max(pre.min(cur));
                pre = cur;
                cur = 0;
            }
        }

        ans
    }
}
