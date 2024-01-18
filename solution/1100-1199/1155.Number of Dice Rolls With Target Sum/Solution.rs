impl Solution {
    pub fn num_rolls_to_target(n: i32, k: i32, target: i32) -> i32 {
        let _mod = 1_000_000_007;
        let n = n as usize;
        let k = k as usize;
        let target = target as usize;
        let mut f = vec![vec![0; target + 1]; n + 1];
        f[0][0] = 1;

        for i in 1..=n {
            for j in 1..=target.min(i * k) {
                for h in 1..=j.min(k) {
                    f[i][j] = (f[i][j] + f[i - 1][j - h]) % _mod;
                }
            }
        }

        f[n][target]
    }
}
