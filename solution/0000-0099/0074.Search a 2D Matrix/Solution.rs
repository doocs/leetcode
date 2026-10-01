impl Solution {
    pub fn search_matrix(matrix: Vec<Vec<i32>>, target: i32) -> bool {
        let m = matrix.len();
        let n = matrix[0].len();
        let mut left = 0;
        let mut right = m * n - 1;
        while left < right {
            let mid = (left + right) >> 1;
            let x = mid / n;
            let y = mid % n;
            if matrix[x][y] >= target {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        matrix[left / n][left % n] == target
    }
}
