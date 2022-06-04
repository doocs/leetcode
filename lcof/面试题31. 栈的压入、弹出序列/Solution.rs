impl Solution {
    pub fn validate_stack_sequences(pushed: Vec<i32>, popped: Vec<i32>) -> bool {
        let mut stack = Vec::new();
        let mut i = 0;
        for &num in pushed.iter() {
            stack.push(num);
            while !stack.is_empty() && *stack.last().unwrap() == popped[i] {
                stack.pop();
                i += 1;
            }
        }
        stack.len() == 0
    }
}
