struct BinaryIndexedTree {
    n: usize,
    tree: Vec<i32>,
}

impl BinaryIndexedTree {
    fn new(n: usize) -> Self {
        Self {
            n,
            tree: vec![0; n + 1],
        }
    }

    fn update(&mut self, mut i: usize, delta: i32) {
        i += 1;
        while i <= self.n {
            self.tree[i] ^= delta;
            i += i & i.wrapping_neg();
        }
    }

    fn query(&self, mut i: usize) -> i32 {
        let mut res = 0;
        while i > 0 {
            res ^= self.tree[i];
            i -= i & i.wrapping_neg();
        }
        res
    }
}

impl Solution {
    pub fn palindrome_path(
        n: i32,
        edges: Vec<Vec<i32>>,
        s: String,
        queries: Vec<String>,
    ) -> Vec<bool> {
        let n = n as usize;
        let mut g = vec![vec![]; n];
        for e in &edges {
            let u = e[0] as usize;
            let v = e[1] as usize;
            g[u].push(v);
            g[v].push(u);
        }

        let mut parent = vec![0usize; n];
        let mut depth = vec![0usize; n];
        let mut tin = vec![0usize; n];
        let mut tout = vec![0usize; n];
        let mut timer = 0usize;

        fn dfs(
            u: usize,
            p: usize,
            g: &[Vec<usize>],
            parent: &mut [usize],
            depth: &mut [usize],
            tin: &mut [usize],
            tout: &mut [usize],
            timer: &mut usize,
        ) {
            parent[u] = if p == usize::MAX { 0 } else { p };
            tin[u] = *timer;
            *timer += 1;
            for &v in &g[u] {
                if v != p {
                    depth[v] = depth[u] + 1;
                    dfs(v, u, g, parent, depth, tin, tout, timer);
                }
            }
            tout[u] = *timer;
        }
        dfs(
            0,
            usize::MAX,
            &g,
            &mut parent,
            &mut depth,
            &mut tin,
            &mut tout,
            &mut timer,
        );

        let m = (32 - (n as u32).leading_zeros()) as usize;
        let mut up = vec![vec![0usize; n]; m];
        up[0].clone_from(&parent);
        for j in 1..m {
            for i in 0..n {
                up[j][i] = up[j - 1][up[j - 1][i]];
            }
        }

        let lca = |mut u: usize, mut v: usize| -> usize {
            if depth[u] < depth[v] {
                std::mem::swap(&mut u, &mut v);
            }
            let mut d = depth[u] - depth[v];
            let mut j = 0;
            while d > 0 {
                if d & 1 == 1 {
                    u = up[j][u];
                }
                d >>= 1;
                j += 1;
            }
            if u == v {
                return u;
            }
            for j in (0..m).rev() {
                if up[j][u] != up[j][v] {
                    u = up[j][u];
                    v = up[j][v];
                }
            }
            parent[u]
        };

        let bytes = s.as_bytes();
        let mut bit = vec![0i32; n];
        for i in 0..n {
            bit[i] = 1 << (bytes[i] - b'a') as u32;
        }

        let mut tree = BinaryIndexedTree::new(n);
        for i in 0..n {
            tree.update(tin[i], bit[i]);
            tree.update(tout[i], bit[i]);
        }

        let mut ans = Vec::new();
        for q in &queries {
            let parts: Vec<&str> = q.split(' ').collect();
            let u: usize = parts[1].parse().unwrap();
            if parts[0] == "update" {
                let x = bit[u];
                let y = 1 << (parts[2].as_bytes()[0] - b'a') as u32;
                if x != y {
                    let delta = x ^ y;
                    tree.update(tin[u], delta);
                    tree.update(tout[u], delta);
                    bit[u] = y;
                }
            } else {
                let v: usize = parts[2].parse().unwrap();
                let w = lca(u, v);
                let mask = tree.query(tin[u] + 1) ^ tree.query(tin[v] + 1) ^ bit[w];
                ans.push(mask & (mask - 1) == 0);
            }
        }
        ans
    }
}
