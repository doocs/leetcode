impl Solution {
    pub fn daily_temperatures(temperatures: Vec<i32>) -> Vec<i32> {
        let n = temperatures.len();
        let mut res = vec![0; n];
        let mut stack = Vec::new();
        for i in (0..n).rev() {
            while !stack.is_empty() && temperatures[*stack.last().unwrap()] <= temperatures[i] {
                stack.pop();
            }
            res[i] = if stack.is_empty() { 0 } else { (stack.last().unwrap() - i) as i32 };
            stack.push(i);
        }
        res
    }
}
