impl Solution {
    pub fn num_rook_captures(board: Vec<Vec<char>>) -> i32 {
        let dirs = [-1, 0, 1, 0, -1];
        let n = board.len();
        for i in 0..n {
            for j in 0..n {
                if board[i][j] == 'R' {
                    let mut ans = 0;
                    for k in 0..4 {
                        let mut x = i as i32 + dirs[k];
                        let mut y = j as i32 + dirs[k + 1];
                        while x >= 0
                            && x < n as i32
                            && y >= 0
                            && y < n as i32
                            && board[x as usize][y as usize] != 'B'
                        {
                            if board[x as usize][y as usize] == 'p' {
                                ans += 1;
                                break;
                            }
                            x += dirs[k];
                            y += dirs[k + 1];
                        }
                    }
                    return ans;
                }
            }
        }
        0
    }
}
