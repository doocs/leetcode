impl Solution {
    pub fn range_add_queries(n: i32, queries: Vec<Vec<i32>>) -> Vec<Vec<i32>> {
        let n = n as usize;
        let mut mat = vec![vec![0; n]; n];

        for q in queries {
            let (x1, y1, x2, y2) = (q[0] as usize, q[1] as usize, q[2] as usize, q[3] as usize);
            mat[x1][y1] += 1;
            if x2 + 1 < n {
                mat[x2 + 1][y1] -= 1;
            }
            if y2 + 1 < n {
                mat[x1][y2 + 1] -= 1;
            }
            if x2 + 1 < n && y2 + 1 < n {
                mat[x2 + 1][y2 + 1] += 1;
            }
        }

        for i in 0..n {
            for j in 0..n {
                if i > 0 {
                    mat[i][j] += mat[i - 1][j];
                }
                if j > 0 {
                    mat[i][j] += mat[i][j - 1];
                }
                if i > 0 && j > 0 {
                    mat[i][j] -= mat[i - 1][j - 1];
                }
            }
        }

        mat
    }
}
