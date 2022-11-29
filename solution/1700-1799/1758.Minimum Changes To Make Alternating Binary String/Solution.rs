impl Solution {
    pub fn min_operations(s: String) -> i32 {
        let n = s.len();
        let s = s.as_bytes();
        let cs = [b'0', b'1'];
        let mut count = 0;
        for i in 0..n {
            count += if s[i] != cs[i & 1] { 1 } else { 0 };
        }
        count.min(n - count) as i32
    }
}
