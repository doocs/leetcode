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
        fn farthest(g: &Vec<Vec<usize>>, start: usize) -> (i32, usize) {
            let mut ans = 0;
            let mut node = start;
            let mut stk = vec![(start, -1isize, 0i32)];
            while let Some((i, fa, t)) = stk.pop() {
                if ans < t {
                    ans = t;
                    node = i;
                }
                for &j in &g[i] {
                    if j as isize != fa {
                        stk.push((j, i as isize, t + 1));
                    }
                }
            }
            (ans, node)
        }
        let (_, a) = farthest(&g, 0);
        let (ans, _) = farthest(&g, a);
        ans
    }
}
