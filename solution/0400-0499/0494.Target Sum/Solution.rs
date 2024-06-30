impl Solution {
    pub fn find_target_sum_ways(nums: Vec<i32>, target: i32) -> i32 {
        let s: i32 = nums.iter().sum();
        if s < target || (s - target) % 2 != 0 {
            return 0;
        }
        let m = nums.len();
        let n = ((s - target) / 2) as usize;
        let mut f = vec![vec![0; n + 1]; m + 1];
        f[0][0] = 1;
        for i in 1..=m {
            for j in 0..=n {
                f[i][j] = f[i - 1][j];
                if j as i32 >= nums[i - 1] {
                    f[i][j] += f[i - 1][j - nums[i - 1] as usize];
                }
            }
        }
        f[m][n]
    }
}
