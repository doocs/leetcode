impl Solution {
    pub fn max_chunks_to_sorted(arr: Vec<i32>) -> i32 {
        let mut stack = vec![];
        for num in arr.iter() {
            if !stack.is_empty() && num < stack.last().unwrap() {
                let max = stack.pop().unwrap();
                while !stack.is_empty() && num < stack.last().unwrap() {
                    stack.pop();
                }
                stack.push(max)
            } else {
                stack.push(*num);
            }
        }
        stack.len() as i32
    }
}
