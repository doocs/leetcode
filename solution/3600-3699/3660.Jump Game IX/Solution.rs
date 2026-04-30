impl Solution {
    pub fn max_value(nums: Vec<i32>) -> Vec<i32> {
        let n = nums.len();
        let mut ans = vec![0; n];
        let mut pre_max = vec![nums[0]; n];

        for i in 1..n {
            pre_max[i] = pre_max[i - 1].max(nums[i]);
        }

        let mut suf_min = i32::MAX;

        for i in (0..n).rev() {
            ans[i] = if i == n - 1 || pre_max[i] <= suf_min {
                pre_max[i]
            } else {
                ans[i + 1]
            };
            suf_min = suf_min.min(nums[i]);
        }

        ans
    }
}
