impl Solution {
    pub fn num_of_ways(n: i32) -> i32 {
        const MOD: i64 = 1_000_000_007;
        let mut f0: i64 = 6;
        let mut f1: i64 = 6;

        for _ in 0..n - 1 {
            let g0 = (3 * f0 + 2 * f1) % MOD;
            let g1 = (2 * f0 + 2 * f1) % MOD;
            f0 = g0;
            f1 = g1;
        }

        ((f0 + f1) % MOD) as i32
    }
}
