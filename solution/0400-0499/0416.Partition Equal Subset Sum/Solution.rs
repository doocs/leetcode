impl Solution {
    pub fn can_partition(nums: Vec<i32>) -> bool {
        let s: i32 = nums.iter().sum();
        if s % 2 != 0 {
            return false;
        }
        let m = (s / 2) as usize;
        let n = nums.len();
        let mut f = vec![vec![false; m + 1]; n + 1];
        f[0][0] = true;

        for i in 1..=n {
            let x = nums[i - 1] as usize;
            for j in 0..=m {
                f[i][j] = f[i - 1][j] || (j >= x && f[i - 1][j - x]);
            }
        }

        f[n][m]
    }
}
