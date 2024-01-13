use std::cmp::Ordering;
impl Solution {
    pub fn search_matrix(matrix: Vec<Vec<i32>>, target: i32) -> bool {
        let m = matrix.len();
        let n = matrix[0].len();
        let mut left = 0;
        let mut right = m * n;
        while left < right {
            let mid = left + (right - left) / 2;
            let i = mid / n;
            let j = mid % n;
            match matrix[i][j].cmp(&target) {
                Ordering::Equal => {
                    return true;
                }
                Ordering::Less => {
                    left = mid + 1;
                }
                Ordering::Greater => {
                    right = mid;
                }
            }
        }
        false
    }
}
