use std::collections::VecDeque;

impl Solution {
    pub fn maximum_safeness_factor(grid: Vec<Vec<i32>>) -> i32 {
        let n = grid.len();
        if grid[0][0] == 1 || grid[n - 1][n - 1] == 1 {
            return 0;
        }

        let inf: i32 = 1 << 30;
        let mut dist = vec![vec![inf; n]; n];
        let mut q = VecDeque::new();

        for i in 0..n {
            for j in 0..n {
                if grid[i][j] == 1 {
                    dist[i][j] = 0;
                    q.push_back((i as i32, j as i32));
                }
            }
        }

        let dirs = [-1, 0, 1, 0, -1];

        while let Some((i, j)) = q.pop_front() {
            for k in 0..4 {
                let x = i + dirs[k];
                let y = j + dirs[k + 1];
                if x >= 0 && x < n as i32 && y >= 0 && y < n as i32 {
                    let (x, y) = (x as usize, y as usize);
                    let (i, j) = (i as usize, j as usize);
                    if dist[x][y] == inf {
                        dist[x][y] = dist[i][j] + 1;
                        q.push_back((x as i32, y as i32));
                    }
                }
            }
        }

        let mut t: Vec<(i32, usize, usize)> = Vec::new();
        for i in 0..n {
            for j in 0..n {
                t.push((dist[i][j], i, j));
            }
        }

        t.sort_by(|a, b| b.0.cmp(&a.0));

        let mut uf = UnionFind::new(n * n);
        let dirs = [-1, 0, 1, 0, -1];

        for (d, i, j) in t {
            for k in 0..4 {
                let x = i as i32 + dirs[k];
                let y = j as i32 + dirs[k + 1];
                if x >= 0 && x < n as i32 && y >= 0 && y < n as i32 {
                    let (x, y) = (x as usize, y as usize);
                    if dist[x][y] >= d {
                        uf.union(i * n + j, x * n + y);
                    }
                }
            }

            if uf.find(0) == uf.find(n * n - 1) {
                return d;
            }
        }

        0
    }
}

struct UnionFind {
    p: Vec<usize>,
}

impl UnionFind {
    fn new(n: usize) -> Self {
        let mut p = vec![0; n];
        for i in 0..n {
            p[i] = i;
        }
        Self { p }
    }

    fn find(&mut self, x: usize) -> usize {
        if self.p[x] != x {
            self.p[x] = self.find(self.p[x]);
        }
        self.p[x]
    }

    fn union(&mut self, a: usize, b: usize) -> bool {
        let pa = self.find(a);
        let pb = self.find(b);
        if pa == pb {
            return false;
        }
        self.p[pa] = pb;
        true
    }
}
