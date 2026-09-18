use std::collections::VecDeque;

impl Solution {
    pub fn find_median(n: i32, edges: Vec<Vec<i32>>, queries: Vec<Vec<i32>>) -> Vec<i32> {
        let n = n as usize;
        let m = 32 - (n as u32).leading_zeros() as usize;
        let mut g = vec![vec![]; n];
        for e in &edges {
            let u = e[0] as usize;
            let v = e[1] as usize;
            let w = e[2] as i64;
            g[u].push((v, w));
            g[v].push((u, w));
        }
        let mut f = vec![vec![0; m]; n];
        let mut p = vec![0; n];
        let mut depth = vec![0; n];
        let mut dist = vec![0i64; n];
        let mut q = VecDeque::new();
        q.push_back(0);
        while let Some(i) = q.pop_front() {
            f[i][0] = p[i];
            for j in 1..m {
                f[i][j] = f[f[i][j - 1]][j - 1];
            }
            for &(j, w) in &g[i] {
                if j != p[i] {
                    p[j] = i;
                    depth[j] = depth[i] + 1;
                    dist[j] = dist[i] + w;
                    q.push_back(j);
                }
            }
        }
        let mut ans = Vec::with_capacity(queries.len());
        for qq in &queries {
            let u = qq[0] as usize;
            let v = qq[1] as usize;
            if u == v {
                ans.push(u as i32);
                continue;
            }
            let (mut x, mut y) = (u, v);
            if depth[x] < depth[y] {
                std::mem::swap(&mut x, &mut y);
            }
            for j in (0..m).rev() {
                if depth[x] - depth[y] >= (1 << j) {
                    x = f[x][j];
                }
            }
            for j in (0..m).rev() {
                if f[x][j] != f[y][j] {
                    x = f[x][j];
                    y = f[y][j];
                }
            }
            if x != y {
                x = p[x];
            }
            let w = dist[u] + dist[v] - 2 * dist[x];
            if 2 * (dist[u] - dist[x]) >= w {
                let mut cur = u;
                for j in (0..m).rev() {
                    let k = f[cur][j];
                    if depth[k] >= depth[x] && 2 * (dist[u] - dist[k]) < w {
                        cur = k;
                    }
                }
                ans.push(p[cur] as i32);
            } else {
                let mut cur = v;
                for j in (0..m).rev() {
                    let k = f[cur][j];
                    if depth[k] > depth[x] && 2 * (dist[u] + dist[k] - 2 * dist[x]) >= w {
                        cur = k;
                    }
                }
                ans.push(cur as i32);
            }
        }
        ans
    }
}
