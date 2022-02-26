use std::cmp::Ordering;

impl Solution {
    pub fn is_perfect_square(mut num: i32) -> bool {
        let num: i64 = num as i64;
        let mut l = 0;
        let mut r = num;
        while l < r {
            let mid = l + (r - l) / 2;
            match (mid * mid).cmp(&num) {
                Ordering::Less => l = mid + 1,
                Ordering::Greater => r = mid - 1,
                Ordering::Equal => return true,
            }
        }
        r * r == num
    }
}
