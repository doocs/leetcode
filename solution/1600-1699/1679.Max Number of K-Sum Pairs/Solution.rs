impl Solution {
    pub fn max_operations(nums: Vec<i32>, k: i32) -> i32 {
        let mut nums = nums.clone();
        nums.sort();
        let (mut l, mut r, mut ans) = (0, nums.len() - 1, 0);
        while l < r {
            match nums[l] + nums[r] {
                sum if sum == k => {
                    ans += 1;
                    l += 1;
                    r -= 1;
                }
                sum if sum > k => {
                    r -= 1;
                }
                _ => {
                    l += 1;
                }
            }
        }
        ans
    }
}
