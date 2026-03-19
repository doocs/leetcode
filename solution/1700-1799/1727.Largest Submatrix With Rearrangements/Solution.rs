impl Solution {
    pub fn largest_submatrix(mut matrix: Vec<Vec<i32>>) -> i32 {
        let m: usize = matrix.len();
        let n: usize = matrix[0].len();

        for i in 1..m {
            for j in 0..n {
                if matrix[i][j] != 0 {
                    matrix[i][j] = matrix[i - 1][j] + 1;
                }
            }
        }

        let mut ans: i32 = 0;

        for row in matrix.iter_mut() {
            row.sort_unstable_by(|a, b| b.cmp(a));
            for j in 0..n {
                ans = ans.max((j as i32 + 1) * row[j]);
            }
        }

        ans
    }
}
