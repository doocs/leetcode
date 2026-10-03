impl Solution {
    pub fn min_score(n: i32, roads: Vec<Vec<i32>>) -> i32 {
        let n = n as usize;
        let mut g: Vec<Vec<(usize, i32)>> = vec![vec![]; n + 1];
        for e in roads {
            let a = e[0] as usize;
            let b = e[1] as usize;
            let w = e[2];
            g[a].push((b, w));
            g[b].push((a, w));
        }
        let mut vis = vec![false; n + 1];
        let mut ans = i32::MAX;
        let mut stk = vec![1usize];
        while let Some(a) = stk.pop() {
            if vis[a] {
                continue;
            }
            vis[a] = true;
            for &(b, w) in &g[a] {
                ans = ans.min(w);
                if !vis[b] {
                    stk.push(b);
                }
            }
        }
        ans
    }
}
