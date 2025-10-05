use std::collections::VecDeque;

impl Solution {
    pub fn pacific_atlantic(heights: Vec<Vec<i32>>) -> Vec<Vec<i32>> {
        let (m, n) = (heights.len(), heights[0].len());
        let mut vis1 = vec![vec![false; n]; m];
        let mut vis2 = vec![vec![false; n]; m];
        let mut q1 = VecDeque::new();
        let mut q2 = VecDeque::new();
        let dirs = [-1, 0, 1, 0, -1];

        for i in 0..m {
            q1.push_back((i, 0));
            vis1[i][0] = true;
            q2.push_back((i, n - 1));
            vis2[i][n - 1] = true;
        }
        for j in 0..n {
            q1.push_back((0, j));
            vis1[0][j] = true;
            q2.push_back((m - 1, j));
            vis2[m - 1][j] = true;
        }

        let bfs = |q: &mut VecDeque<(usize, usize)>, vis: &mut Vec<Vec<bool>>| {
            while let Some((x, y)) = q.pop_front() {
                for k in 0..4 {
                    let nx = x as i32 + dirs[k];
                    let ny = y as i32 + dirs[k + 1];
                    if nx >= 0
                        && nx < m as i32
                        && ny >= 0
                        && ny < n as i32
                        && !vis[nx as usize][ny as usize]
                        && heights[nx as usize][ny as usize] >= heights[x][y]
                    {
                        vis[nx as usize][ny as usize] = true;
                        q.push_back((nx as usize, ny as usize));
                    }
                }
            }
        };

        bfs(&mut q1, &mut vis1);
        bfs(&mut q2, &mut vis2);

        let mut ans = vec![];
        for i in 0..m {
            for j in 0..n {
                if vis1[i][j] && vis2[i][j] {
                    ans.push(vec![i as i32, j as i32]);
                }
            }
        }
        ans
    }
}
