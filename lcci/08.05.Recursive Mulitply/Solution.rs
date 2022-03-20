impl Solution {
    pub fn multiply(a: i32, b: i32) -> i32 {
        if a == 0 || b == 0 {
            return 0;
        }
        a.max(b) + Self::multiply(a.max(b), a.min(b) - 1)
    }
}
