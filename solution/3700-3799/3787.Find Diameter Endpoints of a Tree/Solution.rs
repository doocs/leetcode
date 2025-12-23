use std::collections::VecDeque;

impl Solution {
    pub fn find_special_nodes(n: i32, edges: Vec<Vec<i32>>) -> String {
        let n = n as usize;
        let mut g: Vec<Vec<usize>> = vec![vec![]; n];
        for e in edges {
            let a = e[0] as usize;
            let b = e[1] as usize;
            g[a].push(b);
            g[b].push(a);
        }

        fn bfs(start: usize, g: &Vec<Vec<usize>>) -> (usize, Vec<i32>) {
            let n = g.len();
            let mut dist = vec![-1i32; n];
            let mut q: VecDeque<usize> = VecDeque::new();
            dist[start] = 0;
            q.push_back(start);

            let mut far = start;
            while let Some(u) = q.pop_front() {
                if dist[u] > dist[far] {
                    far = u;
                }
                for &v in &g[u] {
                    if dist[v] == -1 {
                        dist[v] = dist[u] + 1;
                        q.push_back(v);
                    }
                }
            }
            (far, dist)
        }

        let (a, _) = bfs(0, &g);
        let (b, dist1) = bfs(a, &g);
        let (_, dist2) = bfs(b, &g);
        let d = dist1[b];

        let mut ans = vec![b'0'; n];
        for i in 0..n {
            if dist1[i] == d || dist2[i] == d {
                ans[i] = b'1';
            }
        }
        String::from_utf8(ans).unwrap()
    }
}
