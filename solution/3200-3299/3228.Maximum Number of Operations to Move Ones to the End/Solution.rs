impl Solution {
    pub fn max_operations(s: String) -> i32 {
        let mut ans = 0;
        let mut cnt = 0;
        let n = s.len();
        let bytes = s.as_bytes();
        for i in 0..n {
            if bytes[i] == b'1' {
                cnt += 1;
            } else if i > 0 && bytes[i - 1] == b'1' {
                ans += cnt;
            }
        }
        ans
    }
}
