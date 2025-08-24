impl Solution {
    pub fn longest_subarray(nums: Vec<i32>) -> i32 {
        let mut cnt = 0;
        let mut l = 0;

        for &x in &nums {
            cnt += x ^ 1;
            if cnt > 1 {
                cnt -= nums[l] ^ 1;
                l += 1;
            }
        }

        (nums.len() - l - 1) as i32
    }
}
