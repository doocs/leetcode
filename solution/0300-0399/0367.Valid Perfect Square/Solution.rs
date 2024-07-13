impl Solution {
    pub fn is_perfect_square(num: i32) -> bool {
        let mut l = 1;
        let mut r = num as i64;
        while l < r {
            let mid = (l + r) / 2;
            if mid * mid >= (num as i64) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        l * l == (num as i64)
    }
}
