impl Solution {
    pub fn solve(board: &mut Vec<Vec<char>>) {
        let m = board.len();
        let n = board[0].len();
        let dirs = vec![-1, 0, 1, 0, -1];

        fn dfs(
            board: &mut Vec<Vec<char>>,
            i: usize,
            j: usize,
            dirs: &Vec<i32>,
            m: usize,
            n: usize
        ) {
            if i >= 0 && i < m && j >= 0 && j < n && board[i][j] == 'O' {
                board[i][j] = '.';
                for k in 0..4 {
                    dfs(
                        board,
                        ((i as i32) + dirs[k]) as usize,
                        ((j as i32) + dirs[k + 1]) as usize,
                        dirs,
                        m,
                        n
                    );
                }
            }
        }

        for i in 0..m {
            dfs(board, i, 0, &dirs, m, n);
            dfs(board, i, n - 1, &dirs, m, n);
        }
        for j in 0..n {
            dfs(board, 0, j, &dirs, m, n);
            dfs(board, m - 1, j, &dirs, m, n);
        }

        for i in 0..m {
            for j in 0..n {
                if board[i][j] == '.' {
                    board[i][j] = 'O';
                } else if board[i][j] == 'O' {
                    board[i][j] = 'X';
                }
            }
        }
    }
}
