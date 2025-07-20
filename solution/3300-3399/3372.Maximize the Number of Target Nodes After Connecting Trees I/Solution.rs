impl Solution {
    pub fn max_target_nodes(edges1: Vec<Vec<i32>>, edges2: Vec<Vec<i32>>, k: i32) -> Vec<i32> {
        fn build(edges: &Vec<Vec<i32>>) -> Vec<Vec<i32>> {
            let n = edges.len() + 1;
            let mut g = vec![vec![]; n];
            for e in edges {
                let a = e[0] as usize;
                let b = e[1] as usize;
                g[a].push(b as i32);
                g[b].push(a as i32);
            }
            g
        }

        fn dfs(g: &Vec<Vec<i32>>, a: usize, fa: i32, d: i32) -> i32 {
            if d < 0 {
                return 0;
            }
            let mut cnt = 1;
            for &b in &g[a] {
                if b != fa {
                    cnt += dfs(g, b as usize, a as i32, d - 1);
                }
            }
            cnt
        }

        let g2 = build(&edges2);
        let m = edges2.len() + 1;
        let mut t = 0;
        for i in 0..m {
            t = t.max(dfs(&g2, i, -1, k - 1));
        }

        let g1 = build(&edges1);
        let n = edges1.len() + 1;
        let mut ans = vec![t; n];
        for i in 0..n {
            ans[i] += dfs(&g1, i, -1, k);
        }

        ans
    }
}
