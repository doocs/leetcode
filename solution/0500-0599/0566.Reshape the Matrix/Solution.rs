impl Solution {
    pub fn matrix_reshape(mat: Vec<Vec<i32>>, r: i32, c: i32) -> Vec<Vec<i32>> {
        let r = r as usize;
        let c = c as usize;
        let m = mat.len();
        let n = mat[0].len();
        if m * n != r * c {
            return mat;
        }
        let mut i = 0;
        let mut j = 0;
        (0..r)
            .into_iter()
            .map(|_| {
                (0..c)
                    .into_iter()
                    .map(|_| {
                        let res = mat[i][j];
                        j += 1;
                        if j == n {
                            j = 0;
                            i += 1;
                        }
                        res
                    })
                    .collect()
            })
            .collect()
    }
}
