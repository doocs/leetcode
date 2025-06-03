impl Solution {
    pub fn max_target_nodes(edges1: Vec<Vec<i32>>, edges2: Vec<Vec<i32>>) -> Vec<i32> {
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

        fn dfs(g: &Vec<Vec<i32>>, a: usize, fa: i32, c: &mut Vec<i32>, d: i32, cnt: &mut Vec<i32>) {
            c[a] = d;
            cnt[d as usize] += 1;
            for &b in &g[a] {
                if b != fa {
                    dfs(g, b as usize, a as i32, c, d ^ 1, cnt);
                }
            }
        }

        let g1 = build(&edges1);
        let g2 = build(&edges2);
        let n = g1.len();
        let m = g2.len();

        let mut c1 = vec![0; n];
        let mut c2 = vec![0; m];
        let mut cnt1 = vec![0; 2];
        let mut cnt2 = vec![0; 2];

        dfs(&g2, 0, -1, &mut c2, 0, &mut cnt2);
        dfs(&g1, 0, -1, &mut c1, 0, &mut cnt1);

        let t = cnt2[0].max(cnt2[1]);
        let mut ans = vec![0; n];
        for i in 0..n {
            ans[i] = t + cnt1[c1[i] as usize];
        }

        ans
    }
}
