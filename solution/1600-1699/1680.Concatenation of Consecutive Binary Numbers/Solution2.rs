impl Solution {
    pub fn concatenated_binary(n: i32) -> i32 {
        let mod_: i64 = 1_000_000_007;
        let mut ans: i64 = 0;
        let mut shift: u32 = 0;
        for i in 1..=n as i64 {
            if (i & (i - 1)) == 0 {
                shift += 1;
            }
            ans = ((ans << shift) | i) % mod_;
        }
        ans as i32
    }
}
