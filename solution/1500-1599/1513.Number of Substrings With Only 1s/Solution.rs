impl Solution {
    pub fn num_sub(s: String) -> i32 {
        const MOD: i32 = 1_000_000_007;
        let mut ans: i32 = 0;
        let mut cur: i32 = 0;
        for c in s.chars() {
            if c == '0' {
                cur = 0;
            } else {
                cur += 1;
                ans = (ans + cur) % MOD;
            }
        }
        ans
    }
}
