impl Solution {
    pub fn are_similar(mat: Vec<Vec<i32>>, k: i32) -> bool {
        let m = mat.len();
        let n = mat[0].len();
        let k = (k as usize) % n;

        for i in 0..m {
            for j in 0..n {
                if i % 2 == 1 && mat[i][j] != mat[i][(j + k) % n] {
                    return false;
                }
                if i % 2 == 0 && mat[i][j] != mat[i][(j + n - k) % n] {
                    return false;
                }
            }
        }
        true
    }
}
