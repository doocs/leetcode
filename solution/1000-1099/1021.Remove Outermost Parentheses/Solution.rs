impl Solution {
    pub fn remove_outer_parentheses(s: String) -> String {
        let mut res = String::new();
        let mut depth = 0;
        for c in s.chars() {
            if c == '(' {
                depth += 1;
            }
            if depth != 1 {
                res.push(c);
            }
            if c == ')' {
                depth -= 1;
            }
        }
        res
    }
}
