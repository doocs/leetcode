impl Solution {
    fn dfs(i: usize, j: usize, mark: char, vis: &mut Vec<Vec<bool>>, board: &mut Vec<Vec<char>>) {
        if vis[i][j] || board[i][j] != mark {
            return;
        }
        vis[i][j] = true;
        if i > 0 {
            Self::dfs(i - 1, j, mark, vis, board);
        }
        if i < vis.len() - 1 {
            Self::dfs(i + 1, j, mark, vis, board);
        }
        if j > 0 {
            Self::dfs(i, j - 1, mark, vis, board);
        }
        if j < vis[0].len() - 1 {
            Self::dfs(i, j + 1, mark, vis, board);
        }
    }

    pub fn solve(board: &mut Vec<Vec<char>>) {
        let m = board.len();
        let n = board[0].len();
        let mut vis = vec![vec![false; n]; m];
        for i in 0..m {
            Self::dfs(i, 0, board[i][0], &mut vis, board);
            Self::dfs(i, n - 1, board[i][n - 1], &mut vis, board);
        }
        for i in 0..n {
            Self::dfs(0, i, board[0][i], &mut vis, board);
            Self::dfs(m - 1, i, board[m - 1][i], &mut vis, board);
        }
        for i in 0..m {
            for j in 0..n {
                if vis[i][j] {
                    continue;
                }
                board[i][j] = 'X';
            }
        }
    }
}
