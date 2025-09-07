impl Solution {
    pub fn minimum_sum(grid: Vec<Vec<i32>>) -> i32 {
        let m = grid.len();
        let n = grid[0].len();
        let mut ans = (m * n) as i32;
        let inf = i32::MAX / 4;

        let f = |i1: usize, j1: usize, i2: usize, j2: usize| -> i32 {
            let mut x1 = inf;
            let mut y1 = inf;
            let mut x2 = -inf;
            let mut y2 = -inf;

            for i in i1..=i2 {
                for j in j1..=j2 {
                    if grid[i][j] == 1 {
                        x1 = x1.min(i as i32);
                        y1 = y1.min(j as i32);
                        x2 = x2.max(i as i32);
                        y2 = y2.max(j as i32);
                    }
                }
            }
            if x1 > x2 || y1 > y2 {
                inf
            } else {
                (x2 - x1 + 1) * (y2 - y1 + 1)
            }
        };

        for i1 in 0..m - 1 {
            for i2 in i1 + 1..m - 1 {
                ans = ans
                    .min(f(0, 0, i1, n - 1) + f(i1 + 1, 0, i2, n - 1) + f(i2 + 1, 0, m - 1, n - 1));
            }
        }

        for j1 in 0..n - 1 {
            for j2 in j1 + 1..n - 1 {
                ans = ans
                    .min(f(0, 0, m - 1, j1) + f(0, j1 + 1, m - 1, j2) + f(0, j2 + 1, m - 1, n - 1));
            }
        }

        for i in 0..m - 1 {
            for j in 0..n - 1 {
                ans = ans.min(f(0, 0, i, j) + f(0, j + 1, i, n - 1) + f(i + 1, 0, m - 1, n - 1));
                ans = ans
                    .min(f(0, 0, i, n - 1) + f(i + 1, 0, m - 1, j) + f(i + 1, j + 1, m - 1, n - 1));
                ans = ans.min(f(0, 0, i, j) + f(i + 1, 0, m - 1, j) + f(0, j + 1, m - 1, n - 1));
                ans = ans
                    .min(f(0, 0, m - 1, j) + f(0, j + 1, i, n - 1) + f(i + 1, j + 1, m - 1, n - 1));
            }
        }

        ans
    }
}
