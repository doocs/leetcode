impl Solution {
    pub fn is_power_of_four(n: i32) -> bool {
        n > 0 && (n & (n - 1)) == 0 && (n & 0xaaaaaaaa_u32 as i32) == 0
    }
}
