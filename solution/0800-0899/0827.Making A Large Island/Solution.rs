use std::collections::HashSet;
impl Solution {
    fn dfs(
        i: usize,
        j: usize,
        grid: &Vec<Vec<i32>>,
        paths: &mut Vec<(usize, usize)>,
        vis: &mut Vec<Vec<bool>>,
    ) {
        let n = vis.len();
        if vis[i][j] || grid[i][j] != 1 {
            return;
        }
        paths.push((i, j));
        vis[i][j] = true;
        if i != 0 {
            Self::dfs(i - 1, j, grid, paths, vis);
        }
        if j != 0 {
            Self::dfs(i, j - 1, grid, paths, vis);
        }
        if i != n - 1 {
            Self::dfs(i + 1, j, grid, paths, vis);
        }
        if j != n - 1 {
            Self::dfs(i, j + 1, grid, paths, vis);
        }
    }

    pub fn largest_island(mut grid: Vec<Vec<i32>>) -> i32 {
        let n = grid.len();
        let mut vis = vec![vec![false; n]; n];
        let mut group = vec![vec![0; n]; n];
        let mut count = 1;
        for i in 0..n {
            for j in 0..n {
                let mut paths: Vec<(usize, usize)> = Vec::new();
                Self::dfs(i, j, &grid, &mut paths, &mut vis);
                let m = paths.len() as i32;
                if m != 0 {
                    for (x, y) in paths {
                        grid[x][y] = m;
                        group[x][y] = count;
                    }
                    count += 1;
                }
            }
        }
        let mut res = 0;
        for i in 0..n {
            for j in 0..n {
                let mut sum = grid[i][j];
                if grid[i][j] == 0 {
                    sum += 1;
                    let mut set = HashSet::new();
                    if i != 0 {
                        sum += grid[i - 1][j];
                        set.insert(group[i - 1][j]);
                    }
                    if j != 0 && !set.contains(&group[i][j - 1]) {
                        sum += grid[i][j - 1];
                        set.insert(group[i][j - 1]);
                    }
                    if i != n - 1 && !set.contains(&group[i + 1][j]) {
                        sum += grid[i + 1][j];
                        set.insert(group[i + 1][j]);
                    }
                    if j != n - 1 && !set.contains(&group[i][j + 1]) {
                        sum += grid[i][j + 1];
                        set.insert(group[i][j + 1]);
                    }
                }
                res = res.max(sum);
            }
        }
        res
    }
}
