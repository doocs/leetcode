impl Solution {
    pub fn minimum_total(triangle: Vec<Vec<i32>>) -> i32 {
        let n = triangle.len();
        let mut f = vec![vec![0; n + 1]; n + 1];
        for i in (0..n).rev() {
            for j in 0..=i {
                f[i][j] = f[i + 1][j].min(f[i + 1][j + 1]) + triangle[i][j];
            }
        }
        f[0][0]
    }
}
