impl Solution {
    pub fn multiply(a: i32, b: i32) -> i32 {
        if b == 1 {
            return a;
        }
        if (b & 1) == 1 {
            return (Self::multiply(a, b >> 1) << 1) + a;
        }
        Self::multiply(a, b >> 1) << 1
    }
}
