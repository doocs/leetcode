impl Solution {
    pub fn find_diagonal_order(mat: Vec<Vec<i32>>) -> Vec<i32> {
        let (m, n) = (mat.len(), mat[0].len());
        let (mut i, mut j) = (0, 0);
        (0..m * n)
            .map(|_| {
                let res = mat[i][j];
                if (i + j) % 2 == 0 {
                    if j == n - 1 {
                        i += 1;
                    } else if i == 0 {
                        j += 1;
                    } else {
                        i -= 1;
                        j += 1;
                    }
                } else {
                    if i == m - 1 {
                        j += 1;
                    } else if j == 0 {
                        i += 1;
                    } else {
                        i += 1;
                        j -= 1;
                    }
                }
                res
            })
            .collect()
    }
}
