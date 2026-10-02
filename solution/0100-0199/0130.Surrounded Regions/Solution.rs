impl Solution {
    pub fn solve(board: &mut Vec<Vec<char>>) {
        let m = board.len();
        if m == 0 {
            return;
        }
        let n = board[0].len();
        if n == 0 {
            return;
        }
        let mut stack = Vec::new();
        for i in 0..m {
            if board[i][0] == 'O' {
                board[i][0] = '.';
                stack.push((i, 0));
            }
            if board[i][n - 1] == 'O' {
                board[i][n - 1] = '.';
                stack.push((i, n - 1));
            }
        }
        for j in 0..n {
            if board[0][j] == 'O' {
                board[0][j] = '.';
                stack.push((0, j));
            }
            if board[m - 1][j] == 'O' {
                board[m - 1][j] = '.';
                stack.push((m - 1, j));
            }
        }
        while let Some((i, j)) = stack.pop() {
            if i > 0 && board[i - 1][j] == 'O' {
                board[i - 1][j] = '.';
                stack.push((i - 1, j));
            }
            if i + 1 < m && board[i + 1][j] == 'O' {
                board[i + 1][j] = '.';
                stack.push((i + 1, j));
            }
            if j > 0 && board[i][j - 1] == 'O' {
                board[i][j - 1] = '.';
                stack.push((i, j - 1));
            }
            if j + 1 < n && board[i][j + 1] == 'O' {
                board[i][j + 1] = '.';
                stack.push((i, j + 1));
            }
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
