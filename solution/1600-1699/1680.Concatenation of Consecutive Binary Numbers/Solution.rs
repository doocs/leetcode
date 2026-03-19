impl Solution {
    pub fn concatenated_binary(n: i32) -> i32 {
        let mod_: i64 = 1_000_000_007;
        let mut ans: i64 = 0;
        for i in 1..=n as i64 {
            let bit_length: u32 = 64 - i.leading_zeros() as u32;
            ans = ((ans << bit_length) | i) % mod_;
        }
        ans as i32
    }
}
