impl Solution {
    pub fn knight_probability(n: i32, k: i32, row: i32, column: i32) -> f64 {
        let n = n as usize;
        let k = k as usize;

        let mut f = vec![vec![vec![0.0; n]; n]; k + 1];

        for i in 0..n {
            for j in 0..n {
                f[0][i][j] = 1.0;
            }
        }

        let dirs = [-2, -1, 2, 1, -2, 1, 2, -1, -2];

        for h in 1..=k {
            for i in 0..n {
                for j in 0..n {
                    for p in 0..8 {
                        let x = i as isize + dirs[p];
                        let y = j as isize + dirs[p + 1];

                        if x >= 0 && x < n as isize && y >= 0 && y < n as isize {
                            let x = x as usize;
                            let y = y as usize;
                            f[h][i][j] += f[h - 1][x][y] / 8.0;
                        }
                    }
                }
            }
        }

        f[k][row as usize][column as usize]
    }
}
