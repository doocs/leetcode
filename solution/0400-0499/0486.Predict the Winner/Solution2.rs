impl Solution {
    pub fn predict_the_winner(nums: Vec<i32>) -> bool {
        let n = nums.len();
        let mut f = vec![vec![0; n]; n];

        for i in 0..n {
            f[i][i] = nums[i];
        }

        for i in (0..n - 1).rev() {
            for j in i + 1..n {
                f[i][j] = std::cmp::max(nums[i] - f[i + 1][j], nums[j] - f[i][j - 1]);
            }
        }

        f[0][n - 1] >= 0
    }
}
