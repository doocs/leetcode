impl Solution {
    pub fn can_see_persons_count(heights: Vec<i32>) -> Vec<i32> {
        let n = heights.len();
        let mut ans = vec![0; n];
        let mut stack = Vec::new();
        for i in (0..n).rev() {
            while !stack.is_empty() {
                ans[i] += 1;
                if heights[i] <= heights[*stack.last().unwrap()] {
                    break;
                }
                stack.pop();
            }
            stack.push(i);
        }
        ans
    }
}
