impl Solution {
    pub fn coin_change(coins: Vec<i32>, amount: i32) -> i32 {
        let n = amount as usize;
        let mut f = vec![n + 1; n + 1];
        f[0] = 0;
        for &x in &coins {
            for j in x as usize..=n {
                f[j] = f[j].min(f[j - (x as usize)] + 1);
            }
        }
        if f[n] > n {
            -1
        } else {
            f[n] as i32
        }
    }
}
