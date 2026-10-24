impl Solution {
    pub fn count_days_together(
        arrive_alice: String,
        leave_alice: String,
        arrive_bob: String,
        leave_bob: String,
    ) -> i32 {
        const PREFIX: [i32; 12] = [0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334];
        let f =
            |s: &str| PREFIX[s[..2].parse::<usize>().unwrap() - 1] + s[3..].parse::<i32>().unwrap();
        let x = f(&arrive_alice).max(f(&arrive_bob));
        let y = f(&leave_alice).min(f(&leave_bob));
        (y - x + 1).max(0)
    }
}
