impl Solution {
    pub fn maximum_groups(grades: Vec<i32>) -> i32 {
        let n = grades.len() as i64;
        let (mut l, mut r) = (0i64, n);
        while l < r {
            let mid = (l + r + 1) / 2;
            if mid * mid + mid > 2 * n {
                r = mid - 1;
            } else {
                l = mid;
            }
        }
        l as i32
    }
}
