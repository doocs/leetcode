impl Solution {
    #[allow(dead_code)]
    pub fn is_bipartite(graph: Vec<Vec<i32>>) -> bool {
        let n = graph.len();
        let mut disjoint_set: Vec<usize> = vec![0; n];
        // Initialize the disjoint set
        for i in 0..n {
            disjoint_set[i] = i;
        }

        // Traverse the graph
        for i in 0..n {
            if graph[i].is_empty() {
                continue;
            }
            let first = graph[i][0] as usize;
            for v in &graph[i] {
                let v = *v as usize;
                let i_p = Self::find(i, &mut disjoint_set);
                let v_p = Self::find(v, &mut disjoint_set);
                if i_p == v_p {
                    return false;
                }
                // Otherwise, union the node
                Self::union(first, v, &mut disjoint_set);
            }
        }

        true
    }

    #[allow(dead_code)]
    fn find(x: usize, d_set: &mut Vec<usize>) -> usize {
        if d_set[x] != x {
            d_set[x] = Self::find(d_set[x], d_set);
        }
        d_set[x]
    }

    #[allow(dead_code)]
    fn union(x: usize, y: usize, d_set: &mut Vec<usize>) {
        let p_x = Self::find(x, d_set);
        let p_y = Self::find(y, d_set);
        d_set[p_x] = p_y;
    }
}
