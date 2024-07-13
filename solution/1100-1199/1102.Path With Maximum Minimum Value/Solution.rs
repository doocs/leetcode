struct UnionFind {
    p: Vec<usize>,
    size: Vec<usize>,
}

impl UnionFind {
    fn new(n: usize) -> Self {
        let p: Vec<usize> = (0..n).collect();
        let size = vec![1; n];
        UnionFind { p, size }
    }

    fn find(&mut self, x: usize) -> usize {
        if self.p[x] != x {
            self.p[x] = self.find(self.p[x]);
        }
        self.p[x]
    }

    fn union(&mut self, a: usize, b: usize) {
        let pa = self.find(a);
        let pb = self.find(b);
        if pa != pb {
            if self.size[pa] > self.size[pb] {
                self.p[pb] = pa;
                self.size[pa] += self.size[pb];
            } else {
                self.p[pa] = pb;
                self.size[pb] += self.size[pa];
            }
        }
    }
}

impl Solution {
    pub fn maximum_minimum_path(grid: Vec<Vec<i32>>) -> i32 {
        let m = grid.len();
        let n = grid[0].len();
        let mut uf = UnionFind::new(m * n);
        let mut q: Vec<Vec<i32>> = Vec::new();

        for i in 0..m {
            for j in 0..n {
                q.push(vec![grid[i][j], i as i32, j as i32]);
            }
        }

        q.sort_by(|a, b| b[0].cmp(&a[0]));

        let mut vis: Vec<Vec<bool>> = vec![vec![false; n]; m];
        let dirs: [i32; 5] = [-1, 0, 1, 0, -1];
        let mut ans = 0;
        for k in 0..q.len() {
            if uf.find(0) == uf.find(m * n - 1) {
                break;
            }
            let t = &q[k];
            let (v, i, j) = (t[0], t[1] as usize, t[2] as usize);
            ans = v;
            vis[i][j] = true;
            for d in 0..4 {
                let x = (i as i32) + dirs[d];
                let y = (j as i32) + dirs[d + 1];
                if x >= 0
                    && x < (m as i32)
                    && y >= 0
                    && y < (n as i32)
                    && vis[x as usize][y as usize]
                {
                    uf.union((x as usize) * n + (y as usize), i * n + j);
                }
            }
        }
        ans
    }
}
