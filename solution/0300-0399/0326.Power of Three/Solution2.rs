impl Solution {
    pub fn is_power_of_three(mut n: i32) -> bool {
        n > 0 && 1162261467 % n == 0
    }
}
