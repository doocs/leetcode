impl Solution {
    fn dfs(board: &mut Vec<Vec<char>>, chars: &Vec<char>, i: usize, j: usize, mut k: usize) -> bool {
        if board[i][j] != chars[k] {
            return false;
        }
        k += 1;
        if k == chars.len() {
            return true;
        }
        let temp = board[i][j];
        board[i][j] = ' ';
        if i != 0 && Self::dfs(board, chars, i - 1, j, k)
            || j != 0 && Self::dfs(board, chars, i, j - 1, k)
            || i != board.len() - 1 && Self::dfs(board, chars, i + 1, j, k)
            || j != board[0].len() - 1 && Self::dfs(board, chars, i, j + 1, k)
        {
            return true;
        }
        board[i][j] = temp;
        false
    }

    pub fn exist(mut board: Vec<Vec<char>>, word: String) -> bool {
        let m = board.len();
        let n = board[0].len();
        let chars = word.chars().collect::<Vec<char>>();
        for i in 0..m {
            for j in 0..n {
                if Self::dfs(&mut board, &chars, i, j, 0) {
                    return true;
                }
            }
        }
        false
    }
}
