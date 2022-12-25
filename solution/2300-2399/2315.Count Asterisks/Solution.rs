impl Solution {
    pub fn count_asterisks(s: String) -> i32 {
        let mut ans = 0;
        let mut flag = true;
        for &c in s.as_bytes() {
            if c == b'|' {
                flag = !flag;
            } else if c == b'*' && flag {
                ans += 1;
            }
        }
        ans
    }
}
