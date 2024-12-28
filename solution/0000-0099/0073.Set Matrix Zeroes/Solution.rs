impl Solution {
    pub fn set_zeroes(matrix: &mut Vec<Vec<i32>>) {
        let m = matrix.len();
        let n = matrix[0].len();
        let mut row = vec![false; m];
        let mut col = vec![false; n];
        for i in 0..m {
            for j in 0..n {
                if matrix[i][j] == 0 {
                    row[i] = true;
                    col[j] = true;
                }
            }
        }
        for i in 0..m {
            for j in 0..n {
                if row[i] || col[j] {
                    matrix[i][j] = 0;
                }
            }
        }
    }
}
