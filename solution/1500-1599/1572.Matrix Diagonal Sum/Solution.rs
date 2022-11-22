impl Solution {
    pub fn diagonal_sum(mat: Vec<Vec<i32>>) -> i32 {
        let n = mat.len();
        let mut ans = 0;
        for i in 0..n {
            ans += mat[i][i] + mat[n - 1 - i][i];
        }
        if n & 1 == 1 {
            ans -= mat[n >> 1][n >> 1];
        }
        ans
    }
}
