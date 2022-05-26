use std::cmp::Ordering;
impl Solution {
    pub fn find_number_in2_d_array(matrix: Vec<Vec<i32>>, target: i32) -> bool {
        if matrix.len() == 0 || matrix[0].len() == 0 {
            return false;
        }
        let (m, n) = (matrix.len(), matrix[0].len());
        let (mut i, mut j) = (0, n);
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
