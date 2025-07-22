impl Solution {
    pub fn max_free_time(event_time: i32, k: i32, start_time: Vec<i32>, end_time: Vec<i32>) -> i32 {
        let n = end_time.len();
        let f = |i: usize| -> i32 {
            if i == 0 {
                start_time[0]
            } else if i == n {
                event_time - end_time[n - 1]
            } else {
                start_time[i] - end_time[i - 1]
            }
        };
        let mut ans = 0;
        let mut s = 0;
        for i in 0..=n {
            s += f(i);
            if i >= k as usize {
                ans = ans.max(s);
                s -= f(i - k as usize);
            }
        }
        ans
    }
}
