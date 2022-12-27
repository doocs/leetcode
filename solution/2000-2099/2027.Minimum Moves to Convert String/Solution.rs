impl Solution {
    pub fn minimum_moves(s: String) -> i32 {
        let s = s.as_bytes();
        let n = s.len();
        let mut ans = 0;
        let mut i = 0;
        while i < n {
            if s[i] == b'X' {
                ans += 1;
                i += 3;
            } else {
                i += 1;
            }
        }
        ans
    }
}
