impl Solution {
    pub fn daily_temperatures(temperatures: Vec<i32>) -> Vec<i32> {
        let n = temperatures.len();
        let mut res = vec![0; n];
        let mut stack = Vec::new();
        for i in 0..n {
            while !stack.is_empty() && temperatures[*stack.last().unwrap()] < temperatures[i] {
                let j = stack.pop().unwrap();
                res[j] = (i - j) as i32;
            }
            stack.push(i);
        }
        res
    }
}
