impl Solution {
    pub fn reverse_parentheses(s: String) -> String {
        let mut stk = Vec::new();
        for c in s.chars() {
            if c == ')' {
                let mut t = Vec::new();
                while *stk.last().unwrap() != '(' {
                    t.push(stk.pop().unwrap());
                }
                stk.pop();
                stk.extend(t);
            } else {
                stk.push(c);
            }
        }
        stk.into_iter().collect()
    }
}
