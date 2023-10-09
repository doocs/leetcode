impl Solution {
    pub fn number_of_employees_who_met_target(hours: Vec<i32>, target: i32) -> i32 {
        let mut ans = 0;
        for &v in hours.iter() {
            if v >= target {
                ans += 1;
            }
        }
        ans
    }
}
