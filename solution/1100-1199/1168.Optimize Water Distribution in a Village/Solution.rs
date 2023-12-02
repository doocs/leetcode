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

    fn union(&mut self, a: usize, b: usize) -> bool {
        let pa = self.find(a);
        let pb = self.find(b);
        if pa == pb {
            false
        } else if self.size[pa] > self.size[pb] {
            self.p[pb] = pa;
            self.size[pa] += self.size[pb];
            true
        } else {
            self.p[pa] = pb;
            self.size[pb] += self.size[pa];
            true
        }
    }
}

impl Solution {
    pub fn min_cost_to_supply_water(n: i32, wells: Vec<i32>, pipes: Vec<Vec<i32>>) -> i32 {
        let n = n as usize;
        let mut pipes = pipes;
        for i in 0..n {
            pipes.push(vec![0, (i + 1) as i32, wells[i]]);
        }
        pipes.sort_by(|a, b| a[2].cmp(&b[2]));
        let mut uf = UnionFind::new(n + 1);
        let mut ans = 0;
        for pipe in pipes {
            let a = pipe[0] as usize;
            let b = pipe[1] as usize;
            let c = pipe[2];
            if uf.union(a, b) {
                ans += c;
                if n == 0 {
                    break;
                }
            }
        }
        ans
    }
}
