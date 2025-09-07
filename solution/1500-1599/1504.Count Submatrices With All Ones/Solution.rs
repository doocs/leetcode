impl Solution {
    pub fn num_submat(mat: Vec<Vec<i32>>) -> i32 {
        let m = mat.len();
        let n = mat[0].len();
        let mut g = vec![vec![0; n]; m];

        for i in 0..m {
            for j in 0..n {
                if mat[i][j] == 1 {
                    if j == 0 {
                        g[i][j] = 1;
                    } else {
                        g[i][j] = 1 + g[i][j - 1];
                    }
                }
            }
        }

        let mut ans = 0;
        for i in 0..m {
            for j in 0..n {
                let mut col = i32::MAX;
                let mut k = i as i32;
                while k >= 0 && col > 0 {
                    col = col.min(g[k as usize][j]);
                    ans += col;
                    k -= 1;
                }
            }
        }
        ans
    }
}
