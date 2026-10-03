impl Solution {
    pub fn valid_path(n: i32, edges: Vec<Vec<i32>>, source: i32, destination: i32) -> bool {
        let n = n as usize;
        let source = source as usize;
        let destination = destination as usize;
        if source == destination {
            return true;
        }

        let mut g = vec![Vec::new(); n];
        for e in edges {
            let u = e[0] as usize;
            let v = e[1] as usize;
            g[u].push(v);
            g[v].push(u);
        }

        let mut vis = vec![false; n];
        vis[source] = true;
        let mut stk = vec![source];
        while let Some(i) = stk.pop() {
            for &j in &g[i] {
                if j == destination {
                    return true;
                }
                if !vis[j] {
                    vis[j] = true;
                    stk.push(j);
                }
            }
        }
        false
    }
}
