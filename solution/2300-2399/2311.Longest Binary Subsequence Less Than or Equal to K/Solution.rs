impl Solution {
    pub fn longest_subsequence(s: String, k: i32) -> i32 {
        let mut ans = 0;
        let mut v = 0;
        let s = s.as_bytes();
        for i in (0..s.len()).rev() {
            if s[i] == b'0' {
                ans += 1;
            } else if ans < 30 && (v | (1 << ans)) <= k {
                v |= 1 << ans;
                ans += 1;
            }
        }
        ans
    }
}
