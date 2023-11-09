use std::collections::VecDeque;

impl Solution {
    #[allow(dead_code)]
    pub fn highest_peak(is_water: Vec<Vec<i32>>) -> Vec<Vec<i32>> {
        let n = is_water.len();
        let m = is_water[0].len();
        let mut ret_vec = vec![vec![-1; m]; n];
        let mut q: VecDeque<(usize, usize)> = VecDeque::new();
        let vis_pair: Vec<(i32, i32)> = vec![(-1, 0), (1, 0), (0, -1), (0, 1)];

        // Initialize the return vector
        for i in 0..n {
            for j in 0..m {
                if is_water[i][j] == 1 {
                    // This cell is water, the height of which must be 0
                    ret_vec[i][j] = 0;
                    q.push_back((i, j));
                }
            }
        }

        while !q.is_empty() {
            // Get the front X-Y Coordinates
            let (x, y) = q.front().unwrap().clone();
            q.pop_front();
            // Traverse through the vis pair
            for d in &vis_pair {
                let (dx, dy) = *d;
                if Self::check_bounds((x as i32) + dx, (y as i32) + dy, n as i32, m as i32) {
                    if ret_vec[((x as i32) + dx) as usize][((y as i32) + dy) as usize] == -1 {
                        // This cell hasn't been visited, update its height
                        ret_vec[((x as i32) + dx) as usize][((y as i32) + dy) as usize] =
                            ret_vec[x][y] + 1;
                        // Enqueue the current cell
                        q.push_back((((x as i32) + dx) as usize, ((y as i32) + dy) as usize));
                    }
                }
            }
        }

        ret_vec
    }

    #[allow(dead_code)]
    fn check_bounds(i: i32, j: i32, n: i32, m: i32) -> bool {
        i >= 0 && i < n && j >= 0 && j < m
    }
}
