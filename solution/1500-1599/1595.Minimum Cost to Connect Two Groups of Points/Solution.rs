impl Solution {
    pub fn connect_two_groups(cost: Vec<Vec<i32>>) -> i32 {
        let m = cost.len();
        let n = cost[0].len();
        let inf = 1 << 30;

        let mut f = vec![vec![inf; 1 << n]; m + 1];
        f[0][0] = 0;

        for i in 1..=m {
            for j in 0..(1 << n) {
                for k in 0..n {
                    if (j >> k) & 1 == 1 {
                        let c = cost[i - 1][k];
                        f[i][j] = f[i][j].min(f[i][j ^ (1 << k)] + c);
                        f[i][j] = f[i][j].min(f[i - 1][j] + c);
                        f[i][j] = f[i][j].min(f[i - 1][j ^ (1 << k)] + c);
                    }
                }
            }
        }

        f[m][(1 << n) - 1]
    }
}
