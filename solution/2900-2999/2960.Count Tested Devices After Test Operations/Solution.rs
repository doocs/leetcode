impl Solution {
    pub fn count_tested_devices(battery_percentages: Vec<i32>) -> i32 {
        let mut ans = 0;
        for x in battery_percentages {
            ans += if x > ans { 1 } else { 0 };
        }
        ans
    }
}
