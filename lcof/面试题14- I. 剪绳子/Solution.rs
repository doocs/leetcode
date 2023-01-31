impl Solution {
    pub fn cutting_rope(n: i32) -> i32 {
        if n < 4 {
            return n - 1;
        }
        let count = (n - 2) / 3;
        3i32.pow(count as u32) * (n - count * 3)
    }
}
