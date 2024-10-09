impl Solution {
    pub fn longest_nice_subarray(nums: Vec<i32>) -> i32 {
        let mut ans = 0;
        let mut mask = 0;
        let mut l = 0;
        for (r, &x) in nums.iter().enumerate() {
            while mask & x != 0 {
                mask ^= nums[l];
                l += 1;
            }
            mask |= x;
            ans = ans.max((r - l + 1) as i32);
        }
        ans
    }
}
