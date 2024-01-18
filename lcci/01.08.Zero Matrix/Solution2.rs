impl Solution {
    pub fn set_zeroes(matrix: &mut Vec<Vec<i32>>) {
        let m = matrix.len();
        let n = matrix[0].len();
        let l0 = {
            let mut res = false;
            for j in 0..n {
                if matrix[0][j] == 0 {
                    res = true;
                    break;
                }
            }
            res
        };
        let r0 = {
            let mut res = false;
            for i in 0..m {
                if matrix[i][0] == 0 {
                    res = true;
                    break;
                }
            }
            res
        };
        for i in 0..m {
            for j in 0..n {
                if matrix[i][j] == 0 {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        for i in 1..m {
            for j in 1..n {
                if matrix[i][0] == 0 || matrix[0][j] == 0 {
                    matrix[i][j] = 0;
                }
            }
        }
        if l0 {
            for j in 0..n {
                matrix[0][j] = 0;
            }
        }
        if r0 {
            for i in 0..m {
                matrix[i][0] = 0;
            }
        }
    }
}
