impl Solution {
    pub fn search_matrix(matrix: Vec<Vec<i32>>, target: i32) -> bool {
        let m = matrix.len();
        let n = matrix[0].len();
        let mut i = m as i32 - 1;
        let mut j: i32 = 0;
        while i >= 0 && j < n as i32 {
            let cur = matrix[i as usize][j as usize];
            if cur == target {
                return true;
            }
            if cur > target {
                i -= 1;
            } else {
                j += 1;
            }
        }
        false
    }
}
