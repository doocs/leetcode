use std::cmp::Ordering;
impl Solution {
    pub fn is_perfect_square(num: i32) -> bool {
        let num: i64 = num as i64;
        let mut left = 1;
        let mut right = num >> 1;
        while left < right {
            let mid = left + (right - left) / 2;
            match (mid * mid).cmp(&num) {
                Ordering::Less => left = mid + 1,
                Ordering::Greater => right = mid - 1,
                Ordering::Equal => return true,
            }
        }
        left * left == num
    }
}
