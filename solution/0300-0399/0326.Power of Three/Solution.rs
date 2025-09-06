impl Solution {
    pub fn is_power_of_three(mut n: i32) -> bool {
        while n > 2 {
            if n % 3 != 0 {
                return false;
            }
            n /= 3;
        }
        n == 1
    }
}
