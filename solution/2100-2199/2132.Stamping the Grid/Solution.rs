impl Solution {
    pub fn possible_to_stamp(grid: Vec<Vec<i32>>, stamp_height: i32, stamp_width: i32) -> bool {
        let n: usize = grid.len();
        let m: usize = grid[0].len();

        let mut prefix_vec: Vec<Vec<i32>> = vec![vec![0; m + 1]; n + 1];

        // Initialize the prefix vector
        for i in 0..n {
            for j in 0..m {
                prefix_vec[i + 1][j + 1] =
                    prefix_vec[i][j + 1] + prefix_vec[i + 1][j] - prefix_vec[i][j] + grid[i][j];
            }
        }

        let mut diff_vec: Vec<Vec<i32>> = vec![vec![0; m + 1]; n + 1];

        // Construct the difference vector based on prefix sum vector
        for i in 0..n {
            for j in 0..m {
                // If the value of the cell is one, just bypass this
                if grid[i][j] == 1 {
                    continue;
                }
                // Otherwise, try stick the stamp
                let x: usize = i + (stamp_height as usize);
                let y: usize = j + (stamp_width as usize);
                // Check the bound
                if x <= n && y <= m {
                    // If the region can be sticked (All cells are empty, which means the sum will be zero)
                    if
                        prefix_vec[x][y] - prefix_vec[x][j] - prefix_vec[i][y] + prefix_vec[i][j] ==
                        0
                    {
                        // Update the difference vector
                        diff_vec[i][j] += 1;
                        diff_vec[x][y] += 1;

                        diff_vec[x][j] -= 1;
                        diff_vec[i][y] -= 1;
                    }
                }
            }
        }

        let mut check_vec: Vec<Vec<i32>> = vec![vec![0; m + 1]; n + 1];

        // Check the prefix sum of difference vector, to determine if there is any empty cell left
        for i in 0..n {
            for j in 0..m {
                // If the value of the cell is one, just bypass this
                if grid[i][j] == 1 {
                    continue;
                }
                // Otherwise, check if the region is empty, by calculating the prefix sum of difference vector
                check_vec[i + 1][j + 1] =
                    check_vec[i][j + 1] + check_vec[i + 1][j] - check_vec[i][j] + diff_vec[i][j];
                if check_vec[i + 1][j + 1] == 0 {
                    return false;
                }
            }
        }

        true
    }
}
