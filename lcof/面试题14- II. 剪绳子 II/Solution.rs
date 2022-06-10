impl Solution {
    pub fn cutting_rope(mut n: i32) -> i32 {
        if n < 4 {
            return n - 1;
        }
        let mut res = 1i64;
        while n > 4 {
            res = (res * 3) % 1000000007;
            n -= 3;
        }
        ((res * n as i64) % 1000000007) as i32
    }
}
