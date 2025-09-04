impl Solution {
    pub fn is_valid_sudoku(board: Vec<Vec<char>>) -> bool {
        let mut row = vec![vec![false; 9]; 9];
        let mut col = vec![vec![false; 9]; 9];
        let mut sub = vec![vec![false; 9]; 9];

        for i in 0..9 {
            for j in 0..9 {
                let c = board[i][j];
                if c == '.' {
                    continue;
                }
                let num = (c as u8 - b'0' - 1) as usize;
                let k = i / 3 * 3 + j / 3;
                if row[i][num] || col[j][num] || sub[k][num] {
                    return false;
                }
                row[i][num] = true;
                col[j][num] = true;
                sub[k][num] = true;
            }
        }
        true
    }
}
