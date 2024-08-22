use std::collections::VecDeque;

impl Solution {
    pub fn update_matrix(mat: Vec<Vec<i32>>) -> Vec<Vec<i32>> {
        let m = mat.len();
        let n = mat[0].len();
        let mut ans = vec![vec![-1; n]; m];
        let mut q = VecDeque::new();

        for i in 0..m {
            for j in 0..n {
                if mat[i][j] == 0 {
                    q.push_back((i, j));
                    ans[i][j] = 0;
                }
            }
        }

        let dirs = [-1, 0, 1, 0, -1];
        while let Some((i, j)) = q.pop_front() {
            for k in 0..4 {
                let x = i as isize + dirs[k];
                let y = j as isize + dirs[k + 1];
                if x >= 0 && x < m as isize && y >= 0 && y < n as isize {
                    let x = x as usize;
                    let y = y as usize;
                    if ans[x][y] == -1 {
                        ans[x][y] = ans[i][j] + 1;
                        q.push_back((x, y));
                    }
                }
            }
        }

        ans
    }
}
