impl Solution {
    pub fn is_bipartite(graph: Vec<Vec<i32>>) -> bool {
        let n = graph.len();
        let mut p: Vec<usize> = (0..n).collect();

        fn find(x: usize, p: &mut Vec<usize>) -> usize {
            if p[x] != x {
                p[x] = find(p[x], p);
            }
            p[x]
        }

        for a in 0..n {
            for &b in &graph[a] {
                let pa = find(a, &mut p);
                let pb = find(b as usize, &mut p);
                if pa == pb {
                    return false;
                }
                p[pb] = find(graph[a][0] as usize, &mut p);
            }
        }
        true
    }
}
