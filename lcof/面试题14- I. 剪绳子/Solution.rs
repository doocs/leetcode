impl Solution {
    pub fn cutting_rope(mut n: i32) -> i32 {
        if n < 4 {
            return n - 1;
        }
        let mut res = 1;
        while n > 4 {
            res *= 3;
            n -= 3;
        }
        res * n
    }
}
