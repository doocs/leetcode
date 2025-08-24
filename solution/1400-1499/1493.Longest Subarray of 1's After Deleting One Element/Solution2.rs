impl Solution {
    pub fn longest_subarray(nums: Vec<i32>) -> i32 {
        let n = nums.len();
        let mut ans = 0;
        let mut j = 0;
        let mut cnt = 0;

        for i in 0..n {
            cnt += nums[i] ^ 1;
            while cnt > 1 {
                cnt -= nums[j] ^ 1;
                j += 1;
            }
            ans = ans.max(i - j);
        }

        ans as i32
    }
}
