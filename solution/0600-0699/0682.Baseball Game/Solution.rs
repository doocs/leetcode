impl Solution {
    pub fn cal_points(operations: Vec<String>) -> i32 {
        let mut stk = vec![];
        for op in operations {
            match op.as_str() {
                "+" => {
                    let n = stk.len();
                    stk.push(stk[n - 1] + stk[n - 2]);
                }
                "D" => {
                    stk.push(stk.last().unwrap() * 2);
                }
                "C" => {
                    stk.pop();
                }
                n => {
                    stk.push(n.parse::<i32>().unwrap());
                }
            }
        }
        stk.into_iter().sum()
    }
}
