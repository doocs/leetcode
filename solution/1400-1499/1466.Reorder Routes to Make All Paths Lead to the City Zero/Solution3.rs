impl Solution {
    pub fn min_reorder(n: i32, connections: Vec<Vec<i32>>) -> i32 {
        let n = n as usize;
        let mut g: Vec<Vec<(i32, i32)>> = vec![vec![]; n];
        for e in connections.iter() {
            let a = e[0] as usize;
            let b = e[1] as usize;
            g[a].push((b as i32, 1));
            g[b].push((a as i32, 0));
        }
        let mut ans = 0;
        let mut stk: Vec<(usize, i32)> = vec![(0, -1)];
        while let Some((a, fa)) = stk.pop() {
            for &(b, c) in g[a].iter() {
                if b != fa {
                    ans += c;
                    stk.push((b as usize, a as i32));
                }
            }
        }
        ans
    }
}
