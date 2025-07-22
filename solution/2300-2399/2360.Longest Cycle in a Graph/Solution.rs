impl Solution {
    pub fn longest_cycle(edges: Vec<i32>) -> i32 {
        let n = edges.len();
        let mut vis = vec![false; n];
        let mut ans = -1;

        for i in 0..n {
            if vis[i] {
                continue;
            }
            let mut j = i as i32;
            let mut cycle = Vec::new();

            while j != -1 && !vis[j as usize] {
                vis[j as usize] = true;
                cycle.push(j);
                j = edges[j as usize];
            }

            if j == -1 {
                continue;
            }

            for k in 0..cycle.len() {
                if cycle[k] == j {
                    ans = ans.max((cycle.len() - k) as i32);
                    break;
                }
            }
        }
        ans
    }
}
