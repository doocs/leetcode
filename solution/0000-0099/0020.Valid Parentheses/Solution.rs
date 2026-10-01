use std::collections::HashMap;

impl Solution {
    pub fn is_valid(s: String) -> bool {
        let d: HashMap<char, char> = [('(', ')'), ('[', ']'), ('{', '}')]
            .iter()
            .copied()
            .collect();
        let mut stk = Vec::new();
        for c in s.chars() {
            if let Some(&v) = d.get(&c) {
                stk.push(v);
            } else if stk.pop() != Some(c) {
                return false;
            }
        }
        stk.is_empty()
    }
}
