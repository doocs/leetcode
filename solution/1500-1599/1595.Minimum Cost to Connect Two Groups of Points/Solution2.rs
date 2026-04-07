impl Solution {
    pub fn connect_two_groups(cost: Vec<Vec<i32>>) -> i32 {
        let m = cost.len();
        let n = cost[0].len();
        let inf = 1 << 30;

        let mut f = vec![inf; 1 << n];
        f[0] = 0;

        let mut g = f.clone();

        for i in 1..=m {
            for j in 0..(1 << n) {
                g[j] = inf;
                for k in 0..n {
                    if (j >> k) & 1 == 1 {
                        let c = cost[i - 1][k];
                        g[j] = g[j].min(g[j ^ (1 << k)] + c);
                        g[j] = g[j].min(f[j] + c);
                        g[j] = g[j].min(f[j ^ (1 << k)] + c);
                    }
                }
            }
            f.clone_from_slice(&g);
        }

        f[(1 << n) - 1]
    }
}
