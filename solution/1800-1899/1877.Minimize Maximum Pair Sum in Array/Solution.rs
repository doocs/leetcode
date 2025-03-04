impl Solution {
    pub fn min_pair_sum(nums: Vec<i32>) -> i32 {
        let mut nums = nums;
        nums.sort();
        let mut ans = 0;
        let n = nums.len();
        for i in 0..n / 2 {
            ans = ans.max(nums[i] + nums[n - i - 1]);
        }
        ans
    }
}
