impl Solution {
    pub fn check_powers_of_three(n: i32) -> bool {
        let mut n = n;
        while n > 0 {
            if n % 3 > 1 {
                return false;
            }
            n /= 3;
        }
        true
    }
}
