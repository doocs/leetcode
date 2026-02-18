impl Solution {
    pub fn has_alternating_bits(n: i32) -> bool {
        let mut x = n ^ (n >> 1);
        (x & (x + 1)) == 0
    }
}
