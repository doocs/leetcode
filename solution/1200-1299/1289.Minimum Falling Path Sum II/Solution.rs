impl Solution {
    pub fn min_falling_path_sum(grid: Vec<Vec<i32>>) -> i32 {
        let n = grid.len();
        let mut f = vec![0; n];
        let inf = i32::MAX;

        for row in grid {
            let mut g = row.clone();
            for i in 0..n {
                let mut t = inf;
                for j in 0..n {
                    if j != i {
                        t = t.min(f[j]);
                    }
                }
                g[i] += if t == inf { 0 } else { t };
            }
            f = g;
        }

        *f.iter().min().unwrap()
    }
}
