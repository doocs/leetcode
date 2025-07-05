impl Solution {
    pub fn max_free_time(event_time: i32, k: i32, start_time: Vec<i32>, end_time: Vec<i32>) -> i32 {
        let n = end_time.len();
        let mut nums = vec![0; n + 1];
        nums[0] = start_time[0];
        for i in 1..n {
            nums[i] = start_time[i] - end_time[i - 1];
        }
        nums[n] = event_time - end_time[n - 1];

        let mut ans = 0;
        let mut s = 0;
        for i in 0..=n {
            s += nums[i];
            if i as i32 >= k {
                ans = ans.max(s);
                s -= nums[i - k as usize];
            }
        }
        ans
    }
}
