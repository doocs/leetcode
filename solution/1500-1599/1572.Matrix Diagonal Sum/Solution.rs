impl Solution {
    pub fn diagonal_sum(mat: Vec<Vec<i32>>) -> i32 {
        let n = mat.len();
        let mut res = 0;
        for i in 0..n {
            res += mat[i][i] + mat[n - i - 1][i];
        }
        if n & 1 == 1 {
            return res - mat[n / 2][n / 2];
        }
        res
    }
}
