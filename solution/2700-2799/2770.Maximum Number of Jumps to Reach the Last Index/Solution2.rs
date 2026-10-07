impl Solution {
    pub fn maximum_jumps(nums: Vec<i32>, target: i32) -> i32 {
        let n = nums.len();
        let mut f = vec![-(1 << 30); n];
        f[n - 1] = 0;
        for i in (0..n - 1).rev() {
            for j in i + 1..n {
                if (nums[i] - nums[j]).abs() <= target {
                    f[i] = f[i].max(1 + f[j]);
                }
            }
        }
        if f[0] < 0 {
            -1
        } else {
            f[0]
        }
    }
}
