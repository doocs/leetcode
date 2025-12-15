use std::cmp::Reverse;
use std::collections::BinaryHeap;

impl Solution {
    pub fn min_cost_excluding_max(n: i32, edges: Vec<Vec<i32>>) -> i64 {
        let n = n as usize;

        let mut g: Vec<Vec<(usize, i64)>> = vec![Vec::new(); n];
        for e in edges {
            let u = e[0] as usize;
            let v = e[1] as usize;
            let w = e[2] as i64;
            g[u].push((v, w));
            g[v].push((u, w));
        }

        let inf: i64 = i64::MAX / 4;
        let mut dist = vec![[inf; 2]; n];
        dist[0][0] = 0;

        // (cur_cost, node, used)
        let mut pq = BinaryHeap::new();
        pq.push(Reverse((0_i64, 0_usize, 0_usize)));

        while let Some(Reverse((cur, u, used))) = pq.pop() {
            if cur > dist[u][used] {
                continue;
            }

            if u == n - 1 && used == 1 {
                return cur;
            }

            for &(v, w) in &g[u] {
                // normal edge
                let nxt = cur + w;
                if nxt < dist[v][used] {
                    dist[v][used] = nxt;
                    pq.push(Reverse((nxt, v, used)));
                }

                // skip max edge (only once)
                if used == 0 {
                    let nxt = cur;
                    if nxt < dist[v][1] {
                        dist[v][1] = nxt;
                        pq.push(Reverse((nxt, v, 1)));
                    }
                }
            }
        }

        dist[n - 1][1]
    }
}
