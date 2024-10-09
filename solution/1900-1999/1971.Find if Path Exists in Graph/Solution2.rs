use std::collections::{HashSet, VecDeque};

impl Solution {
    pub fn valid_path(n: i32, edges: Vec<Vec<i32>>, source: i32, destination: i32) -> bool {
        let n = n as usize;
        let source = source as usize;
        let destination = destination as usize;

        let mut g = vec![Vec::new(); n];
        for edge in edges {
            let u = edge[0] as usize;
            let v = edge[1] as usize;
            g[u].push(v);
            g[v].push(u);
        }

        let mut q = VecDeque::new();
        let mut vis = HashSet::new();
        q.push_back(source);
        vis.insert(source);

        while let Some(i) = q.pop_front() {
            if i == destination {
                return true;
            }
            for &j in &g[i] {
                if !vis.contains(&j) {
                    vis.insert(j);
                    q.push_back(j);
                }
            }
        }

        false
    }
}
