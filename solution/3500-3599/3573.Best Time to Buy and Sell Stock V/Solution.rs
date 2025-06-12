impl Solution {
    pub fn maximum_profit(prices: Vec<i32>, k: i32) -> i64 {
        let n = prices.len();
        let k = k as usize;
        let mut f = vec![vec![vec![0i64; 3]; k + 1]; n];
        for j in 1..=k {
            f[0][j][1] = -(prices[0] as i64);
            f[0][j][2] = prices[0] as i64;
        }
        for i in 1..n {
            for j in 1..=k {
                f[i][j][0] = f[i - 1][j][0]
                    .max(f[i - 1][j][1] + prices[i] as i64)
                    .max(f[i - 1][j][2] - prices[i] as i64);
                f[i][j][1] = f[i - 1][j][1].max(f[i - 1][j - 1][0] - prices[i] as i64);
                f[i][j][2] = f[i - 1][j][2].max(f[i - 1][j - 1][0] + prices[i] as i64);
            }
        }
        f[n - 1][k][0]
    }
}
