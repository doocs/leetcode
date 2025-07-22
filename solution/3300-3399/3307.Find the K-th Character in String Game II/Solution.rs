impl Solution {
    pub fn kth_character(mut k: i64, operations: Vec<i32>) -> char {
        let mut n = 1i64;
        let mut i = 0;
        while n < k {
            n *= 2;
            i += 1;
        }
        let mut d = 0;
        while n > 1 {
            if k > n / 2 {
                k -= n / 2;
                d += operations[i - 1] as i64;
            }
            n /= 2;
            i -= 1;
        }
        ((b'a' + (d % 26) as u8) as char)
    }
}
