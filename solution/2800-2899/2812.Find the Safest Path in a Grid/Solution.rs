use std::collections::VecDeque;
impl Solution {
    fn dfs(i: usize, j: usize, v: i32, g: &Vec<Vec<i32>>, vis: &mut Vec<Vec<bool>>) -> bool {
        if vis[i][j] || g[i][j] <= v {
            return false;
        }
        vis[i][j] = true;
        let n = g.len();
        i == n - 1 && j == n - 1
            || i != 0 && Self::dfs(i - 1, j, v, g, vis)
            || i != n - 1 && Self::dfs(i + 1, j, v, g, vis)
            || j != 0 && Self::dfs(i, j - 1, v, g, vis)
            || j != n - 1 && Self::dfs(i, j + 1, v, g, vis)
    }

    pub fn maximum_safeness_factor(grid: Vec<Vec<i32>>) -> i32 {
        let n = grid.len();
        let mut g = vec![vec![-1; n]; n];
        let mut q = VecDeque::new();
        for i in 0..n {
            for j in 0..n {
                if grid[i][j] == 1 {
                    q.push_back((i, j));
                }
            }
        }
        let mut level = 0;
        while !q.is_empty() {
            let m = q.len();
            for _ in 0..m {
                let (i, j) = q.pop_front().unwrap();
                if g[i][j] != -1 {
                    continue;
                }
                g[i][j] = level;
                if i != n - 1 {
                    q.push_back((i + 1, j));
                }
                if i != 0 {
                    q.push_back((i - 1, j));
                }
                if j != n - 1 {
                    q.push_back((i, j + 1));
                }
                if j != 0 {
                    q.push_back((i, j - 1));
                }
            }
            level += 1;
        }
        let mut left = 0;
        let mut right = level;
        while left < right {
            let mid = (left + right) >> 1;
            if Self::dfs(0, 0, mid, &g, &mut vec![vec![false; n]; n]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        right
    }
}
