impl Solution {
    pub fn is_one_bit_character(bits: Vec<i32>) -> bool {
        let mut i = 0usize;
        let n = bits.len();
        while i < n - 1 {
            i += (bits[i] + 1) as usize;
        }
        i == n - 1
    }
}
