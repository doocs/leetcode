impl Solution {
    pub fn sort_matrix(mut grid: Vec<Vec<i32>>) -> Vec<Vec<i32>> {
        let n = grid.len();
        if n <= 1 {
            return grid;
        }
        for k in (0..=n - 2).rev() {
            let mut i = k;
            let mut j = 0;
            let mut t = Vec::new();
            while i < n && j < n {
                t.push(grid[i][j]);
                i += 1;
                j += 1;
            }
            t.sort();
            let mut i = k;
            let mut j = 0;
            while i < n && j < n {
                grid[i][j] = t.pop().unwrap();
                i += 1;
                j += 1;
            }
        }
        for k in (1..=n - 2).rev() {
            let mut i = k;
            let mut j = n - 1;
            let mut t = Vec::new();
            loop {
                t.push(grid[i][j]);
                if i == 0 {
                    break;
                }
                i -= 1;
                j -= 1;
            }
            t.sort();
            let mut i = k;
            let mut j = n - 1;
            loop {
                grid[i][j] = t.pop().unwrap();
                if i == 0 {
                    break;
                }
                i -= 1;
                j -= 1;
            }
        }
        grid
    }
}
