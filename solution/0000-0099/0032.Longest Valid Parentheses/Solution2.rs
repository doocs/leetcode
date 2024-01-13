impl Solution {
    pub fn longest_valid_parentheses(s: String) -> i32 {
        let mut stack = vec![-1];
        let mut res = 0;
        for i in 0..s.len() {
            if let Some('(') = s.chars().nth(i) {
                stack.push(i as i32);
            } else {
                stack.pop().unwrap();
                if stack.is_empty() {
                    stack.push(i as i32);
                } else {
                    res = std::cmp::max(res, (i as i32) - stack.last().unwrap());
                }
            }
        }
        res
    }
}
