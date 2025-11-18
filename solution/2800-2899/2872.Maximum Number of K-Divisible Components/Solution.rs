impl Solution {
    pub fn max_k_divisible_components(n: i32, edges: Vec<Vec<i32>>, values: Vec<i32>, k: i32) -> i32 {
        let n = n as usize;
        let mut g = vec![vec![]; n];
        for e in edges {
            let a = e[0] as usize;
            let b = e[1] as usize;
            g[a].push(b);
            g[b].push(a);
        }

        let mut ans = 0;

        fn dfs(i: usize, fa: i32, g: &Vec<Vec<usize>>, values: &Vec<i32>, k: i32, ans: &mut i32) -> i64 {
            let mut s = values[i] as i64;
            for &j in &g[i] {
                if j as i32 != fa {
                    s += dfs(j, i as i32, g, values, k, ans);
                }
            }
            if s % k as i64 == 0 {
                *ans += 1;
            }
            s
        }

        dfs(0, -1, &g, &values, k, &mut ans);
        ans
    }
}
