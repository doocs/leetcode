struct UnionFind {
    p: Vec<usize>,
    size: Vec<usize>,
}

impl UnionFind {
    fn new(n: usize) -> Self {
        let mut p = vec![0; n];
        let mut size = vec![1; n];
        for i in 0..n {
            p[i] = i;
        }
        Self { p, size }
    }

    fn find(&mut self, x: usize) -> usize {
        if self.p[x] != x {
            let px = self.p[x];
            self.p[x] = self.find(px);
        }
        self.p[x]
    }

    fn union(&mut self, a: usize, b: usize) -> bool {
        let pa = self.find(a);
        let pb = self.find(b);
        if pa == pb {
            return false;
        }
        if self.size[pa] > self.size[pb] {
            self.p[pb] = pa;
            self.size[pa] += self.size[pb];
        } else {
            self.p[pa] = pb;
            self.size[pb] += self.size[pa];
        }
        true
    }
}

impl Solution {
    pub fn latest_day_to_cross(row: i32, col: i32, cells: Vec<Vec<i32>>) -> i32 {
        let mn = cells.len();
        let mut uf = UnionFind::new(mn + 2);
        let s = mn;
        let t = mn + 1;
        let row = row as usize;
        let col = col as usize;

        let mut g = vec![vec![1i32; col]; row];
        let dirs = [-1, 0, 1, 0, -1];

        let mut i = mn as i32 - 1;
        loop {
            let x = (cells[i as usize][0] - 1) as usize;
            let y = (cells[i as usize][1] - 1) as usize;
            g[x][y] = 0;

            for j in 0..4 {
                let nx = x as i32 + dirs[j];
                let ny = y as i32 + dirs[j + 1];
                if nx >= 0
                    && nx < row as i32
                    && ny >= 0
                    && ny < col as i32
                    && g[nx as usize][ny as usize] == 0
                {
                    uf.union(x * col + y, nx as usize * col + ny as usize);
                }
            }

            if x == 0 {
                uf.union(s, x * col + y);
            }
            if x == row - 1 {
                uf.union(t, x * col + y);
            }
            if uf.find(s) == uf.find(t) {
                return i;
            }
            i -= 1;
        }
    }
}
