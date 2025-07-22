impl Solution {
    pub fn daily_temperatures(temperatures: Vec<i32>) -> Vec<i32> {
        let n = temperatures.len();
        let mut stk: Vec<usize> = Vec::new();
        let mut ans = vec![0; n];

        for i in (0..n).rev() {
            while let Some(&top) = stk.last() {
                if temperatures[top] <= temperatures[i] {
                    stk.pop();
                } else {
                    break;
                }
            }
            if let Some(&top) = stk.last() {
                ans[i] = (top - i) as i32;
            }
            stk.push(i);
        }

        ans
    }
}
