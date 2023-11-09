impl Solution {
    #[allow(dead_code)]
    pub fn contains_cycle(grid: Vec<Vec<char>>) -> bool {
        let n = grid.len();
        let m = grid[0].len();
        let mut d_set: Vec<usize> = vec![0; n * m];

        // Initialize the disjoint set
        for i in 0..n * m {
            d_set[i] = i;
        }

        // Traverse the grid
        for i in 0..n {
            for j in 0..m {
                if i + 1 < n && grid[i + 1][j] == grid[i][j] {
                    // Check the below cell
                    let p_curr = Self::find(i * m + j, &mut d_set);
                    let p_below = Self::find((i + 1) * m + j, &mut d_set);
                    if p_curr == p_below {
                        return true;
                    }
                    // Otherwise, union the two cells
                    Self::union(p_curr, p_below, &mut d_set);
                }
                // Same to the right cell
                if j + 1 < m && grid[i][j + 1] == grid[i][j] {
                    let p_curr = Self::find(i * m + j, &mut d_set);
                    let p_right = Self::find(i * m + (j + 1), &mut d_set);
                    if p_curr == p_right {
                        return true;
                    }
                    // Otherwise, union the two cells
                    Self::union(p_curr, p_right, &mut d_set);
                }
            }
        }

        false
    }

    #[allow(dead_code)]
    fn find(x: usize, d_set: &mut Vec<usize>) -> usize {
        if d_set[x] != x {
            d_set[x] = Self::find(d_set[x], d_set);
        }
        d_set[x]
    }

    #[allow(dead_code)]
    fn union(x: usize, y: usize, d_set: &mut Vec<usize>) {
        let p_x = Self::find(x, d_set);
        let p_y = Self::find(y, d_set);
        d_set[p_x] = p_y;
    }
}
