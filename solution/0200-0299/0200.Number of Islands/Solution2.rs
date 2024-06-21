use std::collections::VecDeque;

const DIRS: [i32; 5] = [-1, 0, 1, 0, -1];

impl Solution {
    pub fn num_islands(grid: Vec<Vec<char>>) -> i32 {
        fn bfs(grid: &mut Vec<Vec<char>>, i: usize, j: usize) {
            grid[i][j] = '0';
            let mut queue = VecDeque::from([(i, j)]);
            while !queue.is_empty() {
                let (i, j) = queue.pop_front().unwrap();
                for k in 0..4 {
                    let x = (i as i32) + DIRS[k];
                    let y = (j as i32) + DIRS[k + 1];
                    if x >= 0
                        && (x as usize) < grid.len()
                        && y >= 0
                        && (y as usize) < grid[0].len()
                        && grid[x as usize][y as usize] == '1'
                    {
                        grid[x as usize][y as usize] = '0';
                        queue.push_back((x as usize, y as usize));
                    }
                }
            }
        }

        let mut grid = grid;
        let mut ans = 0;
        for i in 0..grid.len() {
            for j in 0..grid[0].len() {
                if grid[i][j] == '1' {
                    bfs(&mut grid, i, j);
                    ans += 1;
                }
            }
        }
        ans
    }
}
