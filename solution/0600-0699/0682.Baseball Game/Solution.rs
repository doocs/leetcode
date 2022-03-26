impl Solution {
    pub fn cal_points(ops: Vec<String>) -> i32 {
        let mut stack = vec![];
        for op in ops {
            match op.as_str() {
                "+" => {
                    let n = stack.len();
                    stack.push(stack[n - 1] + stack[n - 2]);
                }
                "D" => {
                    stack.push(stack.last().unwrap() * 2);
                }
                "C" => {
                    stack.pop();
                }
                n => {
                    stack.push(n.parse::<i32>().unwrap());
                }
            }
        }
        stack.into_iter().sum()
    }
}
