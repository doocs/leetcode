impl Solution {
    pub fn count_pairs(n: i32, edges: Vec<Vec<i32>>) -> i64 {
        let n = n as usize;
        let mut g = vec![vec![]; n];
        let mut vis = vec![false; n];
        for e in edges {
            let u = e[0] as usize;
            let v = e[1] as usize;
            g[u].push(v);
            g[v].push(u);
        }

        fn dfs(g: &Vec<Vec<usize>>, vis: &mut Vec<bool>, u: usize) -> i64 {
            if vis[u] {
                return 0;
            }
            vis[u] = true;
            let mut cnt = 1;
            for &v in &g[u] {
                cnt += dfs(g, vis, v);
            }
            cnt
        }

        let mut ans = 0;
        let mut s = 0;
        for u in 0..n {
            let t = dfs(&g, &mut vis, u);
            ans += t * s;
            s += t;
        }
        ans
    }
}
