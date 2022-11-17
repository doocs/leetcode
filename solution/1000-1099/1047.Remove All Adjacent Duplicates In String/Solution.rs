impl Solution {
    pub fn remove_duplicates(s: String) -> String {
        let mut stack = Vec::new();
        for c in s.chars() {
            if !stack.is_empty() && *stack.last().unwrap() == c {
                stack.pop();
            } else {
                stack.push(c);
            }
        }
        stack.into_iter().collect()
    }
}
