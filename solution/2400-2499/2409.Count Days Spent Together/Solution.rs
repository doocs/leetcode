impl Solution {
    pub fn count_days_together(
        arrive_alice: String,
        leave_alice: String,
        arrive_bob: String,
        leave_bob: String,
    ) -> i32 {
        let f = [0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334];
        let start = arrive_alice.max(arrive_bob);
        let end = leave_alice.min(leave_bob);
        let d1 = f[start[..2].parse::<usize>().unwrap() - 1] + start[3..].parse::<i32>().unwrap();
        let d2 = f[end[..2].parse::<usize>().unwrap() - 1] + end[3..].parse::<i32>().unwrap();
        (d2 - d1 + 1).max(0)
    }
}
