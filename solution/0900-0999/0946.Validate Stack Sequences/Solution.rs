impl Solution {
    pub fn validate_stack_sequences(pushed: Vec<i32>, popped: Vec<i32>) -> bool {
        let mut stk: Vec<i32> = Vec::new();
        let mut i = 0;
        for &x in &pushed {
            stk.push(x);
            while !stk.is_empty() && *stk.last().unwrap() == popped[i] {
                stk.pop();
                i += 1;
            }
        }
        i == popped.len()
    }
}
