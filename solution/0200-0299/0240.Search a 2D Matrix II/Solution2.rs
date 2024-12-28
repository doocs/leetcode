impl Solution {
    pub fn search_matrix(matrix: Vec<Vec<i32>>, target: i32) -> bool {
        let m = matrix.len();
        let n = matrix[0].len();
        let mut i = m - 1;
        let mut j = 0;
        while i >= 0 && j < n {
            if matrix[i][j] == target {
                return true;
            }
            if matrix[i][j] > target {
                if i == 0 {
                    break;
                }
                i -= 1;
            } else {
                j += 1;
            }
        }
        false
    }
}
