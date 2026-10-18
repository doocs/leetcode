impl Solution {
    pub fn max_depth(s: String) -> i32 {
        let mut ans = 0;
        let mut d = 0;
        for c in s.chars() {
            if c == '(' {
                d += 1;
                ans = ans.max(d);
            } else if c == ')' {
                d -= 1;
            }
        }
        ans
    }
}
