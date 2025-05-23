use std::collections::VecDeque;

impl Solution {
    pub fn closest_meeting_node(edges: Vec<i32>, node1: i32, node2: i32) -> i32 {
        let n = edges.len();
        let mut g = vec![Vec::new(); n];
        for i in 0..n {
            if edges[i] != -1 {
                g[i].push(edges[i] as usize);
            }
        }
        let inf = 1 << 30;
        let f = |mut i: usize| -> Vec<i32> {
            let mut dist = vec![inf; n];
            dist[i] = 0;
            let mut q = VecDeque::new();
            q.push_back(i);
            while !q.is_empty() {
                i = q.pop_front().unwrap();
                for &j in &g[i] {
                    if dist[j] == inf {
                        dist[j] = dist[i] + 1;
                        q.push_back(j);
                    }
                }
            }
            dist
        };
        let d1 = f(node1 as usize);
        let d2 = f(node2 as usize);
        let mut ans = -1;
        let mut d = inf;
        for i in 0..n {
            let t = std::cmp::max(d1[i], d2[i]);
            if t < d {
                d = t;
                ans = i as i32;
            }
        }
        ans
    }
}
