impl Solution {
    pub fn count_squares(matrix: Vec<Vec<i32>>) -> i32 {
        let m = matrix.len();
        let n = matrix[0].len();
        let mut f = vec![vec![0; n]; m];
        let mut ans = 0;

        for i in 0..m {
            for j in 0..n {
                if matrix[i][j] == 0 {
                    continue;
                }
                if i == 0 || j == 0 {
                    f[i][j] = 1;
                } else {
                    f[i][j] =
                        std::cmp::min(f[i - 1][j - 1], std::cmp::min(f[i - 1][j], f[i][j - 1])) + 1;
                }
                ans += f[i][j];
            }
        }

        ans
    }
}
