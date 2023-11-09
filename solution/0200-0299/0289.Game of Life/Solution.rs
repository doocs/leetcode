const DIR: [(i32, i32); 8] = [
    (-1, 0),
    (1, 0),
    (0, -1),
    (0, 1),
    (-1, -1),
    (-1, 1),
    (1, -1),
    (1, 1),
];

impl Solution {
    #[allow(dead_code)]
    pub fn game_of_life(board: &mut Vec<Vec<i32>>) {
        let n = board.len();
        let m = board[0].len();
        let mut weight_vec: Vec<Vec<i32>> = vec![vec![0; m]; n];

        // Initialize the weight vector
        for i in 0..n {
            for j in 0..m {
                if board[i][j] == 0 {
                    continue;
                }
                for (dx, dy) in DIR {
                    let x = (i as i32) + dx;
                    let y = (j as i32) + dy;
                    if Self::check_bounds(x, y, n as i32, m as i32) {
                        weight_vec[x as usize][y as usize] += 1;
                    }
                }
            }
        }

        // Update the board
        for i in 0..n {
            for j in 0..m {
                if weight_vec[i][j] < 2 {
                    board[i][j] = 0;
                } else if weight_vec[i][j] <= 3 {
                    if board[i][j] == 0 && weight_vec[i][j] == 3 {
                        board[i][j] = 1;
                    }
                } else {
                    board[i][j] = 0;
                }
            }
        }
    }

    #[allow(dead_code)]
    fn check_bounds(i: i32, j: i32, n: i32, m: i32) -> bool {
        i >= 0 && i < n && j >= 0 && j < m
    }
}
