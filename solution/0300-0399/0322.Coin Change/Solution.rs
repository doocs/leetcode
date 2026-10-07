impl Solution {
    pub fn coin_change(coins: Vec<i32>, amount: i32) -> i32 {
        let m = coins.len();
        let n = amount as usize;
        let inf = 1 << 30;
        let mut f = vec![vec![inf; n + 1]; m + 1];
        f[0][0] = 0;
        for i in 1..=m {
            let x = coins[i - 1] as usize;
            for j in 0..=n {
                f[i][j] = f[i - 1][j];
                if j >= x {
                    f[i][j] = f[i][j].min(f[i][j - x] + 1);
                }
            }
        }
        if f[m][n] > amount {
            -1
        } else {
            f[m][n]
        }
    }
}
