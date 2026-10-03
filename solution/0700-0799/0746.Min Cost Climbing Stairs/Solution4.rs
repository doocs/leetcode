impl Solution {
    pub fn min_cost_climbing_stairs(cost: Vec<i32>) -> i32 {
        let n = cost.len();
        let mut f = vec![0; n + 2];
        for i in (0..n).rev() {
            f[i] = cost[i] + f[i + 1].min(f[i + 2]);
        }
        f[0].min(f[1])
    }
}
