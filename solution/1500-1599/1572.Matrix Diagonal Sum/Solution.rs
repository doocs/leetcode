impl Solution {
    pub fn diagonal_sum(mat: Vec<Vec<i32>>) -> i32 {
        let n = mat.len();
        let mut ans = 0;

        for i in 0..n {
            ans += mat[i][i];
            let j = n - i - 1;
            if j != i {
                ans += mat[i][j];
            }
        }

        ans
    }
}
