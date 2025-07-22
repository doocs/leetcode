impl Solution {
    pub fn minimum_diameter_after_merge(edges1: Vec<Vec<i32>>, edges2: Vec<Vec<i32>>) -> i32 {
        let d1 = Self::tree_diameter(&edges1);
        let d2 = Self::tree_diameter(&edges2);
        d1.max(d2).max((d1 + 1) / 2 + (d2 + 1) / 2 + 1)
    }

    fn tree_diameter(edges: &Vec<Vec<i32>>) -> i32 {
        let n = edges.len() + 1;
        let mut g = vec![vec![]; n];
        for e in edges {
            let a = e[0] as usize;
            let b = e[1] as usize;
            g[a].push(b);
            g[b].push(a);
        }
        let mut ans = 0;
        let mut a = 0;
        fn dfs(g: &Vec<Vec<usize>>, i: usize, fa: isize, t: i32, ans: &mut i32, a: &mut usize) {
            for &j in &g[i] {
                if j as isize != fa {
                    dfs(g, j, i as isize, t + 1, ans, a);
                }
            }
            if *ans < t {
                *ans = t;
                *a = i;
            }
        }
        dfs(&g, 0, -1, 0, &mut ans, &mut a);
        dfs(&g, a, -1, 0, &mut ans, &mut a);
        ans
    }
}
