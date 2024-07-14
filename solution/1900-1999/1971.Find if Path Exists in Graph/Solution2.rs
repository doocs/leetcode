use std::collections::{HashSet, VecDeque};

impl Solution {
    pub fn valid_path(n: i32, edges: Vec<Vec<i32>>, source: i32, destination: i32) -> bool {
        let mut g = vec![HashSet::new(); n as usize];
        for e in edges {
            let a = e[0] as usize;
            let b = e[1] as usize;
            g[a].insert(b);
            g[b].insert(a);
        }

        let mut q = VecDeque::new();
        q.push_back(source as usize);
        let mut vis = vec![false; n as usize];
        vis[source as usize] = true;

        while let Some(i) = q.pop_front() {
            if i == (destination as usize) {
                return true;
            }
            for &j in &g[i] {
                if !vis[j] {
                    vis[j] = true;
                    q.push_back(j);
                }
            }
        }

        false
    }
}
