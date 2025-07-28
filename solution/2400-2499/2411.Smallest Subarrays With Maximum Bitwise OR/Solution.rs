impl Solution {
    pub fn smallest_subarrays(nums: Vec<i32>) -> Vec<i32> {
        let n = nums.len();
        let mut ans = vec![1; n];
        let mut f = vec![-1; 32];

        for i in (0..n).rev() {
            let mut t = 1;
            for j in 0..32 {
                if (nums[i] >> j) & 1 != 0 {
                    f[j] = i as i32;
                } else if f[j] != -1 {
                    t = t.max(f[j] - i as i32 + 1);
                }
            }
            ans[i] = t;
        }

        ans
    }
}
