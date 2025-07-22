impl Solution {
    pub fn decompress_rl_elist(nums: Vec<i32>) -> Vec<i32> {
        let mut ans = Vec::new();
        let n = nums.len();
        let mut i = 0;
        while i < n {
            let freq = nums[i];
            let val = nums[i + 1];
            for _ in 0..freq {
                ans.push(val);
            }
            i += 2;
        }
        ans
    }
}
