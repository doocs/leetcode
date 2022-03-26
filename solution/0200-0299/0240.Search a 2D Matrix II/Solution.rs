use std::cmp::Ordering;

impl Solution {
    pub fn search_matrix(matrix: Vec<Vec<i32>>, target: i32) -> bool {
        let m = matrix.len();
        let n = matrix[0].len();
        let mut i = 0;
        let mut j = n;
        while i < m && j > 0 {
            match target.cmp(&matrix[i][j - 1]) {
                Ordering::Less => j -= 1,
                Ordering::Greater => i += 1,
                Ordering::Equal => return true,
            }
        }
        false
    }
}
