use std::collections::VecDeque;

impl Solution {
    pub fn time_taken(arrival: Vec<i32>, state: Vec<i32>) -> Vec<i32> {
        let n = arrival.len();
        let mut q = vec![VecDeque::new(), VecDeque::new()];
        let mut t = 0;
        let mut i = 0;
        let mut st = 1;
        let mut ans = vec![-1; n];

        while i < n || !q[0].is_empty() || !q[1].is_empty() {
            while i < n && arrival[i] <= t {
                q[state[i] as usize].push_back(i);
                i += 1;
            }

            if !q[0].is_empty() && !q[1].is_empty() {
                ans[*q[st].front().unwrap()] = t;
                q[st].pop_front();
            } else if !q[0].is_empty() || !q[1].is_empty() {
                st = if q[0].is_empty() { 1 } else { 0 };
                ans[*q[st].front().unwrap()] = t;
                q[st].pop_front();
            } else {
                st = 1;
            }

            t += 1;
        }

        ans
    }
}
