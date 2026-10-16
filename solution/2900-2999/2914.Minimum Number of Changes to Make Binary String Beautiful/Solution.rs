impl Solution {
    pub fn min_changes(s: String) -> i32 {
        let s = s.as_bytes();
        let mut ans = 0;
        let mut i = 1;
        while i < s.len() {
            if s[i] != s[i - 1] {
                ans += 1;
            }
            i += 2;
        }
        ans
    }
}
