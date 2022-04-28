impl Solution {
    pub fn find_rotation(mat: Vec<Vec<i32>>, target: Vec<Vec<i32>>) -> bool {
        let n = mat.len();
        let mut is_equal = [true; 4];
        for i in 0..n {
            for j in 0..n {
                if is_equal[0] && mat[i][j] != target[i][j] {
                    is_equal[0] = false;
                }
                if is_equal[1] && mat[i][j] != target[j][n - 1 - i] {
                    is_equal[1] = false;
                }
                if is_equal[2] && mat[i][j] != target[n - 1 - i][n - 1 - j] {
                    is_equal[2] = false;
                }
                if is_equal[3] && mat[i][j] != target[n - 1 - j][i] {
                    is_equal[3] = false;
                }
            }
        }
        is_equal.into_iter().any(|&v| v)
    }
}
