use std::collections::HashSet;

impl Solution {
    pub fn largest_island(grid: Vec<Vec<i32>>) -> i32 {
        let n = grid.len();
        let mut p = vec![vec![0; n]; n];
        let mut cnt = vec![0; n * n + 1];
        let dirs = [-1, 0, 1, 0, -1];
        let mut root = 0;
        let mut ans = 0;

        fn dfs(
            grid: &Vec<Vec<i32>>,
            p: &mut Vec<Vec<i32>>,
            cnt: &mut Vec<i32>,
            root: i32,
            i: usize,
            j: usize,
            dirs: &[i32; 5]
        ) -> i32 {
            p[i][j] = root;
            cnt[root as usize] += 1;
            for k in 0..4 {
                let x = (i as i32) + dirs[k];
                let y = (j as i32) + dirs[k + 1];
                if
                    x >= 0 &&
                    (x as usize) < grid.len() &&
                    y >= 0 &&
                    (y as usize) < grid.len() &&
                    grid[x as usize][y as usize] == 1 &&
                    p[x as usize][y as usize] == 0
                {
                    dfs(grid, p, cnt, root, x as usize, y as usize, dirs);
                }
            }
            cnt[root as usize]
        }

        for i in 0..n {
            for j in 0..n {
                if grid[i][j] == 1 && p[i][j] == 0 {
                    root += 1;
                    ans = ans.max(dfs(&grid, &mut p, &mut cnt, root, i, j, &dirs));
                }
            }
        }

        for i in 0..n {
            for j in 0..n {
                if grid[i][j] == 0 {
                    let mut s = HashSet::new();
                    for k in 0..4 {
                        let x = (i as i32) + dirs[k];
                        let y = (j as i32) + dirs[k + 1];
                        if x >= 0 && (x as usize) < n && y >= 0 && (y as usize) < n {
                            s.insert(p[x as usize][y as usize]);
                        }
                    }
                    let mut t = 1;
                    for &x in &s {
                        t += cnt[x as usize];
                    }
                    ans = ans.max(t);
                }
            }
        }
        ans
    }
}
