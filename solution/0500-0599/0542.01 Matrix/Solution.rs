use std::collections::VecDeque;

impl Solution {
    #[allow(dead_code)]
    pub fn update_matrix(mat: Vec<Vec<i32>>) -> Vec<Vec<i32>> {
        let n: usize = mat.len();
        let m: usize = mat[0].len();
        let mut ret_vec: Vec<Vec<i32>> = vec![vec![-1; m]; n];
        // The inner tuple is of <X, Y, Current Count>
        let mut the_q: VecDeque<(usize, usize)> = VecDeque::new();
        let traverse_vec: Vec<(i32, i32)> = vec![(-1, 0), (1, 0), (0, 1), (0, -1)];

        // Initialize the queue
        for i in 0..n {
            for j in 0..m {
                if mat[i][j] == 0 {
                    // For the zero cell, enqueue at first
                    the_q.push_back((i, j));
                    // Set to 0 in return vector
                    ret_vec[i][j] = 0;
                }
            }
        }

        while !the_q.is_empty() {
            let (x, y) = the_q.front().unwrap().clone();
            the_q.pop_front();
            for pair in &traverse_vec {
                let cur_x = pair.0 + (x as i32);
                let cur_y = pair.1 + (y as i32);
                if
                    Solution::check_bounds(cur_x, cur_y, n as i32, m as i32) &&
                    ret_vec[cur_x as usize][cur_y as usize] == -1
                {
                    // The current cell has not be updated yet, and is also in bound
                    ret_vec[cur_x as usize][cur_y as usize] = ret_vec[x][y] + 1;
                    the_q.push_back((cur_x as usize, cur_y as usize));
                }
            }
        }

        ret_vec
    }

    #[allow(dead_code)]
    pub fn check_bounds(i: i32, j: i32, n: i32, m: i32) -> bool {
        i >= 0 && i < n && j >= 0 && j < m
    }
}
