impl Solution {
    pub fn max_collected_fruits(fruits: Vec<Vec<i32>>) -> i32 {
        let n = fruits.len();
        let inf = 1 << 29;
        let mut f = vec![vec![-inf; n]; n];

        f[0][n - 1] = fruits[0][n - 1];
        for i in 1..n {
            for j in i + 1..n {
                f[i][j] = std::cmp::max(f[i - 1][j], f[i - 1][j - 1]) + fruits[i][j];
                if j + 1 < n {
                    f[i][j] = std::cmp::max(f[i][j], f[i - 1][j + 1] + fruits[i][j]);
                }
            }
        }

        f[n - 1][0] = fruits[n - 1][0];
        for j in 1..n {
            for i in j + 1..n {
                f[i][j] = std::cmp::max(f[i][j - 1], f[i - 1][j - 1]) + fruits[i][j];
                if i + 1 < n {
                    f[i][j] = std::cmp::max(f[i][j], f[i + 1][j - 1] + fruits[i][j]);
                }
            }
        }

        let mut ans = f[n - 2][n - 1] + f[n - 1][n - 2];
        for i in 0..n {
            ans += fruits[i][i];
        }

        ans
    }
}
