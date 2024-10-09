impl Solution {
    pub fn self_dividing_numbers(left: i32, right: i32) -> Vec<i32> {
        fn check(x: i32) -> bool {
            let mut y = x;
            while y > 0 {
                if y % 10 == 0 || x % (y % 10) != 0 {
                    return false;
                }
                y /= 10;
            }
            true
        }

        (left..=right).filter(|&x| check(x)).collect()
    }
}
