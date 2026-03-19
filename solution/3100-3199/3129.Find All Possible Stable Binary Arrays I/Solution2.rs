impl Solution {
    pub fn number_of_stable_arrays(zero: i32, one: i32, limit: i32) -> i32 {
        let mod_: i64 = 1_000_000_007;

        let zero = zero as usize;
        let one = one as usize;
        let limit = limit as usize;

        let mut f = vec![vec![[0_i64; 2]; one + 1]; zero + 1];

        for i in 1..=zero.min(limit) {
            f[i][0][0] = 1;
        }

        for j in 1..=one.min(limit) {
            f[0][j][1] = 1;
        }

        for i in 1..=zero {
            for j in 1..=one {
                let x = if i > limit { f[i - limit - 1][j][1] } else { 0 };
                let y = if j > limit { f[i][j - limit - 1][0] } else { 0 };

                f[i][j][0] = (f[i - 1][j][0] + f[i - 1][j][1] - x + mod_) % mod_;
                f[i][j][1] = (f[i][j - 1][0] + f[i][j - 1][1] - y + mod_) % mod_;
            }
        }

        ((f[zero][one][0] + f[zero][one][1]) % mod_) as i32
    }
}
