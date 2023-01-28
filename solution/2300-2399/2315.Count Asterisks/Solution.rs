impl Solution {
    pub fn count_asterisks(s: String) -> i32 {
        let mut ans = 0;
        let mut ok = 1;
        for &c in s.as_bytes() {
            if c == b'*' {
                ans += ok
            } else if c == b'|' {
                ok ^= 1
            }
        }
        ans
    }
}