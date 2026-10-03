const DIRS: [i32; 5] = [-1, 0, 1, 0, -1];

impl Solution {
    pub fn num_islands(grid: Vec<Vec<char>>) -> i32 {
        let mut grid = grid;
        let m = grid.len();
        let n = grid[0].len();
        let mut ans = 0;
        for i in 0..m {
            for j in 0..n {
                if grid[i][j] != '1' {
                    continue;
                }
                grid[i][j] = '0';
                let mut stk = vec![(i, j)];
                while let Some((a, b)) = stk.pop() {
                    for k in 0..4 {
                        let x = a as i32 + DIRS[k];
                        let y = b as i32 + DIRS[k + 1];
                        if x >= 0 && y >= 0 {
                            let (x, y) = (x as usize, y as usize);
                            if x < m && y < n && grid[x][y] == '1' {
                                grid[x][y] = '0';
                                stk.push((x, y));
                            }
                        }
                    }
                }
                ans += 1;
            }
        }
        ans
    }
}
