impl Solution {
    pub fn distinct_subseq_ii(s: String) -> i32 {
        const MOD: i32 = 1_000_000_007;
        let mut f = [0; 26];
        let mut ans = 0;
        for u in s.bytes() {
            let i = (u - b'a') as usize;
            let add = (ans + 1 + MOD - f[i]) % MOD;
            ans = (ans + add) % MOD;
            f[i] = (f[i] + add) % MOD;
        }
        ans
    }
}
