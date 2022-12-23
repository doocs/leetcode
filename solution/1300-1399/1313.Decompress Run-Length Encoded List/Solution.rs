impl Solution {
    pub fn decompress_rl_elist(nums: Vec<i32>) -> Vec<i32> {
        let n = nums.len() >> 1;
        let mut ans = Vec::new();
        for i in 0..n {
            for _ in 0..nums[2 * i] {
                ans.push(nums[2 * i + 1]);
            }
        }
        ans
    }
}
