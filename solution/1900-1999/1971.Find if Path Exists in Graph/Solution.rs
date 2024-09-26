impl Solution {
    pub fn valid_path(n: i32, edges: Vec<Vec<i32>>, source: i32, destination: i32) -> bool {
        let n = n as usize;
        let source = source as usize;
        let destination = destination as usize;

        let mut g = vec![Vec::new(); n];
        let mut vis = vec![false; n];

        for e in edges {
            let u = e[0] as usize;
            let v = e[1] as usize;
            g[u].push(v);
            g[v].push(u);
        }

        fn dfs(g: &Vec<Vec<usize>>, vis: &mut Vec<bool>, i: usize, destination: usize) -> bool {
            if i == destination {
                return true;
            }
            vis[i] = true;
            for &j in &g[i] {
                if !vis[j] && dfs(g, vis, j, destination) {
                    return true;
                }
            }
            false
        }

        dfs(&g, &mut vis, source, destination)
    }
}
