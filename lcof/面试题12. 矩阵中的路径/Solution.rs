impl Solution {
    fn dfs(board: &mut Vec<Vec<char>>, chars: &Vec<char>, y: usize, x: usize, i: usize) -> bool {
        if board[y][x] != chars[i] {
            return false;
        }
        if i + 1 == chars.len() {
            return true;
        }
        let temp = board[y][x];
        board[y][x] = ' ';
        if y != 0 && Solution::dfs(board, chars, y - 1, x, i + 1)
            || x != 0 && Solution::dfs(board, chars, y, x - 1, i + 1)
            || y != board.len() - 1 && Solution::dfs(board, chars, y + 1, x, i + 1)
            || x != board[0].len() - 1 && Solution::dfs(board, chars, y, x + 1, i + 1)
        {
            return true;
        }
        board[y][x] = temp;
        false
    }

    pub fn exist(mut board: Vec<Vec<char>>, word: String) -> bool {
        let m = board.len();
        let n = board[0].len();
        let chars = word.chars().collect::<Vec<char>>();
        for i in 0..m {
            for j in 0..n {
                if Solution::dfs(&mut board, &chars, i, j, 0) {
                    return true;
                }
            }
        }
        false
    }
}
