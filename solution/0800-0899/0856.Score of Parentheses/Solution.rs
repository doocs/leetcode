impl Solution {
    pub fn score_of_parentheses(s: String) -> i32 {
        let (mut ans, mut d, mut prev) = (0, 0, b')');
        for c in s.bytes() {
            if c == b'(' {
                d += 1;
            } else {
                d -= 1;
                if prev == b'(' {
                    ans += 1 << d;
                }
            }
            prev = c;
        }
        ans
    }
}
