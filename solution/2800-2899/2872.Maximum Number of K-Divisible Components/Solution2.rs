impl Solution {
    pub fn max_k_divisible_components(
        n: i32,
        edges: Vec<Vec<i32>>,
        values: Vec<i32>,
        k: i32,
    ) -> i32 {
        let n = n as usize;
        let mut g = vec![vec![]; n];
        for e in edges {
            let a = e[0] as usize;
            let b = e[1] as usize;
            g[a].push(b);
            g[b].push(a);
        }
        let mut sub = vec![0_i64; n];
        let mut ans = 0;
        let mut stk = vec![(0_usize, -1_i32, 0_i32)];
        while let Some((i, fa, state)) = stk.pop() {
            if state == 0 {
                stk.push((i, fa, 1));
                for &j in &g[i] {
                    if j as i32 != fa {
                        stk.push((j, i as i32, 0));
                    }
                }
            } else {
                let mut s = values[i] as i64;
                for &j in &g[i] {
                    if j as i32 != fa {
                        s += sub[j];
                    }
                }
                if s % k as i64 == 0 {
                    ans += 1;
                }
                sub[i] = s;
            }
        }
        ans
    }
}
