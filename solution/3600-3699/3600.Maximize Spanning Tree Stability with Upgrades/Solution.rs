struct UnionFind {
    p: Vec<i32>,
    sz: Vec<i32>,
    cnt: i32,
}

impl UnionFind {
    fn new(n: i32) -> Self {
        Self {
            p: (0..n).collect(),
            sz: vec![1; n as usize],
            cnt: n,
        }
    }

    fn find(&mut self, x: i32) -> i32 {
        let i = x as usize;
        if self.p[i] != x {
            self.p[i] = self.find(self.p[i]);
        }
        self.p[i]
    }

    fn union(&mut self, a: i32, b: i32) -> bool {
        let (pa, pb) = (self.find(a), self.find(b));
        if pa == pb {
            return false;
        }
        let (a, b) = (pa as usize, pb as usize);
        if self.sz[a] < self.sz[b] {
            self.p[a] = pb;
            self.sz[b] += self.sz[a];
        } else {
            self.p[b] = pa;
            self.sz[a] += self.sz[b];
        }
        self.cnt -= 1;
        true
    }
}

impl Solution {
    pub fn max_stability(n: i32, edges: Vec<Vec<i32>>, k: i32) -> i32 {
        let mut uf = UnionFind::new(n);
        let mut mn = 1_000_000;

        for e in &edges {
            if e[3] == 1 {
                mn = mn.min(e[2]);
                if !uf.union(e[0], e[1]) {
                    return -1;
                }
            }
        }

        for e in &edges {
            uf.union(e[0], e[1]);
        }

        if uf.cnt > 1 {
            return -1;
        }

        let check = |lim: i32| {
            let mut uf = UnionFind::new(n);

            for e in &edges {
                if e[2] >= lim {
                    uf.union(e[0], e[1]);
                }
            }

            let mut rem = k;
            for e in &edges {
                if rem > 0 && e[2] * 2 >= lim && uf.union(e[0], e[1]) {
                    rem -= 1;
                }
            }

            uf.cnt == 1
        };

        let (mut l, mut r) = (1, mn);
        while l < r {
            let mid = (l + r + 1) >> 1;
            if check(mid) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }

        l
    }
}
