impl Solution {
    pub fn distinct_subseq_ii(s: String) -> i32 {
        const MOD: i32 = 1_000_000_007;
        let mut f = [0; 26];
        for u in s.bytes() {
            let mut x = 1;
            for &v in &f {
                x = (x + v) % MOD;
            }
            f[(u - b'a') as usize] = x;
        }
        f.iter().fold(0, |acc, &v| (acc + v) % MOD)
    }
}
