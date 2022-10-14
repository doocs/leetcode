impl Solution {
    pub fn distinct_subseq_ii(s: String) -> i32 {
        const MOD: i32 = 1e9 as i32 + 7;
        let mut dp = [0; 26];
        for u in s.as_bytes() {
            let i = (u - &b'a') as usize;
            dp[i] = {
                let mut sum = 0;
                dp.iter().for_each(|&v| sum = (sum + v) % MOD);
                sum
            } + 1;
        }
        let mut res = 0;
        dp.iter().for_each(|&v| res = (res + v) % MOD);
        res
    }
}
