impl Solution {
    pub fn find_rotation(mat: Vec<Vec<i32>>, target: Vec<Vec<i32>>) -> bool {
        let n = mat.len();
        let mut ok: i32 = 0b1111;

        for i in 0..n {
            for j in 0..n {
                if mat[i][j] != target[i][j] {
                    ok &= !0b0001;
                }
                if mat[j][n - 1 - i] != target[i][j] {
                    ok &= !0b0010;
                }
                if mat[n - 1 - i][n - 1 - j] != target[i][j] {
                    ok &= !0b0100;
                }
                if mat[n - 1 - j][i] != target[i][j] {
                    ok &= !0b1000;
                }
                if ok == 0 {
                    return false;
                }
            }
        }

        ok != 0
    }
}
