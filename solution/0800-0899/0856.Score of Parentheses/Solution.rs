impl Solution {
    pub fn score_of_parentheses(s: String) -> i32 {
        let s = s.as_bytes();
        let mut ans = 0;
        let mut d = 0;
        for i in 0..s.len() {
            if s[i] == b'(' {
                d += 1;
            } else {
                d -= 1;
                if s[i - 1] == b'(' {
                    ans += 1 << d;
                }
            }
        }
        ans
    }
}
