impl Solution {
    pub fn num_special(mat: Vec<Vec<i32>>) -> i32 {
        let m = mat.len();
        let n = mat[0].len();
        let mut rows = vec![0; m];
        let mut cols = vec![0; n];
        for i in 0..m {
            for j in 0..n {
                rows[i] += mat[i][j];
                cols[j] += mat[i][j];
            }
        }

        let mut res = 0;
        for i in 0..m {
            for j in 0..n {
                if mat[i][j] == 1 && rows[i] == 1 && cols[j] == 1 {
                    res += 1;
                }
            }
        }
        res
    }
}
