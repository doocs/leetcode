impl Solution {
    pub fn can_partition_grid(grid: Vec<Vec<i32>>) -> bool {
        let mut s: i64 = 0;
        for row in &grid {
            for &x in row {
                s += x as i64;
            }
        }

        if s % 2 != 0 {
            return false;
        }

        let m = grid.len();
        let n = grid[0].len();

        let mut pre: i64 = 0;
        for i in 0..m {
            for &x in &grid[i] {
                pre += x as i64;
            }
            if pre * 2 == s && i + 1 < m {
                return true;
            }
        }

        pre = 0;
        for j in 0..n {
            for i in 0..m {
                pre += grid[i][j] as i64;
            }
            if pre * 2 == s && j + 1 < n {
                return true;
            }
        }

        false
    }
}
