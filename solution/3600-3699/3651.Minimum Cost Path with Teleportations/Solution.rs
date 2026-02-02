use std::collections::HashMap;

impl Solution {
    pub fn min_cost(grid: Vec<Vec<i32>>, k: i32) -> i32 {
        let m = grid.len();
        let n = grid[0].len();
        let k = k as usize;
        let inf: i32 = i32::MAX / 2;

        let mut f = vec![vec![vec![inf; n]; m]; k + 1];

        f[0][0][0] = 0;
        for i in 0..m {
            for j in 0..n {
                if i > 0 {
                    f[0][i][j] = f[0][i][j].min(f[0][i - 1][j] + grid[i][j]);
                }
                if j > 0 {
                    f[0][i][j] = f[0][i][j].min(f[0][i][j - 1] + grid[i][j]);
                }
            }
        }

        let mut g: HashMap<i32, Vec<(usize, usize)>> = HashMap::new();
        for i in 0..m {
            for j in 0..n {
                g.entry(grid[i][j]).or_default().push((i, j));
            }
        }

        let mut keys: Vec<i32> = g.keys().cloned().collect();
        keys.sort_by(|a, b| b.cmp(a));

        for t in 1..=k {
            let mut mn = inf;
            for &key in &keys {
                let pos = &g[&key];
                for &(i, j) in pos {
                    mn = mn.min(f[t - 1][i][j]);
                }
                for &(i, j) in pos {
                    f[t][i][j] = mn;
                }
            }
            for i in 0..m {
                for j in 0..n {
                    if i > 0 {
                        f[t][i][j] = f[t][i][j].min(f[t][i - 1][j] + grid[i][j]);
                    }
                    if j > 0 {
                        f[t][i][j] = f[t][i][j].min(f[t][i][j - 1] + grid[i][j]);
                    }
                }
            }
        }

        let mut ans = inf;
        for t in 0..=k {
            ans = ans.min(f[t][m - 1][n - 1]);
        }
        ans
    }
}
