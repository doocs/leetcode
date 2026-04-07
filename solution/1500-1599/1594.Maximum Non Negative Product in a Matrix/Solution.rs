impl Solution {
    pub fn max_product_path(grid: Vec<Vec<i32>>) -> i32 {
        let m = grid.len();
        let n = grid[0].len();
        let mut f = vec![vec![[0i64; 2]; n]; m];

        for i in 0..m {
            for j in 0..n {
                let x = grid[i][j] as i64;

                if i == 0 && j == 0 {
                    f[i][j] = [x, x];
                    continue;
                }

                let mut mn = i64::MAX;
                let mut mx = i64::MIN;

                if i > 0 {
                    let [a, b] = f[i - 1][j];
                    mn = mn.min(a * x).min(b * x);
                    mx = mx.max(a * x).max(b * x);
                }

                if j > 0 {
                    let [a, b] = f[i][j - 1];
                    mn = mn.min(a * x).min(b * x);
                    mx = mx.max(a * x).max(b * x);
                }

                f[i][j] = [mn, mx];
            }
        }

        let ans = f[m - 1][n - 1][1];
        let mod_val = 1_000_000_007i64;
        if ans < 0 {
            -1
        } else {
            (ans % mod_val) as i32
        }
    }
}
